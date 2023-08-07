package com.backtrack;


import java.util.*;

// https://leetcode.com/discuss/interview-question/1036815/amazon-sde2-onsite-implement-boggle-game-trie-dfs-scalability-follow-up-question
public class BoggleGame {

    private static final int[][] directions = {
            {-1, -1},
            {-1, 0},
            {-1, 1},

            {0, -1},
            {0, 1},

            {1, -1},
            {1, 0},
            {1, 1},

    };

    public static Set<String> presentWords(char[][] matrix, Set<String> dictionary) {
        Map<Character, List<Point>> charPosition = mapWithPosition(matrix);
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Set<String> presentWords = new HashSet<>();
        for (String word : dictionary) {
            char aChar = word.charAt(0);
            List<Point> points = charPosition.get(aChar);
            for (Point point : points) {
                if (isPresent(aChar, matrix, word, visited, charPosition, point)) {
                    presentWords.add(word);
                }
            }
            clearVisited(visited);
        }
        return presentWords;
    }

    private static void clearVisited(boolean[][] visited) {
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
    }

    private static boolean isPresent(char aChar, char[][] matrix, String word, boolean[][] visited,
                                     Map<Character, List<Point>> charPosition, Point point) {
        int totalRows = matrix.length;
        int totalColumns = matrix[0].length;
        if (word.length() == 0) {
            return true;
        }

        //char firstChar = word.charAt(0); //start with first index
        char firstChar = matrix[point.x][point.y];
        if (firstChar != aChar) {
            return false;
        }
        List<Point> points = charPosition.get(firstChar);
        if (points == null) {
            return false;
        }
        if (word.length() == 1) {
            return true;
        }
        for (Point currentPoint : points) {
            if (isVisited(currentPoint, visited)) {
                continue;
            }
            visited[currentPoint.x][currentPoint.y] = true;
            for (int[] direction : directions) {
                Point nextMove = getNextMove(currentPoint, direction);
                if (isWithinBoundary(totalRows, totalColumns, nextMove) && !isVisited(nextMove, visited)) {
                    boolean isPresent = isPresent(word.charAt(1), matrix, word.substring(1), visited, charPosition, nextMove);
                    if (isPresent) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Point getNextMove(Point point, int[] direction) {
        return new Point(point.x + direction[0], point.y + direction[1]);
    }


    private static boolean isVisited(Point point, boolean[][] visited) {
        return visited[point.x][point.y];
    }

    private static boolean isWithinBoundary(int totalRows, int totalColumns, Point point) {
        return point.x >= 0 && point.x < totalRows && point.y >= 0 && point.y < totalColumns;
    }


    private static Map<Character, List<Point>> mapWithPosition(char[][] matrix) {
        Map<Character, List<Point>> charPosition = new HashMap<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int colNum = 0; colNum < cols; colNum++) {
            for (int rowNum = 0; rowNum < rows; rowNum++) {
                char aChar = matrix[rowNum][colNum];
                int currRow = rowNum;
                int currCol = colNum;
                charPosition.compute(aChar, (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(new Point(currRow, currCol));
                    return v;
                });
            }
        }
        return charPosition;
    }


    record Point(int x, int y) {
    }

    public static void main(String[] args) {

        Set<String> checkWords = new HashSet<>();
        checkWords.add("DATA");
        checkWords.add("HALO");
        checkWords.add("HALT");
        checkWords.add("SAG");
        checkWords.add("BEAT");
        checkWords.add("TOTAL");
        checkWords.add("GLOT");
        checkWords.add("DAG");

        //not present
        checkWords.add("DAAAD");
        checkWords.add("TTALB");

        char[][] matrix = {
                {'D', 'A', 'T', 'H'},
                {'C', 'G', 'O', 'A'},
                {'S', 'A', 'T', 'L'},
                {'B', 'E', 'D', 'G'},
        };

        Set<String> presentWords = presentWords(matrix, checkWords);

        System.out.println("Words present -> " + presentWords);

    }

}

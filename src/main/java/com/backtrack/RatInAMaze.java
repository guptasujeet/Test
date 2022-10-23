package com.backtrack;

import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {

    public static ArrayList<String> findSum(int[][] arr, int n) {
        // Write your code here.

        if (n == 0) {
            return new ArrayList<>();
        }

        if (n == 1) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("D");
            return ans;
        }

        ArrayList<String> paths = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        findPaths(arr, 0, 0, n, visited, paths, "");

        return paths;
    }

    private static void findPaths(int[][] arr, int i, int j, int n,
                                  boolean[][] visited, ArrayList<String> paths, String currentPath) {

        if (i == n - 1 && j == n - 1) {
            paths.add(currentPath);
            return;
        }

        List<Direction> visitedDirection = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Move nextMove = getNextPathDirection(direction, arr, i, j, n, visited);
            if (nextMove.hasNextMove && !visitedDirection.contains(direction)) {
                visitedDirection.add(direction);
                findPaths(arr, nextMove.i, nextMove.j, n, visited, paths, currentPath + nextMove.direction);
            }
        }

        //backtrack
        visited[i][j] = false;

    }

    private static Move getNextPathDirection(Direction direction, int[][] arr, int i, int j, int n, boolean[][] visited) {

        Direction nextDirection = null;
        int nextI = i + direction.iInc;
        int nextJ = j + direction.jInc;
        if (checkWithinRange(nextI, nextJ, n)
                && isAvailable(arr, nextI, nextJ)
                && isNotVisited(visited, nextI, nextJ)) {
            visited[i][j] = true;
            nextDirection = direction;
        }

        Move move;
        if (nextDirection != null) {
            move = new Move(nextI, nextJ, nextDirection, true);
        } else {
            move = new Move(nextI, nextJ, null, false);
        }

        return move;

    }

    private static boolean checkWithinRange(int i, int j, int n) {
        return i >= 0 && j >= 0 && i < n && j < n;
    }

    private static boolean isAvailable(int[][] arr, int i, int j) {
        return arr[i][j] == 1;
    }

    private static boolean isNotVisited(boolean[][] visited, int i, int j) {
        return !visited[i][j];
    }

    static class Move {

        int i;
        int j;
        Direction direction;
        boolean hasNextMove;

        public Move(int i, int j, Direction direction, boolean hasNextMove) {
            this.i = i;
            this.j = j;
            this.direction = direction;
            this.hasNextMove = hasNextMove;
        }
    }


    enum Direction {

        D(1, 0),
        L(0, -1),
        R(0, 1),
        U(-1, 0);

        int iInc;
        int jInc;

        Direction(int i, int j) {
            iInc = i;
            jInc = j;
        }


    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };

        ArrayList<String> path = findSum(arr, arr.length);

        System.out.println(path);
        //DDRDRR
        //DRDDRR
    }

}

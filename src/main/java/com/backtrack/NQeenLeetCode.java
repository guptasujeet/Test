package com.backtrack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://leetcode.com/problems/n-queens/
public class NQeenLeetCode {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answers = new ArrayList<>();
        Board board = new Board(n);
        placeNQueens(n, 0, answers, board);
        return answers;
    }


    public void placeNQueens(int n, int column, List<List<String>> answers, Board board) {
        if (n == column) {
            answers.add(board.boardDataToFlatString());
        }
        for (int row = 0; row < n; row++) {
            if (board.isSafeToPlace(row, column)) {
                board.placeQueen(row, column);
                placeNQueens(n, column + 1, answers, board);
                board.removeQueen(row, column);
            }
        }
    }


    static class Board {

        private final int n;
        private final String[][] board;
        private final Set<Integer> rowPlacements = new HashSet<>();
        private final Set<Integer> diagonalUpPlacements = new HashSet<>();
        private final Set<Integer> diagonalDownPlacements = new HashSet<>();

        Board(int n) {
            this.n = n;
            this.board = createBoard();
        }

        private String[][] createBoard() {
            String[][] board = new String[n][n];
            for (String[] chars : board) {
                Arrays.fill(chars, ".");
            }
            return board;
        }

        public void placeQueen(int row, int column) {
            board[row][column] = "Q";
            rowPlacements.add(row);
            diagonalUpPlacements.add(getUpperDiagonalNumber(row, column));
            diagonalDownPlacements.add(getLowerDiagonalNumber(row, column));
        }

        private boolean isSafeToPlace(int row, int column) {
            if (rowPlacements.contains(row)
                    || diagonalUpPlacements.contains(getUpperDiagonalNumber(row, column))
                    || diagonalDownPlacements.contains(getLowerDiagonalNumber(row, column))
            ) {
                //if any of the placements contain it already then, we can't place it again
                return false;
            }
            return true;
        }

        private void removeQueen(int row, int column) {
            board[row][column] = ".";
            rowPlacements.remove(row);
            diagonalUpPlacements.remove(getUpperDiagonalNumber(row, column));
            diagonalDownPlacements.remove(getLowerDiagonalNumber(row, column));
        }

        //same number will be generated for all cells in the diagonal
        private int getUpperDiagonalNumber(int row, int column) {
            return n - 1 + column - row;
        }

        //same number will be generated for all cells in the diagonal
        private int getLowerDiagonalNumber(int row, int column) {
            return row + column;
        }

        public List<String> boardDataToFlatString() {
            return Stream.of(board).map(e -> String.join("", e)).collect(Collectors.toList());
        }
    }


    public static void main(String[] args) {

        NQeenLeetCode nQeenLeetCode = new NQeenLeetCode();

        int n = 4;
        List<List<String>> placements = nQeenLeetCode.solveNQueens(n);
        //2 ways to place hence size of array list is 2 for n =4

        for (List<String> placement : placements) {
            placement.forEach(System.out::println);
            System.out.println("----------------");
        }
    }

}

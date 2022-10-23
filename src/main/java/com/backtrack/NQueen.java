package com.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NQueen {

    public static ArrayList<ArrayList<Integer>> nQueens(int n) {
        // Write Your Code Here
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        Board board = new Board(n);

        findQueensPositions(0, n, board, answer);

        return answer;
    }


    private static void findQueensPositions(int col, int n,
                                            Board board,
                                            ArrayList<ArrayList<Integer>> answer) {

        if (col == n) {
            copyBoardDataToAnswer(board, answer);
            return;
        }


        for (int row = 0; row < n; row++) {
            if (board.isSafe(row, col, board)) {
                board.placeQueen(row, col);
                findQueensPositions(col + 1, n, board, answer);
                //backtrack
                board.removeQueen(row, col);
            }
        }

    }


    private static void copyBoardDataToAnswer(Board board, ArrayList<ArrayList<Integer>> answers) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (ArrayList<Integer> placements : board.board) {
            answer.addAll(placements);
        }
        answers.add(answer);
    }

    static class Board {

        ArrayList<ArrayList<Integer>> board;
        private int n;
        Map<Integer, Boolean> rowPlacement;
        Map<Integer, Boolean> diagonalDownPlacement;
        Map<Integer, Boolean> diagonalUpPlacement;

        public Board(int n) {
            this.board = createBoard(n);
            this.rowPlacement = new HashMap<>();
            this.diagonalDownPlacement = new HashMap<>();
            this.diagonalUpPlacement = new HashMap<>();
            this.n = n;
        }

        public void placeQueen(int row, int col) {
            board.get(row).set(col, 1);
            rowPlacement.put(row, true);
            diagonalDownPlacement.put((row + col), true);
            diagonalUpPlacement.put((n - 1) + (col - row), true);
        }

        public void removeQueen(int row, int col) {
            board.get(row).set(col, 0);
            rowPlacement.remove(row);
            diagonalDownPlacement.remove((row + col));
            diagonalUpPlacement.remove((n - 1) + (col - row));
        }

        public boolean isSafe(int row, int col, Board board) {
            if (rowPlacement.getOrDefault(row, false)) {
                return false;
            }
            if (diagonalDownPlacement.getOrDefault(row + col, false)) {
                return false;
            }
            if (diagonalUpPlacement.getOrDefault((n - 1) + (col - row), false)) {
                return false;
            }
            return true;
        }

        private static ArrayList<ArrayList<Integer>> createBoard(int n) {
            ArrayList<ArrayList<Integer>> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> row = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    row.add(0);
                }
                board.add(row);
            }
            return board;
        }
    }


    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> placement = nQueens(4);
        System.out.println(placement);

    }

}

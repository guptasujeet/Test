package com.backtrack;

import com.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//https://www.codingninjas.com/codestudio/problems/sudoku-solver_699919?
public class SudokuSolver {

    public static void solveSudoku(int[][] sudoku) {
        // Write your code here.
        Board board = new Board(sudoku);
        solve(board);
    }


    private static boolean solve1(Board board) {
        for (int row = 0; row < board.boardSize; row++) {
            for (int column = 0; column < board.boardSize; column++) {
                if (board.board[row][column] == 0) {
                    for (int i = 1; i <= 9; i++) {
                        if (board.isSafeToPlaceValue(row, column, i)) {
                            board.setBoardValue(row, column, i);
                            boolean solved = solve(board);
                            if (solved) {
                                return true;
                            }
                            //backtrack
                            board.unsetBoardValue(row, column);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solve(Board board) {
        if (board.emptyCells.isEmpty()) {
            return true;
        }
        Pair<Integer, Integer> cell = board.emptyCells.get(0);
        for (int i = 1; i <= 9; i++) {
            if (board.isSafeToPlaceValue(cell.getFirst(), cell.getSecond(), i)) {
                board.setBoardValue(cell.getFirst(), cell.getSecond(), i);
                boolean solved = solve(board);
                if (solved) {
                    return true;
                }
                //backtrack
                board.unsetBoardValue(cell.getFirst(), cell.getSecond());
            }
        }
        return false;
    }

    static class Board {
        int[][] board;
        int boardSize = 9;
        List<Pair<Integer, Integer>> emptyCells;


        public Board(int[][] board) {
            this.board = board;
            this.emptyCells = getEmptyCells();
        }

        void setBoardValue(int i, int j, int val) {
            board[i][j] = val;
            emptyCells.remove(Pair.of(i, j));
        }

        void unsetBoardValue(int i, int j) {
            board[i][j] = 0;
            emptyCells.add(Pair.of(i, j));
        }

        boolean isSafeToPlaceValue(int row, int column, int val) {
            for (int i = 0; i < boardSize; i++) {

                //row check
                if (board[row][i] == val) {
                    return false;
                }

                //row check
                if (board[i][column] == val) {
                    return false;
                }

                //3 * 3 matrix check
                if (board[(3 * (row / 3)) + i / 3][(3 * (column / 3)) + i % 3] == val) {
                    return false;
                }
            }
            return true;
        }

        List<Pair<Integer, Integer>> getEmptyCells() {
            List<Pair<Integer, Integer>> emptyCells = new ArrayList<>();
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (board[i][j] == 0) {
                        emptyCells.add(Pair.of(i, j));
                    }
                }
            }
            return emptyCells;
        }
    }


    public static void main(String[] args) {
        int[][] sudoku = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };

        int[][] sudoku2 = new int[][]{
                {0, 8, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 6, 8, 7},
                {0, 7, 0, 6, 0, 3, 2, 5, 0},
                {6, 0, 0, 1, 0, 0, 0, 0, 9},
                {0, 4, 0, 5, 9, 8, 1, 6, 0},
                {0, 9, 0, 0, 0, 4, 0, 3, 0},
                {9, 6, 0, 0, 7, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 8, 0, 1},
                {8, 0, 0, 3, 0, 0, 0, 0, 0}
        };


        solveSudoku(sudoku2);
        System.out.println("Solved -> ");

        for (int[] row : sudoku2) {
            System.out.println(Arrays.toString(row));
        }

        //output should be below answer 1
        /*
        3 1 6 5 7 8 4 9 2
        5 2 9 1 3 4 7 6 8
        4 8 7 6 2 9 5 3 1
        2 6 3 4 1 5 9 8 7
        9 7 4 8 6 3 1 2 5
        8 5 1 7 9 2 6 4 3
        1 3 8 9 4 7 2 5 6
        6 9 2 3 5 1 8 7 4
        7 4 5 2 8 6 3 1 9
        */

        /*
        output answer 2

5 8 6 4 2 7 9 1 3
3 2 4 9 1 5 6 8 7
1 7 9 6 8 3 2 5 4
6 5 8 1 3 2 4 7 9
7 4 3 5 9 8 1 6 2
2 9 1 7 6 4 5 3 8
9 6 2 8 7 1 3 4 5
4 3 7 2 5 6 8 9 1
8 1 5 3 4 9 7 2 6
         */


    }
}

package com.dp;


import java.util.concurrent.atomic.AtomicInteger;

//https://www.youtube.com/watch?v=MMr19RE7KYY&t=707s
public class LargestSquareInAMatrix {

    public static int largestSquare(int[][] matrix) {
        return solve(matrix, 0, 0, matrix.length, matrix[0].length, new AtomicInteger(0));
    }

    public static int solve(int[][] matrix, int i, int j, int rowCount, int columnCount,
                            AtomicInteger maxSqSize) {
        if (i >= rowCount) {
            return 0;
        }

        if (j >= columnCount) {
            return 0;
        }

        int cellVal = matrix[i][j];

        int rightPair = solve(matrix, i, j + 1, rowCount, columnCount, maxSqSize);
        int downPair = solve(matrix, i + 1, j, rowCount, columnCount, maxSqSize);
        int diagonalPair = solve(matrix, i + 1, j + 1, rowCount, columnCount, maxSqSize);

        if (cellVal == 1) {
            return 1 + Math.min(rightPair, Math.min(downPair, diagonalPair));
        }

        return 0;

    }


    public static void main(String[] args) {


        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };

        System.out.println(largestSquare(matrix));
    }

}

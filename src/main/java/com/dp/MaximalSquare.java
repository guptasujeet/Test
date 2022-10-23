package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=MMr19RE7KYY&t=637s
//https://leetcode.com/problems/maximal-square/
class MaximalSquare {


    int maxArea = Integer.MIN_VALUE;

    public int maximalSquare(char[][] matrix) {
        int[][] memory = new int[matrix.length][matrix[0].length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        maxArea = Integer.MIN_VALUE;
        maximalSquareLength(matrix, 0, 0, memory);

        return maxArea * maxArea;

    }

    private int maximalSquareLength(char[][] matrix, int i, int j, int[][] memory) {
        if (i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }

        if (memory[i][j] != -1) {
            return memory[i][j];
        }

        int rightAns = maximalSquareLength(matrix, i, j + 1, memory);
        int downAns = maximalSquareLength(matrix, i + 1, j, memory);
        int diagonalAns = maximalSquareLength(matrix, i + 1, j + 1, memory);

        int ans = 0;
        if (matrix[i][j] == '1') {
            //minimum will be taken as length as that can only form square
            ans = 1 + Math.min(rightAns, Math.min(downAns, diagonalAns));
            //store max length
            maxArea = Math.max(maxArea, ans);
        } else {
            ans = 0;
        }

        memory[i][j] = ans;
        return ans;
    }


    public static void main(String[] args) {

        MaximalSquare maximalRectangle = new MaximalSquare();

        char[][] matrix1 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        char[][] matrix2 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0'}
        };


        System.out.println(maximalRectangle.maximalSquare(matrix1)); //4
        System.out.println(maximalRectangle.maximalSquare(matrix2)); //9
        System.out.println(maximalRectangle.maximalSquare(new char[][]{{'0'}})); //0
        System.out.println(maximalRectangle.maximalSquare(new char[][]{{'1'}})); //1

    }

}

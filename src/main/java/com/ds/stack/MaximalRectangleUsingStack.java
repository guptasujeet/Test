package com.ds.stack;

public class MaximalRectangleUsingStack {

    public int maximalRectangle(char[][] matrix) {

        int maxArea = Integer.MIN_VALUE;

        LargestAreaInHistogram histogram = new LargestAreaInHistogram();
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        int rowStart = 0;
        int rowEnd = 0;
        while (rowStart <= rowEnd && rowEnd < rowLength) {
            int[] histogramRepHeight = new int[columnLength];
            for (int row = 0; row <= rowEnd; row++) {
                for (int column = 0; column < columnLength; column++) {
                    if (matrix[row][column] == '1') {
                        histogramRepHeight[column]++;
                    } else { // i.e 0
                        //base row, no base , set everything as 0
                        histogramRepHeight[column] = 0;
                    }
                }
            }
            int area = histogram.largestRectangleArea(histogramRepHeight);
            maxArea = Math.max(maxArea, area);
            rowEnd++;
        }

        return maxArea;
    }


    public static void main(String[] args) {

        MaximalRectangleUsingStack maximalRectangle = new MaximalRectangleUsingStack();


        char[][] matrix3 = new char[][]{
                {'1', '0', '1', '1', '1'},
                {'0', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1'}
        };

        System.out.println(maximalRectangle.maximalRectangle(matrix3)); //6

        char[][] matrix1 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        char[][] matrix2 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };


        System.out.println(maximalRectangle.maximalRectangle(matrix1)); //6
        System.out.println(maximalRectangle.maximalRectangle(matrix2)); //6
        System.out.println(maximalRectangle.maximalRectangle(new char[][]{{'0'}})); //0
        System.out.println(maximalRectangle.maximalRectangle(new char[][]{{'1'}})); //1


    }
}

package com.dp;

import java.util.Arrays;

//https://leetcode.com/problems/flood-fill/
public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int matchColor = image[sr][sc];
        if (matchColor == color) {
            return image;
        }
        fill(image, sr, sc, matchColor, color);
        return image;
    }

    public void fill(int[][] image, int sr, int sc, int matchColor, int color) {
        if (sr < 0 || sr >= image.length) {
            return;
        }

        if (sc < 0 || sc >= image[0].length) {
            return;
        }

        int currCellColor = image[sr][sc];
        if (currCellColor == matchColor) {
            image[sr][sc] = color;
            fill(image, sr, sc + 1, matchColor, color);
            fill(image, sr + 1, sc, matchColor, color);
            fill(image, sr - 1, sc, matchColor, color);
            fill(image, sr, sc - 1, matchColor, color);
        }

    }


    public static void main(String[] args) {

        FloodFill floodFill = new FloodFill();

        int[][] matrix1 = new int[][]{
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}};


        int[][] ans1 = floodFill.floodFill(matrix1, 1, 1, 2);

        for (int[] row : ans1) {
            System.out.println(Arrays.toString(row));
        }


    }

}

package com.dp;

import java.util.Arrays;

//https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
//https://www.youtube.com/watch?v=Ntzuz7XsdCI&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=19
public class MaxHeightCuboid {


    public int maxHeightRec(int[][] cuboids) {
        //step 1 sort by height 1st
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }

        //step 2 sort by width 2nd

        Arrays.sort(cuboids, (a, b) -> {
            int ans = a[0] - b[0];
            if (ans == 0) {
                ans = a[1] - b[1];
            }
            if (ans == 0) {
                return a[2] - b[2];
            }
            return ans;
        });

        int[][] memory = new int[cuboids.length + 1][cuboids.length + 1];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }

        return calculateMaxHeightRec(cuboids, 0, -1, memory);

    }

    private int calculateMaxHeightRec(int[][] cuboids, int currentIndex, int prevIndex, int[][] memory) {
        if (currentIndex >= cuboids.length) {
            return 0;
        }

        if (memory[currentIndex][prevIndex + 1] != -1) {
            return memory[currentIndex][prevIndex + 1];
        }

        int include = Integer.MIN_VALUE;
        if (prevIndex == -1 || canPlace(cuboids[currentIndex], cuboids[prevIndex])) {
            include = cuboids[currentIndex][2] + calculateMaxHeightRec(cuboids, currentIndex + 1, currentIndex, memory);
        }
        int exclude = calculateMaxHeightRec(cuboids, currentIndex + 1, prevIndex, memory);

        memory[currentIndex][prevIndex + 1] = Math.max(include, exclude);
        return memory[currentIndex][prevIndex + 1];
    }


    public int maxHeight(int[][] cuboids) {

        //step 1 sort by height 1st
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }

        //step 2 sort by width 2nd

        Arrays.sort(cuboids, (a, b) -> {
            int ans = a[0] - b[0];
            if (ans == 0) {
                ans = a[1] - b[1];
            }
            if (ans == 0) {
                return a[2] - b[2];
            }
            return ans;
        });

        //step 3 find longest increasing sub sequence

        int size = cuboids.length;
        int[] next = new int[size + 1];
        int[] curr = new int[size + 1];

        for (int currentIndex = size - 1; currentIndex >= 0; currentIndex--) {
            for (int prevIndex = currentIndex - 1; prevIndex >= -1; prevIndex--) {
                int include = 0;
                if (prevIndex == -1 || canPlace(cuboids[currentIndex], cuboids[prevIndex])) {
                    include = cuboids[currentIndex][2] + next[currentIndex + 1];
                }
                int exclude = next[prevIndex + 1];
                curr[prevIndex + 1] = Math.max(include, exclude);
            }
            int[] temp = curr;
            curr = next;
            next = temp;
        }
        return next[0];
    }

    private boolean canPlace(int[] base, int[] newBox) {
        //return base[0] >= newBox[0] && base[1] >= newBox[1] && base[2] >= newBox[2];
        return newBox[0] <= base[0] && newBox[1] <= base[1] && newBox[2] <= base[2];
    }


    public static void main(String[] args) {

        MaxHeightCuboid height = new MaxHeightCuboid();
        System.out.println(height.maxHeightRec(new int[][]{{92, 47, 83}, {75, 20, 87}, {68, 12, 83}, {12, 85, 15}, {16, 24, 47}, {69, 65, 35}, {96, 56, 93}, {89, 93, 11}, {86, 20, 41}, {69, 77, 12}, {83, 80, 97}, {90, 22, 36}})); //447
        System.out.println(height.maxHeight(new int[][]{{92, 47, 83}, {75, 20, 87}, {68, 12, 83}, {12, 85, 15}, {16, 24, 47}, {69, 65, 35}, {96, 56, 93}, {89, 93, 11}, {86, 20, 41}, {69, 77, 12}, {83, 80, 97}, {90, 22, 36}})); //447

        System.out.println(height.maxHeightRec(new int[][]{{7, 11, 17}, {7, 17, 11}, {11, 7, 17}, {11, 17, 7}, {17, 7, 11}, {17, 11, 7}})); //102
        System.out.println(height.maxHeight(new int[][]{{7, 11, 17}, {7, 17, 11}, {11, 7, 17}, {11, 17, 7}, {17, 7, 11}, {17, 11, 7}})); //102

        System.out.println(height.maxHeightRec(new int[][]{{50, 45, 20}, {95, 37, 53}, {45, 23, 12}})); //190
        System.out.println(height.maxHeight(new int[][]{{50, 45, 20}, {95, 37, 53}, {45, 23, 12}})); //190

        System.out.println(height.maxHeightRec(new int[][]{{38, 25, 45}, {76, 35, 3}})); //76
        System.out.println(height.maxHeight(new int[][]{{38, 25, 45}, {76, 35, 3}})); //76

    }

}

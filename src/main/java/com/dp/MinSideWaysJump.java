package com.dp;


import java.util.Arrays;

//https://leetcode.com/problems/minimum-sideway-jumps/
//https://www.youtube.com/watch?v=dvTTtzamEEo&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb
public class MinSideWaysJump {

    public static final int LANE_COUNT = 3;

    public int minSideJumps(int[] obstacles) {
        int[][] memory = new int[4][obstacles.length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return getMinSideJump(obstacles, obstacles.length - 1, 2, 0, memory);
    }

    private int getMinSideJump(int[] obstacles, int destination, int currentLane, int currentPoint, int[][] memory) {
        if (destination == currentPoint) {
            return 0;
        }

        if (memory[currentLane][currentPoint] != -1) {
            return memory[currentLane][currentPoint];
        }

        //check next point does not have obstacles
        if (currentPoint < destination && obstacles[currentPoint + 1] != currentLane) {
            return getMinSideJump(obstacles, destination, currentLane, currentPoint + 1, memory);
        }

        /*while (currentPoint < destination && obstacles[currentPoint + 1] != currentLane) {
            currentPoint = currentPoint + 1;
        }

        if (destination == currentPoint) {
            return 0;
        }*/

        int minSideJump = Integer.MAX_VALUE;
        for (int lane = 1; lane <= LANE_COUNT; lane++) {
            if (lane != currentLane) {
                if (obstacles[currentPoint] != lane) {
                    int ans = 1 + getMinSideJump(obstacles, destination, lane, currentPoint, memory);
                    minSideJump = Math.min(minSideJump, ans);
                }
            }
        }
        memory[currentLane][currentPoint] = minSideJump;
        return minSideJump;
    }

    public static void main(String[] args) {
        MinSideWaysJump minJum = new MinSideWaysJump();

        System.out.println(minJum.minSideJumps(new int[]{0, 1, 2, 3, 0}));
        System.out.println(minJum.minSideJumps(new int[]{0, 1, 1, 3, 3, 0}));
        System.out.println(minJum.minSideJumps(new int[]{0, 2, 1, 0, 3, 0}));
        System.out.println(minJum.minSideJumps(new int[]{0, 1, 0, 1, 3, 1, 1, 1, 0, 2, 0}));
    }
}

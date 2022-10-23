package com.dp;


import java.util.Arrays;

//https://www.youtube.com/watch?v=aJTCcyPrPOA
//https://practice.geeksforgeeks.org/problems/get-minimum-squares0538/1
public class PerfectSquareSum {

    public static int findMinWaysForPerfectSquareSum(int target) {

        int[] memory = new int[target + 1];
        Arrays.fill(memory, -1);
        memory[0] = 0;
        return minWaysPerfectSquare(target, memory);

    }

    private static int minWaysPerfectSquare(int target, int[] memory) {
        if (target <= 0) {
            return 0;
        }

        if (memory[target] != -1) {
            return memory[target];
        }

        int min = target;
        for (int i = 1; i <= Math.sqrt(target); i++) {
            int sq = i * i;
            int ans = minWaysPerfectSquare(target - sq, memory);
            min = Math.min(min, ans + 1);
        }
        memory[target] = min;
        return min;
    }


    public static void main(String[] args) {
        System.out.println(findMinWaysForPerfectSquareSum(6));
        System.out.println(findMinWaysForPerfectSquareSum(100));
    }
}

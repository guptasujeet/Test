package com.dp;


import com.util.Pair;

import java.util.Arrays;

//https://www.youtube.com/watch?v=YaMcX7sem70&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=24
//https://practice.geeksforgeeks.org/problems/longest-arithmetic-progression1019/1
public class LongestArithmeticSubSequenceAP {


    private int getLongestArithmeticSubSequence(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num1 = nums[i];
                int num2 = nums[j];
                int diff = Math.abs(num2 - num1);
                int len = 2;
                int lastSeriesIndex = i;
                for (int k = i - 1; k >= 0; k--) {
                    if (Math.abs(nums[k] - nums[lastSeriesIndex]) == diff) {
                        lastSeriesIndex = k;
                        len++;
                    }
                }
                ans = Math.max(ans, len);
            }
        }
        return ans;
    }


    private int solve(int[] nums, int i, int diff, int[][] memory) {

        if (i < 0) {
            return 0;
        }

        if (memory[i][diff] != -1) {
            return memory[i][diff];
        }

        int ans = 0;
        for (int k = i - 1; k >= 0; k--) {
            if (Math.abs(nums[k] - nums[i]) == diff) {
                ans = 1 + solve(nums, k, diff, memory);
            }
        }
        memory[i][diff] = ans;
        return ans;
    }

    private int getLongestArithmeticSubSequence2(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        //how to tweak the 100 diff
        //use hashmap for memory instead of array
        int[][] memory = new int[nums.length][100];
        fillArray(memory);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num1 = nums[i];
                int num2 = nums[j];
                int diff = Math.abs(num2 - num1);
                int len = 2 + solve(nums, i, diff, memory);
                ans = Math.max(ans, len);
            }
            fillArray(memory);
        }
        return ans;
    }


    private int solveSpaceOptimized3(int[] nums, int i, int diff, Pair[] memory) {

        if (i < 0) {
            return 0;
        }

        if (memory[i] != null && (Integer) memory[i].getFirst() == diff) {
            return (Integer) memory[i].getSecond();
        }

        int ans = 0;
        for (int k = i - 1; k >= 0; k--) {
            if (Math.abs(nums[k] - nums[i]) == diff) {
                ans = 1 + solveSpaceOptimized3(nums, k, diff, memory);
            }
        }
        memory[i] = Pair.of(diff, ans);
        return ans;
    }

    private int getLongestArithmeticSubSequence3SpaceOptimized(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        //how to tweak the 100 diff
        Pair[] memory = new Pair[nums.length];
        reset(memory);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int num1 = nums[i];
                int num2 = nums[j];
                int diff = Math.abs(num2 - num1);
                if (memory[i] != null && (Integer) memory[i].getFirst() == diff) {
                    return (Integer) memory[i].getSecond();
                }
                int len = 2 + solveSpaceOptimized3(nums, i, diff, memory);
                ans = Math.max(ans, len);
            }
            reset(memory);
        }
        return ans;
    }


    private void reset(Pair[] memory) {
        Arrays.fill(memory, null);
    }


    private void fillArray(int[][] memory) {
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
    }


    public static void main(String[] args) {
        LongestArithmeticSubSequenceAP ap = new LongestArithmeticSubSequenceAP();
        System.out.println(ap.getLongestArithmeticSubSequence(new int[]{1, 7, 10, 13, 14, 19})); // 4
        System.out.println(ap.getLongestArithmeticSubSequence2(new int[]{1, 7, 10, 13, 14, 19})); // 4
        System.out.println(ap.getLongestArithmeticSubSequence3SpaceOptimized(new int[]{1, 7, 10, 13, 14, 19})); // 4

        System.out.println(ap.getLongestArithmeticSubSequence(new int[]{2, 4, 6, 8, 10})); // 5
        System.out.println(ap.getLongestArithmeticSubSequence2(new int[]{2, 4, 6, 8, 10})); // 5
        System.out.println(ap.getLongestArithmeticSubSequence3SpaceOptimized(new int[]{2, 4, 6, 8, 10})); // 5

    }
}

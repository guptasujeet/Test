package com.dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;


//https://www.codingninjas.com/codestudio/problems/maximum-sum-of-non-adjacent-elements_843261
//https://www.youtube.com/watch?v=m9-H6AUBLgY
public class MaximumSumNonAdjacent {


    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        // Write your code here.
        if (nums.size() == 1) {
            return nums.get(0);
        }
        //return solveMaxSumRecBottomUp(nums);
        return solveMaxSumRecBottomUpSpaceOptimized(nums);
        /*int[] memory = new int[nums.size() + 1];
        Arrays.fill(memory, -1);
        return solveMaxSumRec(nums, nums.size() - 1, memory);*/
    }

    private static int solveMaxSumRec(ArrayList<Integer> nums, int currentPos, int[] memory) {
        if (currentPos < 0) {
            return 0;
        }

        int currentNumber = nums.get(currentPos);
        if (currentPos == 0) {
            return currentNumber;
        }

        if (memory[currentPos] != -1) {
            return memory[currentPos];
        }

        int exclude = solveMaxSumRec(nums, currentPos - 1, memory);
        int include = solveMaxSumRec(nums, currentPos - 2, memory) + currentNumber;
        int maxSum = Math.max(include, exclude);
        memory[currentPos] = maxSum;
        return maxSum;
    }


    private static int solveMaxSumRecBottomUp(ArrayList<Integer> nums) {
        int[] memory = new int[nums.size() + 1];
        memory[0] = nums.get(0);

        for (int currentPos = 1; currentPos < nums.size(); currentPos++) {
            int exclude = memory[currentPos - 1];
            int include = nums.get(currentPos);
            if (currentPos - 2 >= 0) {
                include = memory[currentPos - 2] + nums.get(currentPos);
            }

            int maxSum = Math.max(include, exclude);
            memory[currentPos] = maxSum;
        }
        return memory[nums.size() - 1];
    }

    private static int solveMaxSumRecBottomUpSpaceOptimized(ArrayList<Integer> nums) {
        int prev2 = 0;
        int prev1 = nums.get(0);

        for (int currentPos = 1; currentPos < nums.size(); currentPos++) {
            int exclude = prev1;
            int include = prev2 + nums.get(currentPos);
            int maxSum = Math.max(include, exclude);
            prev2 = prev1;
            prev1 = maxSum;
        }
        return prev1;
    }


    public static void main(String[] args) {

        ArrayList<Integer> numbers = Lists.newArrayList(1, 2, 3, 1, 3, 5, 8, 1, 9);

        System.out.println(maximumNonAdjacentSum(numbers));


        ArrayList<Integer> numbers2 = Lists.newArrayList(9, 9, 8, 2);
        System.out.println(maximumNonAdjacentSum(numbers2));

        ArrayList<Integer> numbers3 = Lists.newArrayList(4, 8);
        System.out.println(maximumNonAdjacentSum(numbers3));

    }

}

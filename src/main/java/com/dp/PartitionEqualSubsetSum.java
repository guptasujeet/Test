package com.dp;

import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/subset-sum-problem2014/1
//https://www.youtube.com/watch?v=UGY7FMHt-M8&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=22

//todo : change to DP with memoization
public class PartitionEqualSubsetSum {


    public boolean partitionEqualSubsetSum(int[] numbers) {

        int sum = Arrays.stream(numbers).sum();
        //can't partition odd number into equal subset
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;

        return isTargetPresent(numbers, 0, target);

    }

    private boolean isTargetPresent(int[] numbers, int start, int target) {
        if (target == 0) {
            return true;
        }

        if (start >= numbers.length) {
            return false;
        }

        int number = numbers[start];

        if (target >= number) {
            return isTargetPresent(numbers, start + 1, target - number);
        }
        return isTargetPresent(numbers, start + 1, target);
    }


    public static void main(String[] args) {
        PartitionEqualSubsetSum subsetSum = new PartitionEqualSubsetSum();


        System.out.println(subsetSum.partitionEqualSubsetSum(new int[]{1, 5, 11, 5})); // true
        System.out.println(subsetSum.partitionEqualSubsetSum(new int[]{1, 3, 5})); //false

        System.out.println(subsetSum.isTargetPresent(new int[]{3, 34, 4, 12, 5, 2}, 0, 9)); //true
        System.out.println(subsetSum.isTargetPresent(new int[]{3, 34, 4, 12, 5, 2}, 0, 30)); //false
    }
}

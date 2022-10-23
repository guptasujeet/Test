package com.dp;

import java.util.Arrays;

public class MinSwapToMakeSequenceIncreasing {

    public int minSwap(int[] nums1, int[] nums2) {
        int[][] memory = new int[nums1.length + 1][2];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        int[] num1New = new int[nums1.length + 1];
        int[] num2New = new int[nums2.length + 1];
        num1New[0] = -1;
        num2New[0] = -1;
        System.arraycopy(nums1, 0, num1New, 1, nums1.length);
        System.arraycopy(nums2, 0, num2New, 1, nums2.length);
        return calculateMinSwap(num1New, num2New, 1, 0, memory);
    }


    public int calculateMinSwap(int[] nums1, int[] nums2, int index, int prevElementSwapped, int[][] memory) {

        if (index >= nums1.length) {
            return 0;
        }

        if (memory[index][prevElementSwapped] != -1) {
            return memory[index][prevElementSwapped];
        }

        int prev1 = nums1[index - 1];
        int prev2 = nums2[index - 1];
        //swapping comparision values here as in actual there were no swaps done

        if (prevElementSwapped == 1) {
            prev1 = nums2[index - 1];
            prev2 = nums1[index - 1];
        }

        //no swap
        int noSwapAns = Integer.MAX_VALUE;
        if (nums1[index] > prev1 && nums2[index] > prev2) {
            noSwapAns = calculateMinSwap(nums1, nums2, index + 1, 0, memory);
        }

        int swapAns = Integer.MAX_VALUE;
        if (nums1[index] > prev2 && nums2[index] > prev1) {
            swapAns = 1 + calculateMinSwap(nums1, nums2, index + 1, 1, memory);
        }
        memory[index][prevElementSwapped] = Math.min(noSwapAns, swapAns);
        return memory[index][prevElementSwapped];
    }

    public static void main(String[] args) {

        MinSwapToMakeSequenceIncreasing minSwap = new MinSwapToMakeSequenceIncreasing();

        System.out.println(minSwap.minSwap(new int[]{0, 7, 8, 10, 10, 11, 12, 13, 19, 18}, new int[]{4, 4, 5, 7, 11, 14, 15, 16, 17, 20}));//4

        System.out.println(minSwap.minSwap(new int[]{0, 4, 4, 5, 9}, new int[]{0, 2, 6, 8, 10}));//1
        System.out.println(minSwap.minSwap(new int[]{1, 3, 5, 4}, new int[]{1, 2, 3, 7}));//1
    }

}

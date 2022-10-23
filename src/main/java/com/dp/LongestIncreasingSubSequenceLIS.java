package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=MYHajVcnXSA&t=391s
//https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1
public class LongestIncreasingSubSequenceLIS {


    public static int longestSubSequence(int[] data) {
        if (data.length == 0) {
            return 0;
        }
        if (data.length == 1) {
            return data[0];
        }

        int[][] memory = new int[data.length + 1][data.length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculateLongestSubSequence(data, 0, -1, memory);
    }

    private static int calculateLongestSubSequence(int[] data, int currentIndex, int prevIndex, int[][] memory) {

        if (currentIndex == data.length) {
            return 0;
        }

        if (memory[currentIndex][prevIndex + 1] != -1) {
            return memory[currentIndex][prevIndex + 1];
        }


        int include = Integer.MIN_VALUE;
        if (prevIndex == -1 || data[currentIndex] > data[prevIndex]) {
            include = 1 + calculateLongestSubSequence(data, currentIndex + 1, currentIndex, memory);
        }
        int exclude = calculateLongestSubSequence(data, currentIndex + 1, prevIndex, memory);

        memory[currentIndex][prevIndex + 1] = Math.max(include, exclude);
        return memory[currentIndex][prevIndex + 1];
    }

    private static int longestIncreasingSubSequenceBinarySearch(int[] data) {
        int[] memory = new int[data.length];

        int len = 0;
        for (int item : data) {
            int index = Arrays.binarySearch(memory, 0, len, item);
            if (index < 0) {
                index = -(index + 1);
            }
            memory[index] = item;
            if (index == len) {
                len++;
            }
        }
        return len;
    }


    public static void main(String[] args) {
        System.out.println(longestSubSequence(new int[]{5, 8, 3, 7, 9, 1})); // 3
        System.out.println(longestIncreasingSubSequenceBinarySearch(new int[]{5, 8, 3, 7, 9, 1})); // 3
        System.out.println(longestSubSequence(new int[]{5, 8, 3, 7, -1, 1})); // 2
        System.out.println(longestIncreasingSubSequenceBinarySearch(new int[]{5, 8, 3, 7, -1, 1})); // 2
        System.out.println(longestSubSequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15})); // 6
        System.out.println(longestIncreasingSubSequenceBinarySearch(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15})); // 6
    }
}

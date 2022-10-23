package com.dp;


//https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/
//https://www.youtube.com/watch?v=IOOFHFXenQU

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubSequenceGivenDIff {


    public int longestSubsequence(int[] arr, int givenDiff) {

        Map<Integer, Integer> memory = new HashMap<>();

        int ans = 0;
        for (int num : arr) {
            int prevNumToCheck = num - givenDiff;
            int prevAns = memory.getOrDefault(prevNumToCheck, 0);
            memory.put(num, 1 + prevAns);
            ans = Math.max(ans, memory.get(num));
        }
        return ans;
    }


    public static void main(String[] args) {
        LongestArithmeticSubSequenceGivenDIff subSeq = new LongestArithmeticSubSequenceGivenDIff();

        System.out.println(subSeq.longestSubsequence(new int[]{1, 2, 3, 4}, 1)); //4
        System.out.println(subSeq.longestSubsequence(new int[]{1, 3, 5, 7}, 1)); //1
        System.out.println(subSeq.longestSubsequence(new int[]{1, 3, 2, 7}, 1)); //2
    }
}

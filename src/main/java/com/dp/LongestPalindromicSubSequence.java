package com.dp;

import java.util.Arrays;

//https://leetcode.com/problems/longest-palindromic-subsequence/
//not this is subsequnce problem not SUB STRING
public class LongestPalindromicSubSequence {


    public int longestPalindromeSubseq(String text1) {
        int[][] memory = new int[text1.length()][text1.length()];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return computePalindromic(text1, text1, 0, text1.length() - 1, memory);
    }

    private int computePalindromic(String text1, String text2, int text1Index, int text2Index, int[][] memory) {

        if (text1Index >= text1.length()) {
            return 0;
        }

        if (text2Index < 0) {
            return 0;
        }

        if (memory[text1Index][text2Index] != -1) {
            return memory[text1Index][text2Index];
        }

        int ans;
        if (text1.charAt(text1Index) == text2.charAt(text2Index)) {
            ans = 1 + computePalindromic(text1, text2, text1Index + 1, text2Index - 1, memory);
        } else {
            int text1IncAns = computePalindromic(text1, text2, text1Index + 1, text2Index, memory);
            int text2IncAns = computePalindromic(text1, text2, text1Index, text2Index - 1, memory);
            ans = Math.max(text1IncAns, text2IncAns);
        }
        memory[text1Index][text2Index] = ans;
        return ans;
    }

    public static void main(String[] args) {

        LongestPalindromicSubSequence subSequence = new LongestPalindromicSubSequence();

        System.out.println(subSequence.longestPalindromeSubseq("bbbab")); //4   ie. bbbb
        System.out.println(subSequence.longestPalindromeSubseq("cbbd")); //2    bb

    }

}

package com.dp;


import java.util.Arrays;

//https://leetcode.com/problems/longest-common-subsequence/
//https://www.youtube.com/watch?v=y1b8pObvndA
public class LongestCommonSubSequenceString {

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] memory = new int[text1.length()][text2.length()];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return computeLCS(text1, text2, 0, 0, memory);
    }

    private int computeLCS(String text1, String text2, int text1Index, int text2Index, int[][] memory) {

        if (text1Index >= text1.length() || text2Index >= text2.length()) {
            return 0;
        }

        if (memory[text1Index][text2Index] != -1) {
            return memory[text1Index][text2Index];
        }

        int ans;
        if (text1.charAt(text1Index) == text2.charAt(text2Index)) {
            ans = 1 + computeLCS(text1, text2, text1Index + 1, text2Index + 1, memory);
        } else {
            int text1IncAns = computeLCS(text1, text2, text1Index + 1, text2Index, memory);
            int text2IncAns = computeLCS(text1, text2, text1Index, text2Index + 1, memory);
            ans = Math.max(text1IncAns, text2IncAns);
        }
        memory[text1Index][text2Index] = ans;
        return ans;
    }


    public static void main(String[] args) {

        LongestCommonSubSequenceString lcsString = new LongestCommonSubSequenceString();
        System.out.println(lcsString.longestCommonSubsequence("abcde", "ace")); // 3
        System.out.println(lcsString.longestCommonSubsequence("abc", "abc")); // 3
        System.out.println(lcsString.longestCommonSubsequence("abc", "def")); // 0

    }

}

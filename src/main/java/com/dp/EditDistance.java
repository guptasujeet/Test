package com.dp;

import java.util.Arrays;

//https://leetcode.com/problems/edit-distance/
//https://www.youtube.com/watch?v=8HEjwf28LyE
public class EditDistance {


    public int minDistance(String word1, String word2) {

        int[][] memory = new int[word1.length()][word2.length()];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculateMinDistance(word1, word2, 0, 0, memory);

    }

    private int calculateMinDistance(String word1, String word2, int w1Index, int w2Index, int[][] memory) {

        if (w1Index == word1.length()) {
            return word2.length() - w2Index;
        }

        if (w2Index == word2.length()) {
            return word1.length() - w1Index;
        }

        if (memory[w1Index][w2Index] != -1) {
            return memory[w1Index][w2Index];
        }


        int minAns;

        if (word1.charAt(w1Index) == word2.charAt(w2Index)) {
            return calculateMinDistance(word1, word2, w1Index + 1, w2Index + 1, memory);
        } else {
            int insertAns = 1 + calculateMinDistance(word1, word2, w1Index, w2Index + 1, memory);

            int deleteAns = 1 + calculateMinDistance(word1, word2, w1Index + 1, w2Index, memory);

            int replaceAns = 1 + calculateMinDistance(word1, word2, w1Index + 1, w2Index + 1, memory);

            minAns = Math.min(insertAns, Math.min(deleteAns, replaceAns));
        }

        memory[w1Index][w2Index] = minAns;

        return minAns;
    }


    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.minDistance("horse", "ros")); // 3
        System.out.println(editDistance.minDistance("intention", "execution")); //5
    }


}

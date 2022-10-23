package com.problem;

import java.util.Arrays;

public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {

        String input = "abgbbbacccdddddefffffg";
        //String input = "abgbbbacccdddddeffefffg";


        char output = getFirstNonRepeatingChar(input);
        System.out.println("Output is -> " + output);

        System.out.println("Output is -> " + getFirstNonRepeatingChar("aabc"));

    }

    private static char getFirstNonRepeatingChar(String input) {
        int[] symbolCount = new int[26];
        Arrays.fill(symbolCount, -1);

        char[] chars = input.toCharArray();

        for (char aChar : chars) {
            int location = aChar - 'a';
            symbolCount[location] = symbolCount[location] + 1;
        }

        for (int i = 0; i < symbolCount.length; i++) {
            int count = symbolCount[i];
            if (count == 0) {
                return (char) ('a' + i);
            }
        }
        return '-';
    }

}

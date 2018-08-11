package com.problem;


public class ShortestPalindrome {


    public static void main(String[] args) {
        String input = "abcda";
        //String input = "abab";
        String output = shortestPalindrome(input);

        System.out.println(output);
    }


    private static String shortestPalindrome(String input) {

        if (input.length() == 1 || input.length() == 0) {
            return input;
        }

        int start = 0;
        int end = input.length() - 1;

        while (end >= 0) {
            if (input.charAt(start) == input.charAt(end)) {
                start++;
            }
            end--;
        }

        if (start == input.length()) {
            return input;
        }

        String suffix = input.substring(start);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String mid = shortestPalindrome(input.substring(0, start));
        return prefix + mid + suffix;
    }
}

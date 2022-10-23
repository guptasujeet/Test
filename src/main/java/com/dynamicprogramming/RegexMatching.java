package com.dynamicprogramming;

public class RegexMatching {


    public static boolean isRegexMatching(String regex, String matcher) {
        boolean[][] compute = new boolean[matcher.length() + 1][regex.length() + 1];
        compute[0][0] = true;
        if (regex.charAt(0) == '*') {
            compute[0][1] = true;
        }

        for (int i = 1; i < compute.length; i++) {
            char matcherChar = matcher.charAt(i - 1);
            for (int j = 1; j < compute[0].length; j++) {
                char regexChar = regex.charAt(j - 1);
                if (regexChar == matcherChar || regexChar == '.') {
                    compute[i][j] = compute[i - 1][j - 1];
                } else if (regexChar == '*') {
                    boolean prevCompute = compute[i][j - 2];
                    if (prevCompute) {
                        compute[i][j] = true;
                    } else if (matcherChar == regex.charAt(j - 2)) {
                        compute[i][j] = compute[i - 1][j];
                    } else {
                        compute[i][j] = false;
                    }
                }
            }
        }
        return compute[matcher.length()][regex.length()];
    }

    public static void main(String[] args) {
        System.out.println(isRegexMatching("xa*b.c", "xaaaaaaabyc"));
        System.out.println(isRegexMatching("xa***a*****b.c", "xaaaaaaabyc"));
        System.out.println(isRegexMatching("xa*b.c", "xaabyce"));
        System.out.println(isRegexMatching("xa*b.c", "xaabycd"));
    }

}

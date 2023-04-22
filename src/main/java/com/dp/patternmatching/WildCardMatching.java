package com.dp.patternmatching;

public class WildCardMatching {


    public static boolean isMatching(String regex, String matcher) {
        int regexLength = regex.length();
        int matcherLength = matcher.length();
        boolean[][] calcGroup = new boolean[matcherLength + 1][regexLength + 1];

        calcGroup[0][0] = true;

        if (regex.charAt(0) == '*') {
            calcGroup[0][1] = true;
        }

        for (int i = 1; i < calcGroup.length; i++) {
            char m = matcher.charAt(i - 1);
            for (int j = 1; j < calcGroup[0].length; j++) {
                char r = regex.charAt(j - 1);
                if (m == r || r == '?') {
                    calcGroup[i][j] = calcGroup[i - 1][j - 1];
                } else if (r == '*') {
                    calcGroup[i][j] = calcGroup[i][j - 1] || calcGroup[i - 1][j];
                } else {
                    calcGroup[i][j] = false;
                }
            }
        }
        return calcGroup[calcGroup.length - 1][calcGroup[0].length - 1];
    }

    public static boolean isMatchingSlow(String pattern, String matcher) {
        return isMatchingRecursive(pattern, matcher, 0, 0);
    }

    private static boolean isMatchingRecursive(String pattern, String matcher, int matcherPos, int patternPos) {
        if (patternPos == pattern.length()) {
            return matcherPos == matcher.length();
        }

        if (pattern.charAt(patternPos) == '*') {
            matcherPos--; // going one position last
            while (matcherPos < matcher.length()) {
                if (isMatchingRecursive(pattern, matcher, ++matcherPos, patternPos + 1)) {
                    return true;
                }
            }
        } else {
            if (matcherPos < matcher.length()) {
                char matcherChar = matcher.charAt(matcherPos);
                char patternChar = pattern.charAt(patternPos);
                if (matcherChar == patternChar || patternChar == '?') {
                    return isMatchingRecursive(pattern, matcher, matcherPos + 1, patternPos + 1);
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isMatchingSlow("a?y*z", "ayyzzb")); //false
        System.out.println(isMatching("a?y*z", "ayz")); // false
        System.out.println(isMatching("a?y*z", "ayyz")); // true
        System.out.println(isMatching("a?y*z", "ayyzz")); // true
        System.out.println(isMatching("a?y*z", "ayyzzzzb")); // false
        System.out.println(isMatching("a?y*z", "ayyzzzz")); // true
        System.out.println(isMatching("a?y*z", "ayyyyzzzz")); // true
    }

}

package com.dp;

import java.util.LinkedList;

public class WildCardMatching {

    public boolean isMatch0BasedIndexing(String text, String pattern) {
        //pattern = getCompiledPattern(pattern);
        Boolean[][] memory = new Boolean[text.length()][pattern.length()];
        return checkMatch(text, pattern, text.length() - 1, pattern.length() - 1, memory);
    }

    //public boolean isMatchOneBasedIndexing(String text, String pattern) {
    public boolean isMatch(String text, String pattern) {
        //pattern = getCompiledPattern(pattern);
        Boolean[][] memory = new Boolean[text.length() + 1][pattern.length() + 1];
        return checkMatchOneBasedIndexing(text, pattern, text.length(), pattern.length(), memory);
    }

    private boolean checkMatchOneBasedIndexing(String text, String pattern, int textPos, int patternPos, Boolean[][] memory) {
        if (textPos == 0 && patternPos == 0) {
            return true;
        }
        if (textPos > 0 && patternPos == 0) {
            return false;
        }
        if (textPos == 0 && patternPos > 0) {
            for (int i = 1; i <= patternPos; i++) {
                if (pattern.charAt(i - 1) != '*') {
                    return false;
                }
            }
            return true;
            //or if compiledPattern is used
            //return pattern.charAt(0) == '*';
        }

        /*if (textPos < 0) {
            return false;
        }*/

        if (memory[textPos][patternPos] != null) {
            return memory[textPos][patternPos];
        }

        char patternChar = pattern.charAt(patternPos - 1);

        boolean matching = false;
        if (text.charAt(textPos - 1) == patternChar || '?' == patternChar) {
            matching = checkMatchOneBasedIndexing(text, pattern, textPos - 1, patternPos - 1, memory);
        }
        if ('*' == patternChar) {
            //empty string OR *with current char
            matching = checkMatchOneBasedIndexing(text, pattern, textPos, patternPos - 1, memory) || checkMatchOneBasedIndexing(text, pattern, textPos - 1, patternPos, memory);
        }
        memory[textPos][patternPos] = matching;
        return matching;
    }

    private String getCompiledPattern(String pattern) {
        if (pattern.length() <= 1) {
            return pattern;
        }

        LinkedList<Character> stack = new LinkedList<>();
        stack.push(pattern.charAt(0));
        for (int i = 1; i < pattern.length(); i++) {
            Character pChar = pattern.charAt(i);
            Character peek = stack.peek();
            if (peek == pChar && pChar == '*') {
                continue;
            } else {
                stack.push(pChar);
            }
        }
        Character[] characters = stack.toArray(new Character[0]);
        StringBuilder newPattern = new StringBuilder();
        for (int i = characters.length - 1; i >= 0; i--) {
            newPattern.append(characters[i]);
        }
        return newPattern.toString();
    }

    private boolean checkMatch(String text, String pattern, int textPos, int patternPos, Boolean[][] memory) {
        if (textPos < 0 && patternPos < 0) {
            return true;
        }
        if (textPos >= 0 && patternPos < 0) {
            return false;
        }
        if (textPos < 0 && patternPos >= 0) {
            for (int i = 0; i <= patternPos; i++) {
                if (pattern.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
            //or if compiledPattern is used
            //return pattern.charAt(0) == '*';
        }

        /*if (textPos < 0) {
            return false;
        }*/

        if (memory[textPos][patternPos] != null) {
            return memory[textPos][patternPos];
        }

        char patternChar = pattern.charAt(patternPos);

        boolean matching = false;
        if (text.charAt(textPos) == patternChar || '?' == patternChar) {
            matching = checkMatch(text, pattern, textPos - 1, patternPos - 1, memory);
        }
        if ('*' == patternChar) {
            //empty string OR *with current char
            matching = checkMatch(text, pattern, textPos, patternPos - 1, memory) || checkMatch(text, pattern, textPos - 1, patternPos, memory);
        }
        memory[textPos][patternPos] = matching;
        return matching;
    }


    private boolean isMatchTab(String text, String pattern) {
        boolean[][] memory = new boolean[text.length() + 1][pattern.length() + 1];
        memory[0][0] = true;

        for (int patternPos = 1; patternPos <= pattern.length(); patternPos++) {
            boolean isMatching = true;
            for (int i = 1; i <= patternPos; i++) {
                if (pattern.charAt(i - 1) != '*') {
                    isMatching = false;
                    break;
                }
            }
            if (isMatching) {
                memory[0][patternPos] = true;
            }
        }

        for (int textPos = 1; textPos <= text.length(); textPos++) {
            for (int patternPos = 1; patternPos <= pattern.length(); patternPos++) {
                memory[textPos][0] = false;
                char patternChar = pattern.charAt(patternPos - 1);
                boolean matching = false;
                if (text.charAt(textPos - 1) == patternChar || '?' == patternChar) {
                    matching = memory[textPos - 1][patternPos - 1];
                }
                if ('*' == patternChar) {
                    //empty string OR *with current char
                    matching = memory[textPos][patternPos - 1] || memory[textPos - 1][patternPos];
                }
                memory[textPos][patternPos] = matching;
            }
        }

        return memory[text.length()][pattern.length()];
    }

    private boolean isMatchTabOptimized(String text, String pattern) {

        boolean[] previous = new boolean[pattern.length() + 1];
        boolean[] current = new boolean[pattern.length() + 1];

        previous[0] = true;

        for (int patternPos = 1; patternPos <= pattern.length(); patternPos++) {
            boolean isMatching = true;
            for (int i = 1; i <= patternPos; i++) {
                if (pattern.charAt(i - 1) != '*') {
                    isMatching = false;
                    break;
                }
            }
            if (isMatching) {
                previous[patternPos] = true;
            }
        }

        for (int textPos = 1; textPos <= text.length(); textPos++) {
            for (int patternPos = 1; patternPos <= pattern.length(); patternPos++) {
                current[0] = false;
                char patternChar = pattern.charAt(patternPos - 1);
                boolean matching = false;
                if (text.charAt(textPos - 1) == patternChar || '?' == patternChar) {
                    matching = previous[patternPos - 1];
                }
                if ('*' == patternChar) {
                    //empty string OR *with current char
                    matching = current[patternPos - 1] || previous[patternPos];
                }
                current[patternPos] = matching;
            }
            boolean[] temp = current;
            current = previous;
            previous = temp;
        }

        return previous[pattern.length()];
    }


    public static void main(String[] args) {
        WildCardMatching wildCardMatching = new WildCardMatching();

        System.out.println(wildCardMatching.isMatch("", "**********")); // true
        System.out.println(wildCardMatching.isMatchTab("", "**********")); // true
        System.out.println(wildCardMatching.isMatchTabOptimized("", "**********")); // true
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("zacabz", "*a?b*")); // false
        System.out.println(wildCardMatching.isMatchTab("zacabz", "*a?b*")); // false
        System.out.println(wildCardMatching.isMatchTabOptimized("zacabz", "*a?b*")); // false
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("aab", "c*a*b")); // false
        System.out.println(wildCardMatching.isMatchTab("aab", "c*a*b")); // false
        System.out.println(wildCardMatching.isMatchTabOptimized("aab", "c*a*b")); // false
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("adceb", "*a*b")); // true
        System.out.println(wildCardMatching.isMatchTab("adceb", "*a*b")); // true
        System.out.println(wildCardMatching.isMatchTabOptimized("adceb", "*a*b")); // true
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("aa", "a")); // false
        System.out.println(wildCardMatching.isMatchTab("aa", "a")); // false
        System.out.println(wildCardMatching.isMatchTabOptimized("aa", "a")); // false
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("aa", "*")); // true
        System.out.println(wildCardMatching.isMatchTab("aa", "*")); // true
        System.out.println(wildCardMatching.isMatchTabOptimized("aa", "*")); // true
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("ayyzzb", "a?y*z")); //false
        System.out.println(wildCardMatching.isMatchTab("ayyzzb", "a?y*z")); //false
        System.out.println(wildCardMatching.isMatchTabOptimized("ayyzzb", "a?y*z")); //false
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("ayz", "a?y*z")); // false
        System.out.println(wildCardMatching.isMatchTab("ayz", "a?y*z")); // false
        System.out.println(wildCardMatching.isMatchTabOptimized("ayz", "a?y*z")); // false
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("ayyz", "a?y*z")); // true
        System.out.println(wildCardMatching.isMatchTab("ayyz", "a?y*z")); // true
        System.out.println(wildCardMatching.isMatchTabOptimized("ayyz", "a?y*z")); // true
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("ayyzz", "a?y*z")); // true
        System.out.println(wildCardMatching.isMatchTab("ayyzz", "a?y*z")); // true
        System.out.println(wildCardMatching.isMatchTabOptimized("ayyzz", "a?y*z")); // true
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("ayyzzzzb", "a?y*z")); // false
        System.out.println(wildCardMatching.isMatchTab("ayyzzzzb", "a?y*z")); // false
        System.out.println(wildCardMatching.isMatchTabOptimized("ayyzzzzb", "a?y*z")); // false
        System.out.println("--------");
        System.out.println(wildCardMatching.isMatch("ayyzzzz", "a?y*z")); // true
        System.out.println(wildCardMatching.isMatchTab("ayyzzzz", "a?y*z")); // true
        System.out.println(wildCardMatching.isMatchTabOptimized("ayyzzzz", "a?y*z")); // true
        System.out.println("--------");

    }

}

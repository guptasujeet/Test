package com.problem;


//https://www.interviewbit.com/problems/next-smallest-palindrome/
public class NextPalindrome {
    public String solve(String str) {

        int length = str.length();
        if (length == 1) {
            return str;
        }

        //1st case starts with 9
        if (str.charAt(0) == '9') {
            long nextNum = 10 ^ length + 1;
            return String.valueOf(nextNum);
        }

        //even length
        if (length % 2 == 0) {
            int mid = length / 2 - 1;
            StringBuilder strBuilder = new StringBuilder(str);
            char nextIncChar = String.valueOf(Integer.parseInt("" + str.charAt(mid)) + 1).charAt(0);
            strBuilder.setCharAt(mid, nextIncChar);
            for (int i = mid, j = mid + 1; i >= 0 && j < length; i--, j++) {
                strBuilder.setCharAt(j, strBuilder.charAt(i));
            }
            return strBuilder.toString();
        } else {
            //odd length
            int mid = length / 2;
            StringBuilder strBuilder = new StringBuilder(str);
            char nextIncChar = String.valueOf(Integer.parseInt("" + str.charAt(mid)) + 1).charAt(0);
            strBuilder.setCharAt(mid, nextIncChar);
            for (int i = mid - 1, j = mid + 1; i >= 0 && j < length; i--, j++) {
                strBuilder.setCharAt(j, strBuilder.charAt(i));
            }
            return strBuilder.toString();
        }

    }

    public static void main(String[] args) {
        NextPalindrome nextPalindrome = new NextPalindrome();

        System.out.println(nextPalindrome.solve("74")); //88
        System.out.println(nextPalindrome.solve("110")); //12s1
        System.out.println(nextPalindrome.solve("111")); //121
    }


}


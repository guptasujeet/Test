package com.ds.queue;

//https://practice.geeksforgeeks.org/problems/first-non-repeating-character-in-a-stream1216/1
public class FirstNonRepeatingCharacterInAStream {


    private String firstNonRepeatingChar(String stream) {

        char[] ans = new char[stream.length()];
        ans[0] = stream.charAt(0);
        for (int i = 1; i < stream.length(); i++) {
            char prevCharAns = ans[i - 1];

            char currentChar = stream.charAt(i);

            if (currentChar == prevCharAns) {
                ans[i] = '#';
            } else if (prevCharAns == '#') {
                ans[i] = currentChar;
            } else {
                ans[i] = prevCharAns;
            }
        }

        return new String(ans);

    }


    public static void main(String[] args) {

        FirstNonRepeatingCharacterInAStream nonRepeat = new FirstNonRepeatingCharacterInAStream();

        System.out.println(nonRepeat.firstNonRepeatingChar("aabc")); //a#bb
        System.out.println(nonRepeat.firstNonRepeatingChar("zz")); //z#
        System.out.println(nonRepeat.firstNonRepeatingChar("abcdef")); //aaaaaa
    }

}

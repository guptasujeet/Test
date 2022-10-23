package com.dp;

//https://www.interviewbit.com/problems/interleaving-strings/


//NOT WORKING PROPERLY
public class InterleavingString {


    public int isInterleave(String source1, String source2, String destination) {

        int a = source1.length();
        int b = source2.length();
        int c = destination.length();

        if (a + b == c) {
            boolean ans = solve(a - 1, b - 1, c - 1, source1, source2, destination);
            return ans ? 1 : 0;
        } else {
            return 0;
        }

    }

    private boolean solve(int s1Index, int s2Index, int destIndex, String source1, String source2, String destination) {
        if (s1Index < 0 && s2Index < 0 && destIndex < 0)
            return true;
        if ((s1Index < 0 && s2Index < 0) && destIndex > 0)
            return false;


        if (destIndex != -1) {
            if (s1Index != -1 && (source1.charAt(s1Index) == destination.charAt(destIndex))) {
                return solve(s1Index - 1, s2Index, destIndex - 1, source1, source2, destination);
            } else if (s2Index != -1 && source2.charAt(s2Index) == destination.charAt(destIndex)) {
                return solve(s1Index, s2Index - 1, destIndex - 1, source1, source2, destination);
            } else if (s1Index != -1 && s2Index != -1 && source1.charAt(s1Index) != destination.charAt(destIndex) && source2.charAt(s2Index) != destination.charAt(destIndex)) {
                return false;
            }
        }


        return false;
    }


    public static void main(String[] args) {
        InterleavingString interleaving = new InterleavingString();


        System.out.println(interleaving.isInterleave("aabcc", "dbbca", "aadbbcbcac")); //true //1
        System.out.println(interleaving.isInterleave("aabcc", "dbbca", "aadbbbaccc")); //false //0
    }

}

package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=NW-BLDQHFXk
//https://www.codingninjas.com/codestudio/problems/count-derangements_873861
public class CountDerangements {


    public static long countDerangements(int n) {
        // Write your code here.
        long[] memory = new long[n + 1];
        Arrays.fill(memory, -1);
        memory[0] = 0;
        memory[1] = 0;
        memory[2] = 1;
        return countDerangementsMem(n, memory) % 1000000007;
    }

    private static long countDerangementsMem(int n, long[] memory) {
        if (memory[n] != -1) {
            return memory[n];
        }

        //element can be placed into n-1 box * sub problem
        //basically (n-1) * f (n -1 ) +     (n-1) * f(n-2)
        //i.e. (n-1) * (    f(n-1   + f (n-2)   )
        long ways = (n - 1) * (countDerangementsMem(n - 2, memory) + countDerangementsMem(n - 1, memory));

        memory[n] = ways;
        return memory[n];
    }


    public static void main(String[] args) {
        System.out.println(countDerangements(3));
        System.out.println(countDerangements(4));
        System.out.println(countDerangements(5));
    }

}

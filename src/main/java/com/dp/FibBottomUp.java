package com.dp;

import java.util.Arrays;

public class FibBottomUp {


    //rec
    static int computeFibRecNoDP(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        return computeFibRecNoDP(n - 1) + computeFibRecNoDP(n - 2);
    }

    static int computeFibRecWithDP(int n) {
        int[] memory = new int[n + 1];
        Arrays.fill(memory, -1);
        return computeFibTopDownRecWithDP(n, memory);
    }

    static int computeFibTopDownRecWithDP(int n, int[] memory) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (memory[n] != -1) {
            return memory[n];
        }

        memory[n] = computeFibTopDownRecWithDP(n - 1, memory) + computeFibTopDownRecWithDP(n - 2, memory);
        return memory[n];
    }

    static int computeFibBottomUp(int n) {
        n = n + 1;
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib[n];
    }

    static int computeFibBottomUpSpaceOptimized(int n) {
        n = n + 1;
        int prev2 = 0;
        int prev1 = 1;

        int current = 0;

        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }


    public static void main(String[] args) {

        System.out.println(computeFibBottomUpSpaceOptimized(43));
        System.out.println("-----------");

        System.out.println(computeFibBottomUp(43));
        System.out.println("-----------");

        System.out.println(computeFibRecWithDP(43));
        System.out.println("-----------");
        //huge time taken by below
        System.out.println(computeFibRecNoDP(43));
    }

}

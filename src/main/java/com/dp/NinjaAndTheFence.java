package com.dp;

//https://www.codingninjas.com/codestudio/problems/ninja-and-the-fence_3210208
//https://www.youtube.com/watch?v=5eFh5CC-8KY&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=9

import java.util.Arrays;

public class NinjaAndTheFence {

    public static final int MOD = 1000000007;

    public static int numberOfWays(int blocks, int colors) {
        // Write your code here.
        long[] memory = new long[blocks + 1];
        Arrays.fill(memory, -1);
        return (int) (solve(blocks, colors, memory) % MOD);
    }

    private static long add(long a, long b) {
        return ((a % MOD) + (b % MOD)) % MOD;
    }

    private static long multiply(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    private static long solve(int blocks, int colors, long[] memory) {
        if (blocks == 1) {
            return colors;
        }

        if (blocks == 2) {
            return multiply(colors, colors);
            //return add(colors, multiply(colors, colors - 1));
        }

        if (memory[blocks] != -1) {
            return memory[blocks];
        }

        long same = multiply(solve(blocks - 2, colors, memory), colors - 1);
        long diff = multiply(solve(blocks - 1, colors, memory), colors - 1);

        memory[blocks] = add(same, diff);
        return memory[blocks];
    }


    public static void main(String[] args) {
        System.out.println(numberOfWays(1, 2)); // 1
        System.out.println(numberOfWays(3, 2)); // 6
        System.out.println(numberOfWays(2, 4)); // 16
        System.out.println(numberOfWays(4, 2)); // 10
        System.out.println(numberOfWays(74, 23)); // 583326649
    }
}

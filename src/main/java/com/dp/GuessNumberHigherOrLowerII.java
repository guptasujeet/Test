package com.dp;

import java.util.Arrays;

//https://leetcode.com/problems/guess-number-higher-or-lower-ii/
//https://www.youtube.com/watch?v=x--bMzT1Xhk
public class GuessNumberHigherOrLowerII {


    public int getMoneyAmount(int n) {
        int[][] memory = new int[n + 1][n + 1];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculate(1, n, memory);
    }


    private int calculate(int start, int end, int[][] memory) {
        if (end <= start) {
            return 0;
        }

        if (memory[start][end] != -1) {
            return memory[start][end];
        }

        int money = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int ans = i + Math.max(calculate(start, i - 1, memory), calculate(i + 1, end, memory));
            money = Math.min(ans, money);
        }
        memory[start][end] = money;
        return money;
    }

    public int getMoneyAmountTab(int n) {
        int[][] memory = new int[n + 2][n + 2];

        for (int start = n; start >= 1; start--) {
            for (int end = start; end <= n; end++) {
                if (start != end) {
                    int money = Integer.MAX_VALUE;
                    for (int i = start; i <= end; i++) {
                        int ans = i + Math.max(memory[start][i - 1], memory[i + 1][end]);
                        money = Math.min(ans, money);
                    }
                    memory[start][end] = money;
                }
            }
        }
        return memory[1][n];
    }


    public static void main(String[] args) {
        GuessNumberHigherOrLowerII guess = new GuessNumberHigherOrLowerII();
        System.out.println(guess.getMoneyAmount(10)); //16
        System.out.println(guess.getMoneyAmountTab(10)); //16

        System.out.println(guess.getMoneyAmount(1)); //0
        System.out.println(guess.getMoneyAmountTab(1)); //0

        System.out.println(guess.getMoneyAmount(2)); //1
        System.out.println(guess.getMoneyAmountTab(2)); //1
    }
}

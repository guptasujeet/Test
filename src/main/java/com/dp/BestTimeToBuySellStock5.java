package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=KFqhgQeQAXc
public class BestTimeToBuySellStock5 {

    public int maxProfit(int[] prices, int fee) {
        int[][] memory = new int[prices.length][2];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculateProfit(prices, fee, 0, 1, memory);
    }


    private int calculateProfit(int[] prices, int fee, int currentIndex, int buyAllowed, int[][] memory) {

        if (currentIndex >= prices.length) {
            return 0;
        }

        if (memory[currentIndex][buyAllowed] != -1) {
            return memory[currentIndex][buyAllowed];
        }

        int ignoreCurrentOpProfit = calculateProfit(prices, fee, currentIndex + 1, buyAllowed, memory);

        int profit = 0;
        if (buyAllowed == 1) {
            int buyProfit = -prices[currentIndex] + calculateProfit(prices, fee, currentIndex + 1, 0, memory);
            profit = Math.max(buyProfit, ignoreCurrentOpProfit);
        } else {
            int sellProfit = prices[currentIndex] - fee + calculateProfit(prices, fee, currentIndex + 1, 1, memory);
            profit = Math.max(sellProfit, ignoreCurrentOpProfit);
        }
        memory[currentIndex][buyAllowed] = profit;
        return profit;
    }


    public int maxProfitTab(int[] prices, int fee) {
        int[] next = new int[2];
        int[] current = new int[2];

        for (int currentIndex = prices.length - 1; currentIndex >= 0; currentIndex--) {
            for (int buyAllowed = 0; buyAllowed <= 1; buyAllowed++) {
                int ignoreCurrentOpProfit = next[buyAllowed];

                int profit = 0;
                if (buyAllowed == 1) {
                    int buyProfit = -prices[currentIndex] + next[0];
                    profit = Math.max(buyProfit, ignoreCurrentOpProfit);
                } else {
                    int sellProfit = prices[currentIndex] - fee + next[1];
                    profit = Math.max(sellProfit, ignoreCurrentOpProfit);
                }
                current[buyAllowed] = profit;
            }
            int[] temp = current;
            current = next;
            next = temp;
        }
        return next[1];
    }


    public static void main(String[] args) {

        BestTimeToBuySellStock5 buysSell = new BestTimeToBuySellStock5();

        System.out.println(buysSell.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8
        System.out.println(buysSell.maxProfitTab(new int[]{1, 3, 2, 8, 4, 9}, 2)); // 8

        System.out.println(buysSell.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6
        System.out.println(buysSell.maxProfitTab(new int[]{1, 3, 7, 5, 10, 3}, 3)); // 6

    }
}

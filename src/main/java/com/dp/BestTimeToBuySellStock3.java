package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=NW7XRzg3smo
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

// Find the maximum profit you can achieve. You may complete AT MOST TWO transactions.

public class BestTimeToBuySellStock3 {


    public int maxProfit(int[] prices) {
        int[][][] memory = new int[prices.length][2][4];

        for (int[][] ints : memory) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }

        return calculateMaxProfit(prices, 0, 1, 0, memory);
    }

    private int calculateMaxProfit(int[] prices, int currIndex, int buyAllowed, int txCount, int[][][] memory) {

        if (currIndex >= prices.length) {
            return 0;
        }

        if (txCount >= 4) {
            return 0;
        }

        if (memory[currIndex][buyAllowed][txCount] != -1) {
            return memory[currIndex][buyAllowed][txCount];
        }

        int ignore = calculateMaxProfit(prices, currIndex + 1, buyAllowed, txCount, memory);

        int profit;
        if (buyAllowed == 1) {
            int buy = -prices[currIndex] + calculateMaxProfit(prices, currIndex + 1, 0, txCount + 1, memory);
            profit = Math.max(buy, ignore);
        } else {
            int sell = prices[currIndex] + calculateMaxProfit(prices, currIndex + 1, 1, txCount + 1, memory);
            profit = Math.max(sell, ignore);
        }

        memory[currIndex][buyAllowed][txCount] = profit;

        return profit;
    }


    public int maxProfitTabSpaceOptimized(int[] prices) {
        int[][] next = new int[2][6];
        int[][] current = new int[2][6];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {
                for (int txCount = 1; txCount <= 4; txCount++) {
                    int ignore = next[buyAllowed][txCount];

                    int profit;
                    if (buyAllowed == 1) {
                        int buy = -prices[currIndex] + next[0][txCount + 1];
                        profit = Math.max(buy, ignore);
                    } else {
                        int sell = prices[currIndex] + next[1][txCount + 1];
                        profit = Math.max(sell, ignore);
                    }
                    current[buyAllowed][txCount] = profit;
                }
            }
            int[][] temp = current;
            current = next;
            next = temp;
        }

        return next[1][1];
    }

    //using only 2 tx
    public int maxProfitTabSpaceOptimizedFurther(int[] prices) {
        int[][] next = new int[2][3];
        int[][] current = new int[2][3];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {
                for (int limit = 2; limit > 0; limit--) {
                    int ignore = next[buyAllowed][limit];

                    int profit;
                    if (buyAllowed == 1) {
                        int buy = -prices[currIndex] + next[0][limit];
                        profit = Math.max(buy, ignore);
                    } else {
                        int sell = prices[currIndex] + next[1][limit - 1];
                        profit = Math.max(sell, ignore);
                    }
                    current[buyAllowed][limit] = profit;
                }
            }
            int[][] temp = current;
            current = next;
            next = temp;
        }

        return next[1][2];
    }

    public int maxProfitTab(int[] prices) {
        int[][][] memory = new int[prices.length + 1][2][6];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {
                for (int txCount = 1; txCount <= 4; txCount++) {
                    int ignore = memory[currIndex + 1][buyAllowed][txCount];

                    int profit;
                    if (buyAllowed == 1) {
                        int buy = -prices[currIndex] + memory[currIndex + 1][0][txCount + 1];
                        profit = Math.max(buy, ignore);
                    } else {
                        int sell = prices[currIndex] + memory[currIndex + 1][1][txCount + 1];
                        profit = Math.max(sell, ignore);
                    }

                    memory[currIndex][buyAllowed][txCount] = profit;
                }
            }
        }

        return memory[0][1][1];
    }


    public static void main(String[] args) {

        BestTimeToBuySellStock3 maxProfit = new BestTimeToBuySellStock3();
        System.out.println(maxProfit.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); //6
        System.out.println(maxProfit.maxProfitTab(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); //6
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); //6
        System.out.println(maxProfit.maxProfitTabSpaceOptimizedFurther(new int[]{3, 3, 5, 0, 0, 3, 1, 4})); //6

        System.out.println(maxProfit.maxProfit(new int[]{1, 2, 3, 4, 5})); //4
        System.out.println(maxProfit.maxProfitTab(new int[]{1, 2, 3, 4, 5})); //4
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(new int[]{1, 2, 3, 4, 5})); //4
        System.out.println(maxProfit.maxProfitTabSpaceOptimizedFurther(new int[]{1, 2, 3, 4, 5})); //4

        System.out.println(maxProfit.maxProfit(new int[]{7, 6, 4, 3, 1})); //0
        System.out.println(maxProfit.maxProfitTab(new int[]{7, 6, 4, 3, 1})); //0
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(new int[]{7, 6, 4, 3, 1})); //0
        System.out.println(maxProfit.maxProfitTabSpaceOptimizedFurther(new int[]{7, 6, 4, 3, 1})); //0


    }

}

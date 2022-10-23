package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=tuhjovVtDII
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
public class BestTimeToBuySellStock4 {


    public int maxProfit(int k, int[] prices) {
        int[][][] memory = new int[prices.length][2][2 * k];

        for (int[][] ints : memory) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }

        return calculateMaxProfit(k, prices, 0, 1, 0, memory);
    }

    private int calculateMaxProfit(int k, int[] prices, int currIndex, int buyAllowed, int txCount, int[][][] memory) {

        if (currIndex >= prices.length) {
            return 0;
        }

        if (txCount >= 2 * k) {
            return 0;
        }

        if (memory[currIndex][buyAllowed][txCount] != -1) {
            return memory[currIndex][buyAllowed][txCount];
        }

        int ignore = calculateMaxProfit(k, prices, currIndex + 1, buyAllowed, txCount, memory);

        int profit;
        if (buyAllowed == 1) {
            int buy = -prices[currIndex] + calculateMaxProfit(k, prices, currIndex + 1, 0, txCount + 1, memory);
            profit = Math.max(buy, ignore);
        } else {
            int sell = prices[currIndex] + calculateMaxProfit(k, prices, currIndex + 1, 1, txCount + 1, memory);
            profit = Math.max(sell, ignore);
        }

        memory[currIndex][buyAllowed][txCount] = profit;

        return profit;
    }


    public int maxProfitTabSpaceOptimized(int k, int[] prices) {

        if (k == 0) {
            return 0;
        }

        int[][] next = new int[2][6 * k];
        int[][] current = new int[2][6 * k];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {
                for (int txCount = 1; txCount <= 2 * k; txCount++) {
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
    public int maxProfitTabSpaceOptimizedFurther(int k, int[] prices) {

        if (k == 0) {
            return 0;
        }

        int[][] next = new int[2][k + 1];
        int[][] current = new int[2][k + 1];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {
                for (int limit = k; limit > 0; limit--) {
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

        return next[1][k];
    }

    public int maxProfitTab(int k, int[] prices) {
        int[][][] memory = new int[prices.length + 1][2][6 * k];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {
                for (int txCount = 1; txCount <= 2 * k; txCount++) {
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

        BestTimeToBuySellStock4 maxProfit = new BestTimeToBuySellStock4();
        System.out.println(maxProfit.maxProfit(2, new int[]{2, 4, 1})); //2
        System.out.println(maxProfit.maxProfitTab(2, new int[]{2, 4, 1})); //2
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(2, new int[]{2, 4, 1})); //2
        System.out.println(maxProfit.maxProfitTabSpaceOptimizedFurther(2, new int[]{2, 4, 1})); //2

        System.out.println(maxProfit.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); //7
        System.out.println(maxProfit.maxProfitTab(2, new int[]{3, 2, 6, 5, 0, 3})); //7
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(2, new int[]{3, 2, 6, 5, 0, 3})); //7
        System.out.println(maxProfit.maxProfitTabSpaceOptimizedFurther(2, new int[]{3, 2, 6, 5, 0, 3})); //7


    }

}

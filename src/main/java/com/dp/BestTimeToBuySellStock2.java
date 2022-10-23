package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=dlKGCNVel6A
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class BestTimeToBuySellStock2 {


    public int maxProfit(int[] prices) {
        int[][] memory = new int[prices.length][2];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculateMaxProfit(prices, 0, 1, memory);
    }

    private int calculateMaxProfit(int[] prices, int currIndex, int buyAllowed, int[][] memory) {

        if (currIndex >= prices.length) {
            return 0;
        }

        if (memory[currIndex][buyAllowed] != -1) {
            return memory[currIndex][buyAllowed];
        }

        int ignore = calculateMaxProfit(prices, currIndex + 1, buyAllowed, memory);

        int profit;
        if (buyAllowed == 1) {
            int buy = -prices[currIndex] + calculateMaxProfit(prices, currIndex + 1, 0, memory);
            profit = Math.max(buy, ignore);
        } else {
            int sell = prices[currIndex] + calculateMaxProfit(prices, currIndex + 1, 1, memory);
            profit = Math.max(sell, ignore);
        }

        memory[currIndex][buyAllowed] = profit;

        return profit;

    }


    public int maxProfitTab(int[] prices) {
        int[][] memory = new int[prices.length + 1][2];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {

                int ignore = memory[currIndex + 1][buyAllowed];

                int profit;
                if (buyAllowed == 1) {
                    int buy = -prices[currIndex] + memory[currIndex + 1][0];
                    profit = Math.max(buy, ignore);
                } else {
                    int sell = prices[currIndex] + memory[currIndex + 1][1];
                    profit = Math.max(sell, ignore);
                }

                memory[currIndex][buyAllowed] = profit;
            }
        }

        return memory[0][1];
    }

    public int maxProfitTabSpaceOptimized(int[] prices) {
        int[] next = new int[2];
        int[] curr = new int[2];

        for (int currIndex = prices.length - 1; currIndex >= 0; currIndex--) {
            for (int buyAllowed = 1; buyAllowed >= 0; buyAllowed--) {

                int ignore = next[buyAllowed];

                int profit;
                if (buyAllowed == 1) {
                    int buy = -prices[currIndex] + next[0];
                    profit = Math.max(buy, ignore);
                } else {
                    int sell = prices[currIndex] + next[1];
                    profit = Math.max(sell, ignore);
                }

                curr[buyAllowed] = profit;
            }
            int[] temp = curr;
            curr = next;
            next = temp;
        }

        return next[1];
    }


    public static void main(String[] args) {

        BestTimeToBuySellStock2 maxProfit = new BestTimeToBuySellStock2();
        //System.out.println(maxProfit.maxProfitBruteForce(new int[]{7, 1, 5, 3, 6, 4})); //7
        System.out.println(maxProfit.maxProfit(new int[]{7, 1, 5, 3, 6, 4})); // 7
        System.out.println(maxProfit.maxProfitTab(new int[]{7, 1, 5, 3, 6, 4})); // 7
        //System.out.println(maxProfit.maxProfitTabSpaceOptimized(new int[]{7, 1, 5, 3, 6, 4})); // 7

        //System.out.println(maxProfit.maxProfitBruteForce(new int[]{1,2,3,4,5})); // 4
        System.out.println(maxProfit.maxProfit(new int[]{1, 2, 3, 4, 5})); //4
        System.out.println(maxProfit.maxProfitTab(new int[]{1, 2, 3, 4, 5})); //4
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(new int[]{1, 2, 3, 4, 5})); //4

        System.out.println(maxProfit.maxProfit(new int[]{7, 6, 4, 3, 1})); //0
        System.out.println(maxProfit.maxProfitTab(new int[]{7, 6, 4, 3, 1})); //0
        System.out.println(maxProfit.maxProfitTabSpaceOptimized(new int[]{7, 6, 4, 3, 1})); //0

    }

}

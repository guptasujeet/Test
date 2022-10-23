package com.dp;

//https://www.youtube.com/watch?v=BSRTUtvJSIk
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class BestTimeToBuySellStock1 {


    public int maxProfit1Loop(int[] prices) {

        int profit = 0;
        int minPurchase = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - minPurchase);
            minPurchase = Math.min(minPurchase, prices[i]);
        }
        return profit;
    }


    public int maxProfitBruteForce(int[] prices) {

        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }


    public static void main(String[] args) {

        BestTimeToBuySellStock1 maxProfit = new BestTimeToBuySellStock1();
        System.out.println(maxProfit.maxProfitBruteForce(new int[]{7, 1, 5, 3, 6, 4})); //5
        System.out.println(maxProfit.maxProfit1Loop(new int[]{7, 1, 5, 3, 6, 4})); // 5

        System.out.println(maxProfit.maxProfitBruteForce(new int[]{7, 6, 4, 3, 1})); // 0
        System.out.println(maxProfit.maxProfit1Loop(new int[]{7, 6, 4, 3, 1})); //0

    }

}

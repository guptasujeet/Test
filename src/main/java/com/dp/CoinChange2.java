package com.dp;

import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=ruMqWViJ2_U
//https://leetcode.com/problems/coin-change-ii/
public class CoinChange2 {

    public int change(int amount, int[] coins) {
        Map<Integer, Map<Integer, Integer>> memory = new HashMap<>();
        return solve(amount, coins, 0, memory);
    }

    private int solve(int target, int[] coins, int coinIndex, Map<Integer, Map<Integer, Integer>> memory) {
        //if the target is reached to 0 that means we are able to make it one way
        if (target == 0) {
            return 1;
        }
        if (target < 0 || coinIndex == coins.length) {
            return 0;
        }
        if (memory.containsKey(target)) {
            Map<Integer, Integer> coinToCount = memory.get(target);
            if (coinToCount.get(coinIndex) != null) {
                return coinToCount.get(coinIndex);
            }
        }
        int ans = 0;
        //make sure to start from previous coin selection
        //to make sure we are not repeating same permutation again
        //if we do not start from coinIndex then it will be same as combination sum with all permutation
        for (int i = coinIndex; i < coins.length; i++) {
            if (target >= coins[i]) {
                //not adding +1 in i in recursion b/c we need to check if the same coin can make sum as well or not
                ans += solve(target - coins[i], coins, i, memory);
            }
        }
        if (!memory.containsKey(target)) {
            memory.put(target, new HashMap<>());
        }
        Map<Integer, Integer> coinToCount = memory.get(target);
        coinToCount.put(coinIndex, ans);
        return ans;
    }


    public static void main(String[] args) {
        CoinChange2 coinChange2 = new CoinChange2();

        System.out.println(coinChange2.change(5, new int[]{1, 2, 5})); // 4
        System.out.println(coinChange2.changePractice(5, new int[]{1, 2, 5})); // 4
    }

    public int changePractice(int targetAmount, int[] coin) {
        Map<Integer, Integer> memory = new HashMap<>();
        return changePracticeSolve(targetAmount, coin, 0, memory);
    }

    private int changePracticeSolve(int targetAmount, int[] coins, int coinIndex, Map<Integer, Integer> memory) {
        if (targetAmount == 0) {
            return 1;
        }

        if (coinIndex >= coins.length) {
            return 0;
        }

        if (targetAmount < 0) {
            return 0;
        }

        int ways = 0;
        for (int currentCoinIndex = coinIndex; currentCoinIndex < coins.length; currentCoinIndex++) {
            if (targetAmount >= coins[currentCoinIndex]) {
                ways += changePracticeSolve(targetAmount - coins[currentCoinIndex], coins, currentCoinIndex, memory);
            }
        }
        return ways;
    }
}



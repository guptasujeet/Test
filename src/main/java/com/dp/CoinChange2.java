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
    }

}

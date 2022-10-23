package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=A3FHNCAkhxE
//https://leetcode.com/problems/coin-change-i
//  unbounded knapsack
public class MinNumberOfCoinsOrCoinChange1 {

    public static int minimumElements(int[] denominations, int sum) {
        // Write your code here..
        int[] memory = new int[sum + 1];
        Arrays.fill(memory, -1);
        int mininumCoins = minimumCoinsRec(denominations, sum, memory);
        if (mininumCoins == Integer.MAX_VALUE) {
            return -1;
        }
        return mininumCoins;
    }


    private static int minimumCoinsRec(int[] denominations, int sum, int[] memory) {
        if (sum == 0) {
            return 0;
        }

        if (sum < 0) {
            return Integer.MAX_VALUE;
        }

        if (memory[sum] != -1) {
            return memory[sum];
        }

        int min = Integer.MAX_VALUE;
        for (int denomination : denominations) {
            int nextSum = sum - denomination;
            if (nextSum >= 0) {
                int minCoin = minimumCoinsRec(denominations, nextSum, memory);
                if (minCoin != Integer.MAX_VALUE) {
                    min = Math.min(min, minCoin + 1);
                    memory[sum] = min;
                }
            }
        }
        return min;
    }

    //bottom up
    private static int minimumCoinsTabulation(int denominations[], int sum) {

        int[] memory = new int[sum + 1];
        Arrays.fill(memory, Integer.MAX_VALUE);
        memory[0] = 0;

        for (int currentSum = 1; currentSum <= sum; currentSum++) {
            int min = Integer.MAX_VALUE;
            for (int denomination : denominations) {
                if (denomination <= currentSum) {
                    int minCoinLast = memory[currentSum - denomination];
                    if (minCoinLast != Integer.MAX_VALUE) {
                        min = Math.min(min, minCoinLast + 1);
                        memory[currentSum] = min;
                    }
                }

            }
        }

        if (memory[sum] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return memory[sum];
        }

    }


    public static void main(String[] args) {

        System.out.println(minimumCoinsTabulation(new int[]{17, 10, 5}, 6));
        System.out.println(minimumElements(new int[]{17, 10, 5}, 6));

        System.out.println(minimumCoinsTabulation(new int[]{1, 2, 3}, 7));
        System.out.println(minimumElements(new int[]{1, 2, 3}, 7));

        System.out.println(minimumCoinsTabulation(new int[]{2, 1}, 11));
        System.out.println(minimumElements(new int[]{2, 1}, 11));


    }

}

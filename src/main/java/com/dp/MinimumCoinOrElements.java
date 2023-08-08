package com.dp;

import java.util.Arrays;

// https://www.codingninjas.com/studio/problems/minimum-elements_3843091
public class MinimumCoinOrElements {

    public static int minimumElements(int[] denominations, int sum) {

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

    public static int minimumElements2(int[] denominations, int sum) {
        int[] memory = new int[sum + 1];
        Arrays.fill(memory, -1);
        int mininumCoins = mininumCoins(denominations, sum, memory);
        if (mininumCoins == Integer.MAX_VALUE) {
            return -1;
        }
        return mininumCoins;
    }

    private static int mininumCoins(int[] denominations, int sum, int[] memory) {
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
                int minCoin = mininumCoins(denominations, nextSum, memory);
                if (minCoin != Integer.MAX_VALUE) {
                    min = Math.min(min, minCoin + 1);
                    memory[sum] = min;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] elements = {1, 2, 3};
        System.out.println(minimumElements(elements, 7)); // 3
        System.out.println(minimumElements2(elements, 7)); //3

        int[] elements2 = {1};
        System.out.println(minimumElements(elements2, 0)); //0
        System.out.println(minimumElements2(elements2, 0)); //0

        int[] elements3 = {12, 1, 3};
        System.out.println(minimumElements(elements3, 4)); //2
        System.out.println(minimumElements2(elements3, 4)); //2

        int[] elements4 = {2, 1};
        System.out.println(minimumElements(elements4, 11)); //6
        System.out.println(minimumElements2(elements4, 11));// 6
    }

}

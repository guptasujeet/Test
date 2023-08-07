package com.dp;

import java.util.Arrays;


//https://www.codingninjas.com/codestudio/problems/number-of-ways_3755252?
public class CombinationSumIV {

    public static int findWays2(int[] nums, int tar) {
        int[] memory = new int[tar + 1];
        Arrays.fill(memory, -1);
        return countWays(nums, tar, memory);
    }

    private static int countWays(int[] nums, int target, int[] memory) {
        if (target <= 0) {
            return 0;
        }

        if (memory[target] != -1) {
            return memory[target];
        }

        int count = 0;
        for (int num : nums) {
            if (target >= num) {
                if (target - num == 0) {
                    count++;
                } else {
                    int ans = countWays(nums, target - num, memory);
                    if (ans != 0) {
                        count = count + (ans);
                    }
                }
            }
        }
        memory[target] = count;
        return count;
    }

    public static int findWays(int[] nums, int tar) {
        return countWaysTabulation(nums, tar);
    }

    private static int countWaysTabulation(int[] nums, int finalTarget) {
        int[] memory = new int[finalTarget + 1];
        memory[0] = 0;

        for (int currentTarget = 1; currentTarget <= finalTarget; currentTarget++) {
            int count = 0;
            for (int num : nums) {
                if (currentTarget >= num) {
                    if (currentTarget - num == 0) {
                        count++;
                    } else {
                        int ans = memory[currentTarget - num];
                        if (ans != 0) {
                            count = count + (ans);
                        }
                    }
                }
            }
            memory[currentTarget] = count;
        }
        return memory[finalTarget];
    }


    public static void main(String[] args) {
        System.out.println(findWays2(new int[]{1, 2, 5}, 5));
        System.out.println(findWays(new int[]{1, 2, 5}, 5));
    }


}

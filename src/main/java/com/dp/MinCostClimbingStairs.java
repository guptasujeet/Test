package com.dp;

//https://leetcode.com/problems/min-cost-climbing-stairs/

import java.util.Arrays;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int[] memory = new int[cost.length + 1];
        Arrays.fill(memory, -1);
        int stepBack1 = computeCost(cost, cost.length - 1, memory);
        int stepBack2 = computeCost(cost, cost.length - 2, memory);

        return Math.min(stepBack1, stepBack2);
    }


    public int computeCost(int[] cost, int currentStep, int[] memory) {
        if (currentStep <= 1) {
            return cost[currentStep];
        }

        if (memory[currentStep] != -1) {
            return memory[currentStep];
        }

        int lastStepCost = computeCost(cost, currentStep - 1, memory);
        int secondLastStepCost = computeCost(cost, currentStep - 2, memory);

        int ans = Math.min(lastStepCost, secondLastStepCost) + cost[currentStep];
        memory[currentStep] = ans;
        return ans;
    }

    public int computeCostSpaceBottomUp(int[] cost) {

        int[] memory = new int[cost.length + 1];
        Arrays.fill(memory, -1);

        memory[0] = cost[0];
        memory[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            int ans = Math.min(memory[i - 1], memory[i - 2]) + cost[i];
            memory[i] = ans;
        }

        return Math.min(memory[cost.length - 1], memory[cost.length - 2]);
    }

    public int computeCostSpaceBottomUpSpaceOptimized(int[] cost) {

        int prev2 = cost[0];
        int prev1 = cost[1];

        int current = 0;
        for (int i = 2; i < cost.length; i++) {
            int ans = Math.min(prev1, prev2) + cost[i];
            current = ans;
            prev2 = prev1;
            prev1 = current;
        }

        return Math.min(prev1, prev2);
    }


    public static void main(String[] args) {
        int[] cost1 = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        int minCost = new MinCostClimbingStairs().minCostClimbingStairs(cost1);

        System.out.println(minCost);
        System.out.println(new MinCostClimbingStairs().computeCostSpaceBottomUp(cost1));
        System.out.println(new MinCostClimbingStairs().computeCostSpaceBottomUpSpaceOptimized(cost1));


        int[] cost2 = new int[]{10, 15, 20};

        int minCost2 = new MinCostClimbingStairs().minCostClimbingStairs(cost2);

        System.out.println(minCost2);
        System.out.println(new MinCostClimbingStairs().computeCostSpaceBottomUp(cost2));
        System.out.println(new MinCostClimbingStairs().computeCostSpaceBottomUpSpaceOptimized(cost2));

    }


}

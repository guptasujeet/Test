package com.dp;

import java.util.Arrays;

//unbounded knapsack
public class MinCostForTickets {


    public static int minimumCostTickets(int n, int[] days, int[] cost) {

        //Write your code here
        int[] memory = new int[n + 1];
        Arrays.fill(memory, -1);
        return calculateCost(n, days, cost, 0, memory);
    }


    private static int calculateCost(int totalNumOfDays, int[] days, int[] cost, int currentDayIndex, int[] memory) {
        if (currentDayIndex >= totalNumOfDays) {
            return 0;
        }

        if (memory[currentDayIndex] != -1) {
            return memory[currentDayIndex];
        }


        int cost1 = cost[0] + calculateCost(totalNumOfDays, days, cost, currentDayIndex + 1, memory);


        int dayIndex = currentDayIndex;
        while (dayIndex < totalNumOfDays && days[dayIndex] < (days[currentDayIndex] + 7)) {
            dayIndex++;
        }
        int cost2 = cost[1] + calculateCost(totalNumOfDays, days, cost, dayIndex, memory);

        dayIndex = currentDayIndex;
        while (dayIndex < totalNumOfDays && days[dayIndex] < (days[currentDayIndex] + 30)) {
            dayIndex++;
        }
        int cost3 = cost[2] + calculateCost(totalNumOfDays, days, cost, dayIndex, memory);

        int minCost = Math.min(cost1, Math.min(cost2, cost3));

        memory[currentDayIndex] = minCost;
        return minCost;

    }


    public static void main(String[] args) {

        //System.out.println(minimumCostTickets(6, new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15})); //11
        System.out.println(minimumCostTickets(12, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15})); //17
    }
}

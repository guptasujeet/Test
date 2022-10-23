package com.dp;


import java.util.Arrays;

//https://leetcode.com/problems/reducing-dishes/
//https://www.youtube.com/watch?v=_iGlRDLPLxM&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=17
public class ReducingDishes {

    public int maxSatisfaction(int[] satisfaction) {
        if (satisfaction.length == 0) {
            return 0;
        }

        Arrays.sort(satisfaction);

        int[][] memory = new int[satisfaction.length][satisfaction.length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }

        return calculateSatisfaction(satisfaction, 0, 0, memory);
    }


    public int calculateSatisfaction(int[] satisfaction, int currentIndex, int time, int[][] memory) {
        if (currentIndex >= satisfaction.length) {
            return 0;
        }

        if (memory[currentIndex][time] != -1) {
            return memory[currentIndex][time];
        }

        int currentSatisfaction = satisfaction[currentIndex] * (time + 1);

        int include = currentSatisfaction + calculateSatisfaction(satisfaction, currentIndex + 1, time + 1, memory);

        int exclude = calculateSatisfaction(satisfaction, currentIndex + 1, time, memory);

        memory[currentIndex][time] = Math.max(include, exclude);
        return memory[currentIndex][time];
    }


    public int maxSatisfactionTab(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int[][] memory = new int[satisfaction.length + 1][satisfaction.length + 1];

        for (int currentIndex = satisfaction.length - 1; currentIndex >= 0; currentIndex--) {
            for (int time = currentIndex; time >= 0; time--) {
                int currentSatisfaction = satisfaction[currentIndex] * (time + 1);

                int include = currentSatisfaction + memory[currentIndex + 1][time + 1];
                int exclude = memory[currentIndex + 1][time];

                memory[currentIndex][time] = Math.max(include, exclude);
            }
        }
        return memory[0][0];
    }

    public int maxSatisfactionTabSpaceOptimized(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int[] prev = new int[satisfaction.length + 1];
        int[] curr = new int[satisfaction.length + 1];

        for (int currentIndex = satisfaction.length - 1; currentIndex >= 0; currentIndex--) {
            for (int time = currentIndex; time >= 0; time--) {
                int currentSatisfaction = satisfaction[currentIndex] * (time + 1);

                int include = currentSatisfaction + prev[time + 1];
                int exclude = prev[time];

                curr[time] = Math.max(include, exclude);
            }
            int[] temp = curr;
            curr = prev;
            prev = temp;
        }
        return prev[0];
    }

    public static void main(String[] args) {

        ReducingDishes reducingDishes = new ReducingDishes();

        System.out.println(reducingDishes.maxSatisfaction(new int[]{-1, -8, 0, 5, -9}));
        System.out.println(reducingDishes.maxSatisfactionTab(new int[]{-1, -8, 0, 5, -9}));
        System.out.println(reducingDishes.maxSatisfactionTabSpaceOptimized(new int[]{-1, -8, 0, 5, -9}));
    }
}

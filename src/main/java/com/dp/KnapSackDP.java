package com.dp;


import java.util.Arrays;

//https://www.codingninjas.com/codestudio/problems/0-1-knapsack_920542
//https://www.youtube.com/watch?v=xdPv2SZJLVI
public class KnapSackDP {


    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Change in the given tree itself.
         * No need to return or print the output.
         * Taking input and printing output is handled automatically.
         */

        int[][] memory = new int[n][maxWeight + 1];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return solve(weight, value, n - 1, maxWeight, memory);

    }


    static int solve(int[] weight, int[] value, int currentItemIndex, int capacityRemaining, int[][] memory) {

        if (capacityRemaining <= 0) {
            return 0;
        }

        int currentItemWeight = weight[currentItemIndex];
        int currentItemValue = value[currentItemIndex];

        if (currentItemIndex == 0) {
            if (currentItemWeight <= capacityRemaining) {
                return currentItemValue;
            } else {
                return 0;
            }
        }

        if (memory[currentItemIndex][capacityRemaining] != -1) {
            return memory[currentItemIndex][capacityRemaining];
        }

        int includeItemWeight = 0;
        if (currentItemWeight <= capacityRemaining) {
            includeItemWeight = currentItemValue +
                    solve(weight, value, currentItemIndex - 1, capacityRemaining - currentItemWeight, memory);
        }

        int excludeItemWeight = solve(weight, value, currentItemIndex - 1, capacityRemaining, memory);

        memory[currentItemIndex][capacityRemaining] = Math.max(includeItemWeight, excludeItemWeight);

        return memory[currentItemIndex][capacityRemaining];

    }


    static int knapsackTab(int[] weight, int[] value, int items, int capacity) {

        int[][] memory = new int[items][capacity + 1];

        //fill of -1 not needed

        for (int remCapacity = weight[0]; remCapacity <= capacity; remCapacity++) {
            if (weight[0] <= capacity) {
                memory[0][remCapacity] = value[0];
            } else {
                memory[0][remCapacity] = 0;
            }
        }


        for (int currentItemIndex = 1; currentItemIndex < items; currentItemIndex++) {
            int currentItemWeight = weight[currentItemIndex];
            int currentItemValue = value[currentItemIndex];
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {

                int includeItemWeight = 0;
                if (currentItemWeight <= currentCapacity) {
                    includeItemWeight = currentItemValue + memory[currentItemIndex - 1][currentCapacity - currentItemWeight];
                }

                int excludeItemWeight = memory[currentItemIndex - 1][currentCapacity];

                memory[currentItemIndex][currentCapacity] = Math.max(includeItemWeight, excludeItemWeight);
            }
        }

        return memory[items - 1][capacity];

    }

    static int knapsackTabSpaceOptimized(int[] weight, int[] value, int items, int capacity) {

        int[] prev = new int[capacity + 1];
        int[] current = new int[capacity + 1];

        //fill of -1 not needed

        for (int remCapacity = weight[0]; remCapacity <= capacity; remCapacity++) {
            if (weight[0] <= capacity) {
                prev[remCapacity] = value[0];
            } else {
                prev[remCapacity] = 0;
            }
        }


        for (int currentItemIndex = 1; currentItemIndex < items; currentItemIndex++) {
            int currentItemWeight = weight[currentItemIndex];
            int currentItemValue = value[currentItemIndex];
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {

                int includeItemWeight = 0;
                if (currentItemWeight <= currentCapacity) {
                    includeItemWeight = currentItemValue + prev[currentCapacity - currentItemWeight];
                }

                int excludeItemWeight = prev[currentCapacity];

                current[currentCapacity] = Math.max(includeItemWeight, excludeItemWeight);
            }
            int[] temp = prev;
            prev = current;
            current = temp;
        }

        return prev[capacity];

    }


    static int knapsackTabSpaceOptimizedFurther(int[] weight, int[] value, int items, int capacity) {

        int[] current = new int[capacity + 1];

        //fill of -1 not needed

        for (int remCapacity = weight[0]; remCapacity <= capacity; remCapacity++) {
            if (weight[0] <= capacity) {
                current[remCapacity] = value[0];
            } else {
                current[remCapacity] = 0;
            }
        }


        for (int currentItemIndex = 1; currentItemIndex < items; currentItemIndex++) {
            int currentItemWeight = weight[currentItemIndex];
            int currentItemValue = value[currentItemIndex];
            //todo; check here
            //changed direction from right to left , instead of left to right
            //as we depend on left values for prev compution
            for (int currentCapacity = capacity; currentCapacity >= 0; currentCapacity--) {

                int includeItemWeight = 0;
                if (currentItemWeight <= currentCapacity) {
                    includeItemWeight = currentItemValue + current[currentCapacity - currentItemWeight];
                }

                int excludeItemWeight = current[currentCapacity];

                current[currentCapacity] = Math.max(includeItemWeight, excludeItemWeight);
            }
        }

        return current[capacity];

    }


    public static void main(String[] args) {

        System.out.println(knapsack(new int[]{1, 2, 4, 5}, new int[]{5, 4, 8, 6}, 4, 5));

        System.out.println(knapsackTab(new int[]{1, 2, 4, 5}, new int[]{5, 4, 8, 6}, 4, 5));

        System.out.println(knapsackTabSpaceOptimized(new int[]{1, 2, 4, 5}, new int[]{5, 4, 8, 6}, 4, 5));


        System.out.println(knapsackTabSpaceOptimizedFurther(new int[]{1, 2, 4, 5}, new int[]{5, 4, 8, 6}, 4, 5));


    }
}

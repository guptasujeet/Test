package com.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TruckersProfit {


    public static void main(String[] args) {
        //1st cargoId
        //2nd weight
        //3rd profit

        List<List<Integer>> data = new ArrayList<>();
        List<Integer> data1 = new ArrayList<>();
        data1.add(38);
        data1.add(180);
        data1.add(500);
        data.add(data1);

        List<Integer> data2 = new ArrayList<>();
        data2.add(21);
        data2.add(280);
        data2.add(1800);
        data.add(data2);

        List<Integer> data3 = new ArrayList<>();
        data3.add(13);
        data3.add(120);
        data3.add(1500);
        data.add(data3);
        data.forEach(System.out::println);

        System.out.println(findTruckCargo(300, data));
    }

    static int findTruckCargo(int maxCargoWeight, List<List<Integer>> weights) {
        int w = maxCargoWeight;
        int n = weights.size();

        int[] wt = new int[n];
        int[] val = new int[n];
        for (int i = 0; i < weights.size(); i++) {
            List<Integer> weight = weights.get(i);
            wt[i] = weight.get(1);
            val[i] = weight.get(2);
        }

        System.out.println("Weight -> " + Arrays.toString(wt));
        System.out.println("Price -> " + Arrays.toString(val));

        // Populate base cases
        int[][] mat = new int[n + 1][w + 1];
        for (int r = 0; r < w + 1; r++) {
            mat[0][r] = 0;
        }
        for (int c = 0; c < n + 1; c++) {
            mat[c][0] = 0;
        }

        // Main logic
        for (int item = 1; item <= n; item++) {
            for (int capacity = 1; capacity <= w; capacity++) {
                int maxValWithoutCurr = mat[item - 1][capacity]; // This is guaranteed to exist
                int maxValWithCurr = 0; // We initialize this value to 0

                int weightOfCurr = wt[item - 1]; // We use item -1 to account for the extra row at the top
                if (capacity >= weightOfCurr) { // We check if the knapsack can fit the current item
                    maxValWithCurr = val[item - 1]; // If so, maxValWithCurr is at least the value of the current item

                    int remainingCapacity = capacity - weightOfCurr; // remainingCapacity must be at least 0
                    maxValWithCurr += mat[item - 1][remainingCapacity]; // Add the maximum value obtainable with the remaining capacity
                }

                mat[item][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr); // Pick the larger of the two
            }
        }

        System.out.println(mat[n][w]); // Final answer
        return mat[n][w];

    }


}

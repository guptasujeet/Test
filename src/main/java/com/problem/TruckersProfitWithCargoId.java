package com.problem;

import java.util.*;

public class TruckersProfitWithCargoId {


    public static void main(String[] args) {
        //1st cargoId
        //2nd weight
        //3rd profit

        List<List<Integer>> data = new ArrayList<>();
        List<Integer> data1 = new ArrayList<>();
        data1.add(38);
        data1.add(170);
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

        List<Integer> data4 = new ArrayList<>();
        data4.add(15);
        data4.add(10);
        data4.add(200);
        data.add(data4);

        data.forEach(System.out::println);


        System.out.println(findTruckCargo(300, data));

    }

    static List<Integer> findTruckCargo(int maxCargoWeight, List<List<Integer>> weights) {
        int w = maxCargoWeight;
        int n = weights.size();

        Map<Integer, Integer> cargoIndexId = new HashMap<>();

        int[] wt = new int[n];
        int[] val = new int[n];
        for (int i = 0; i < weights.size(); i++) {
            List<Integer> weight = weights.get(i);
            wt[i] = weight.get(1);
            val[i] = weight.get(2);
            cargoIndexId.put(i, weight.get(0));

        }

        System.out.println("Weight -> " + Arrays.toString(wt));
        System.out.println("Price -> " + Arrays.toString(val));

        // Populate base cases
        CargoPair[][] mat = new CargoPair[n + 1][w + 1];
        for (int r = 0; r < w + 1; r++) {
            mat[0][r] = CargoPair.EMPTY;
        }
        for (int c = 0; c < n + 1; c++) {
            mat[c][0] = CargoPair.EMPTY;
        }

        // Main logic
        for (int item = 1; item <= n; item++) {
            for (int capacity = 1; capacity <= w; capacity++) {
                CargoPair maxValWithoutCurr = mat[item - 1][capacity]; // This is guaranteed to exist
                CargoPair maxValWithCurr = CargoPair.EMPTY; // We initialize this value to 0
                int maxValWithCurrProfit = 0;

                int weightOfCurr = wt[item - 1]; // We use item -1 to account for the extra row at the top
                if (capacity >= weightOfCurr) { // We check if the knapsack can fit the current item
                    maxValWithCurrProfit = val[item - 1]; // If so, maxValWithCurr is at least the value of the current item
                    int cargoId = item - 1;
                    maxValWithCurr = new CargoPair(cargoId, maxValWithCurrProfit);

                    int remainingCapacity = capacity - weightOfCurr; // remainingCapacity must be at least 0

                    CargoPair cargoPair = mat[item - 1][remainingCapacity];
                    maxValWithCurr.maxProfit = maxValWithCurr.maxProfit + cargoPair.maxProfit;
                    maxValWithCurr.cargoIds.addAll(cargoPair.cargoIds);
                }

                mat[item][capacity] = getMax(maxValWithoutCurr, maxValWithCurr); // Pick the larger of the two
            }
        }

        System.out.println("Answer -> " + mat[n][w]); // Final answer
        for (CargoPair[] data : mat) {
            System.out.println(Arrays.toString(data));
        }

        List<Integer> output = new ArrayList<>();
        CargoPair cargoPair = mat[n][w];
        Collections.reverse(cargoPair.cargoIds);
        for (Integer cargoId : cargoPair.cargoIds) {
            output.add(cargoIndexId.get(cargoId));
        }
        output.add(cargoPair.maxProfit);

        return output;

    }

    public static CargoPair getMax(CargoPair c1, CargoPair c2) {
        if (c1.maxProfit > c2.maxProfit) {
            return c1;
        } else {
            return c2;
        }
    }

    static class CargoPair {
        public CargoPair() {
        }

        public static CargoPair EMPTY = new CargoPair();


        public CargoPair(int cargoId, int maxProfit) {
            this.cargoIds.add(cargoId);
            this.maxProfit = maxProfit;
        }

        List<Integer> cargoIds = new ArrayList<>();
        int maxProfit;

        @Override
        public String toString() {
            return "{" +
                    "id=" + cargoIds +
                    ", profit=" + maxProfit +
                    '}';
        }


    }


}

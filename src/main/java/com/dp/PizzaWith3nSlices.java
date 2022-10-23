package com.dp;

import java.util.Arrays;

public class PizzaWith3nSlices {


    public int maxSizeSlices(int[] slices) {

        int[][] memory = new int[slices.length][slices.length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        int including1st = calculateMaxPizzaSize(slices, 0, slices.length - 1, 0, memory);

        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        int excluding1st = calculateMaxPizzaSize(slices, 1, slices.length, 0, memory);


        return Math.max(including1st, excluding1st);
    }

    private int calculateMaxPizzaSize(int[] slices, int start, int end, int sliceHad, int[][] memory) {
        if (sliceHad >= (slices.length + 1) / 3 || start > (end - 1)) {
            return 0;
        }

        if (memory[start][sliceHad] != -1) {
            return memory[start][sliceHad];
        }

        int include = slices[start] + calculateMaxPizzaSize(slices, start + 2, end, sliceHad + 1, memory);
        int exclude = calculateMaxPizzaSize(slices, start + 1, end, sliceHad, memory);

        memory[start][sliceHad] = Math.max(include, exclude);
        return memory[start][sliceHad];
    }


    public static void main(String[] args) {

        PizzaWith3nSlices pizza = new PizzaWith3nSlices();

        System.out.println(pizza.maxSizeSlices(new int[]{9, 5, 1, 7, 8, 4, 4, 5, 5, 8, 7, 7})); //30
        System.out.println(pizza.maxSizeSlices(new int[]{1, 2, 3, 4, 5, 6})); //10

    }

}

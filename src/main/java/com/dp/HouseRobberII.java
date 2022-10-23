package com.dp;

import java.util.Arrays;

//https://www.codingninjas.com/codestudio/problems/house-robber_839733
//https://www.youtube.com/watch?v=Fe2GeXEzWM0
public class HouseRobberII {


    public static long houseRobber(int[] valueInHouse) {
        // Write your code here.
        if (valueInHouse.length == 1) {
            return valueInHouse[0];
        }
        long[] memory = new long[valueInHouse.length + 1];
        Arrays.fill(memory, -1);

        long firstPart = maxAmount(valueInHouse, valueInHouse.length - 1, 0, memory);
        Arrays.fill(memory, -1);
        long secondPart = maxAmount(valueInHouse, valueInHouse.length, 1, memory);
        return Math.max(firstPart, secondPart);
    }

    public static long houseRobber2(int[] valueInHouse) {
        // Write your code here.
        if (valueInHouse.length == 1) {
            return valueInHouse[0];
        }
        long[] memory = new long[valueInHouse.length + 1];
        Arrays.fill(memory, -1);
        long firstPart = maxAmountBottomUp(valueInHouse, 0, valueInHouse.length - 1, memory);
        Arrays.fill(memory, -1);
        long secondPart = maxAmountBottomUp(valueInHouse, 1, valueInHouse.length, memory);
        return Math.max(firstPart, secondPart);
    }

    public static long maxAmount(int[] valueInHouse, int end, int currentHouse, long[] memory) {


        if (currentHouse > end - 1) {
            return 0;
        }

        int amount = valueInHouse[currentHouse];

        if (currentHouse == end - 1) {
            return amount;
        }

        if (memory[currentHouse] != -1) {
            return memory[currentHouse];
        }


        long include = amount + maxAmount(valueInHouse, end, currentHouse + 2, memory);
        long exclude = maxAmount(valueInHouse, end, currentHouse + 1, memory);


        memory[currentHouse] = Math.max(include, exclude);

        return memory[currentHouse];
    }

    public static long maxAmountBottomUp(int[] valueInHouse, int start, int end, long[] memory) {

        memory[start] = valueInHouse[start];

        for (int currentHouse = start + 1; currentHouse < end; currentHouse++) {

            int amount = valueInHouse[currentHouse];

            long exclude = memory[currentHouse - 1];

            long include = amount;
            if (currentHouse - 2 >= start) {
                include = memory[currentHouse - 2] + amount;
            }
            memory[currentHouse] = Math.max(include, exclude);
        }


        return memory[end - 1];
    }


    public static void main(String[] args) {

        System.out.println(houseRobber(new int[]{1, 5, 1, 2, 6}));
        System.out.println(houseRobber2(new int[]{1, 5, 1, 2, 6}));


        System.out.println(houseRobber(new int[]{2, 3, 5}));
        System.out.println(houseRobber2(new int[]{2, 3, 5}));

    }
}

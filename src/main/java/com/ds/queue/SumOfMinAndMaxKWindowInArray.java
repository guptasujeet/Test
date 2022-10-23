package com.ds.queue;

import java.util.TreeSet;

//https://www.geeksforgeeks.org/sum-minimum-maximum-elements-subarrays-size-k/
//https://www.youtube.com/watch?v=_gJ3to4RyeQ&t=4222s
public class SumOfMinAndMaxKWindowInArray {


    private int sumMinMaxKWindow(int[] data, int windowSize) {

        TreeSet<Integer> sorted = new TreeSet<>();

        int sumMinMax = 0;
        for (int i = 0; i < windowSize; i++) {
            sorted.add(data[i]);
        }

        sumMinMax = sumMinMax + (sorted.first() + sorted.last());

        for (int i = windowSize; i < data.length; i++) {
            //remove the first one
            sorted.remove(data[i - windowSize]);
            sorted.add(data[i]);
            sumMinMax = sumMinMax + (sorted.first() + sorted.last());

        }

        return sumMinMax;

    }


    public static void main(String[] args) {

        SumOfMinAndMaxKWindowInArray minMaxSum = new SumOfMinAndMaxKWindowInArray();

        System.out.println(minMaxSum.sumMinMaxKWindow(new int[]{2, 5, -1, 7, -3, -1, -2}, 4)); //18
        System.out.println(minMaxSum.sumMinMaxKWindow(new int[]{2, -1, -1, 7, -3, -1, -2}, 4)); //18
        System.out.println(minMaxSum.sumMinMaxKWindow(new int[]{-1, -1, -1, -1, -1, -1, -1}, 4)); //-8
        System.out.println(minMaxSum.sumMinMaxKWindow(new int[]{1, 1, 1, 1, 1, 1, 1}, 4)); //8
    }
}

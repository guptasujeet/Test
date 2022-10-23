package com.dp;

import com.util.Pair;


//https://www.youtube.com/watch?v=LDiD9fr28tc
public class MinCostTreeFromLeaf {

    public int mctFromLeafValues(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return arr[0] * arr[1];
        }
        Pair<Integer, Integer> pair = calculateMinCost(arr, 1, arr.length);
        System.out.println(pair);
        return pair.getFirst();
    }

    private Pair<Integer, Integer> calculateMinCost(int[] arr, int start, int end) {

        if (end - start == 1) {
            return Pair.of(0, arr[start]);
        }

        if (start == 0) {
            return Pair.of(0, arr[start]);
        }

        if (start >= end) {
            return Pair.of(0, 0);
        }

        int minAns = Integer.MAX_VALUE;
        int nonLeafSum = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int tempAns = Integer.MAX_VALUE;
            if (i + 1 < arr.length) {
                Pair<Integer, Integer> leftPair = calculateMinCost(arr, 0, i - 1);
                Pair<Integer, Integer> rightPair = calculateMinCost(arr, i + 1, arr.length);
                tempAns = (leftPair.getSecond() * rightPair.getSecond()) + rightPair.getFirst() + rightPair.getSecond();
                nonLeafSum = Math.min(nonLeafSum, tempAns);
            }
            minAns = Math.min(tempAns, minAns);
        }

        return Pair.of(minAns, nonLeafSum);

    }


    public static void main(String[] args) {

        MinCostTreeFromLeaf minCost = new MinCostTreeFromLeaf();
        //System.out.println(minCost.mctFromLeafValues(new int[]{4, 11})); //44
        System.out.println(minCost.mctFromLeafValues(new int[]{6, 2, 4})); //32

    }
}

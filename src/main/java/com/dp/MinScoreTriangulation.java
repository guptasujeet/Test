package com.dp;

import java.util.Arrays;

//https://www.youtube.com/watch?v=Eo4G_LPCgX8
//https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
public class MinScoreTriangulation {

    public int minScoreTriangulation(int[] values) {
        if (values.length <= 2) {
            return 0;
        }
        int[][] memory = new int[values.length][values.length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculateMinScore(values, 0, values.length - 1, memory);
    }

    private int calculateMinScore(int[] values, int startIndex, int endIndex, int[][] memory) {
        if (endIndex - startIndex == 1) {
            return 0;
        }

        if (memory[startIndex][endIndex] != -1) {
            return memory[startIndex][endIndex];
        }

        int min = Integer.MAX_VALUE;
        for (int triangulationPoint = startIndex + 1; triangulationPoint < endIndex; triangulationPoint++) {
            int minCost = values[startIndex] * values[triangulationPoint] * values[endIndex];

            int subProb1Cost = calculateMinScore(values, startIndex, triangulationPoint, memory);
            int subProb2Cost = calculateMinScore(values, triangulationPoint, endIndex, memory);

            int total = minCost + subProb1Cost + subProb2Cost;
            min = Math.min(min, total);
        }
        memory[startIndex][endIndex] = min;
        return min;
    }


    public int minScoreTriangulationTab(int[] values) {
        if (values.length <= 2) {
            return 0;
        }
        return calculateMinScoreTab(values);
    }

    private int calculateMinScoreTab(int[] values) {
        int n = values.length;
        int[][] memory = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int triangulationPoint = i + 1; triangulationPoint < j; triangulationPoint++) {
                    int minCost = values[i] * values[triangulationPoint] * values[j];

                    int subProb1Cost = memory[i][triangulationPoint];
                    int subProb2Cost = memory[triangulationPoint][j];

                    int total = minCost + subProb1Cost + subProb2Cost;
                    min = Math.min(min, total);
                }
                memory[i][j] = min;
            }
        }
        return memory[0][n - 1];
    }


    public static void main(String[] args) {
        MinScoreTriangulation triangulation = new MinScoreTriangulation();
        System.out.println(triangulation.minScoreTriangulation(new int[]{3, 7, 4, 5}));
        System.out.println(triangulation.minScoreTriangulationTab(new int[]{3, 7, 4, 5}));
    }
}

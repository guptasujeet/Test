package com.problem;

import java.util.Arrays;

public class TravellingSalesMan {

    public static int shortestRoute(int[][] distance) {

        if (distance.length == 1) {
            return distance[0][0];
        }

        // Write your code here.
        int minDistance = Integer.MAX_VALUE;
        boolean[] visitedCity = new boolean[distance.length];
        int[][][] memory = new int[distance.length][distance.length][distance.length];
        resetDP(memory);
        for (int city = 0; city < distance.length; city++) {
            Arrays.fill(visitedCity, false);
            visitedCity[city] = true;
            int totalDist = solveShortestRouteWorking(distance, city, city, 0, visitedCity);
            minDistance = Math.min(minDistance, totalDist);
        }
        return minDistance;
    }

    private static void resetDP(int[][][] memory) {
        for (int[][] ints : memory) {
            for (int[] row : ints) {
                Arrays.fill(row, -1);
            }
        }
    }


    private static int solveShortestRouteWorking(int[][] distance, int sourceCity, int currentCity, int visitedCityCount,
                                                 boolean[] visitedCity) {

        if (visitedCityCount >= distance.length - 1) {
            return distance[currentCity][sourceCity];
        }


        int minDist = Integer.MAX_VALUE;
        for (int city = 0; city < distance.length; city++) {
            if (!visitedCity[city]) {
                visitedCity[city] = true;
                int currToNxtDist = distance[currentCity][city];

                int nextRouteDist = solveShortestRouteWorking(distance, sourceCity, city, visitedCityCount + 1, visitedCity);
                if (nextRouteDist != Integer.MAX_VALUE) {
                    int currRouteDist = currToNxtDist + nextRouteDist;
                    minDist = Math.min(minDist, currRouteDist);
                }

                visitedCity[city] = false;
            }
        }


        return minDist;
    }


    public static void main(String[] args) {


        int[][] distance3 = new int[][]{
                {0, 32, 50, 29},
                {32, 0, 3, 7},
                {50, 3, 0, 32},
                {29, 7, 32, 0}
        };


        System.out.println(shortestRoute(distance3)); //89


        int[][] distance1 = new int[][]{
                {0, 20, 42, 25},
                {20, 0, 30, 34},
                {42, 30, 0, 10},
                {25, 34, 10, 0}
        };


        System.out.println(shortestRoute(distance1)); //85

        int[][] distance2 = new int[][]{
                {0, 2},
                {2, 0}
        };


        System.out.println(shortestRoute(distance2)); //4


    }
}

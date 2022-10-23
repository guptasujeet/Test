package com.dp;

import java.util.Arrays;

//https://www.codingninjas.com/codestudio/problems/cut-into-segments_1214651
//https://www.youtube.com/watch?v=MFAAZW2znv8&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb
public class CutIntoSegments {

    public static int cutSegments(int segmentSize, int x, int y, int z) {
        // Write your code here.
        int[] segments = new int[]{x, y, z};
        double ans = getSegmentsBottomUp(segmentSize, segments);
        if (Double.isInfinite(ans)) {
            return 0;
        } else {
            return (int) ans;
        }
    }

    public static int cutSegments2(int segmentSize, int x, int y, int z) {
        // Write your code here.
        int[] segments = new int[]{x, y, z};
        double[] memory = new double[segmentSize + 1];
        Arrays.fill(memory, -1);
        double ans = getSegments(segmentSize, segments, memory);
        if (Double.isInfinite(ans)) {
            return 0;
        } else {
            return (int) ans;
        }
    }

    private static double getSegments(int segmentSize, int[] segments, double[] memory) {

        if (segmentSize == 0) {
            return 0;
        }

        if (memory[segmentSize] != -1) {
            return memory[segmentSize];
        }

        double max = Double.NEGATIVE_INFINITY;
        for (int segment : segments) {
            if (segmentSize >= segment) {
                double segResult = getSegments(segmentSize - segment, segments, memory);
                max = Math.max(segResult, max);
            }
        }

        memory[segmentSize] = max + 1;
        return memory[segmentSize];
    }


    private static double getSegmentsBottomUp(int segmentSize, int[] segments) {

        double[] memory = new double[segmentSize + 1];
        Arrays.fill(memory, Double.NEGATIVE_INFINITY);
        memory[0] = 0;


        for (int i = 1; i <= segmentSize; i++) {
            for (int segment : segments) {
                if (i - segment >= 0) {
                    double max = Math.max(memory[i], memory[i - segment] + 1);
                    memory[i] = max;
                }
            }
        }

        return memory[segmentSize];
    }


    public static void main(String[] args) {

        System.out.println(cutSegments(7, 5, 2, 2));
        System.out.println(cutSegments2(7, 5, 2, 2));
        System.out.println("-------");
        System.out.println(cutSegments(8, 3, 3, 3));
        System.out.println(cutSegments2(8, 3, 3, 3));
    }

}

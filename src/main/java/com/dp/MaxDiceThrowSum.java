package com.dp;


import java.util.Arrays;

//https://practice.geeksforgeeks.org/problems/dice-throw5349/1
//https://www.youtube.com/watch?v=XY297u8qRDI&list=PLDzeHZWIZsTomOPnCiU3J95WufjE36wsb&index=21
public class MaxDiceThrowSum {

    public int getTotalWays(int diceCount, int faceCount, int target) {

        int[][] memory = new int[diceCount + 1][target + 1];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }
        return calculateWays(diceCount, faceCount, target, memory);
    }


    public int calculateWays(int diceCount, int faceCount, int target, int[][] memory) {

        if (diceCount == 0 && target == 0) {
            return 1;
        }

        if (target <= 0) {
            return 0;
        }

        if (diceCount <= 0) {
            return 0;
        }

        if (memory[diceCount][target] != -1) {
            return memory[diceCount][target];
        }


        int ways = 0;
        for (int face = 1; face <= faceCount; face++) {
            ways = ways + calculateWays(diceCount - 1, faceCount, target - face, memory);
        }
        memory[diceCount][target] = ways;
        return ways;
    }

    private int getTotalWaysBottomUp(int diceCount, int faceCount, int finalTarget) {
        int[][] memory = new int[diceCount + 1][finalTarget + 1];
        memory[0][0] = 1;

        for (int dice = 1; dice <= diceCount; dice++) {
            for (int target = 1; target <= finalTarget; target++) {
                int ways = 0;
                for (int face = 1; face <= faceCount; face++) {
                    if (target - face >= 0) {
                        ways = ways + memory[dice - 1][target - face];
                    }
                }
                memory[dice][target] = ways;
            }
        }
        return memory[diceCount][finalTarget];
    }

    private int getTotalWaysBottomUpSpaceOptimized(int diceCount, int faceCount, int finalTarget) {
        int[] prev = new int[finalTarget + 1];
        prev[0] = 1;
        int[] current = new int[finalTarget + 1];

        for (int dice = 1; dice <= diceCount; dice++) {
            for (int target = 1; target <= finalTarget; target++) {
                int ways = 0;
                for (int face = 1; face <= faceCount; face++) {
                    if (target - face >= 0) {
                        ways = ways + prev[target - face];
                    }
                }
                current[target] = ways;
            }
            int[] temp = current;
            current = prev;
            prev = temp;
        }
        return prev[finalTarget];
    }


    public static void main(String[] args) {
        MaxDiceThrowSum dice = new MaxDiceThrowSum();
        System.out.println(dice.getTotalWays(3, 2, 6)); //1
        System.out.println(dice.getTotalWaysBottomUp(3, 2, 6)); //1
        System.out.println(dice.getTotalWaysBottomUpSpaceOptimized(3, 2, 6)); //1

        System.out.println(dice.getTotalWays(3, 6, 12)); //25
        System.out.println(dice.getTotalWaysBottomUp(3, 6, 12)); //25
        System.out.println(dice.getTotalWaysBottomUpSpaceOptimized(3, 6, 12)); //25

    }

}

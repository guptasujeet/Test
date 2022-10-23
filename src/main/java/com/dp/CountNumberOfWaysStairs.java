package com.dp;


import java.util.Arrays;

//https://www.youtube.com/watch?v=S31W3kohFDk
//https://www.codingninjas.com/codestudio/problems/count-ways-to-reach-nth-stairs_798650
public class CountNumberOfWaysStairs {


    public static long countDistinctWayToClimbStair2(long nStairs) {
        int stepCount = (int) nStairs;
        // Write your code here.
        long[] memory = new long[stepCount + 1];
        Arrays.fill(memory, -1);
        return getCountRecMem(stepCount, memory);
    }

    public static long getCountRecMem(int steps, long[] memory) {
        if (steps == 0) {
            return 1;
        }
        if (steps <= 2) {
            return steps;
        }

        if (memory[steps] != -1) {
            return memory[steps];
        }

        long ans = getCountRecMem(steps - 1, memory) + getCountRecMem(steps - 2, memory);
        //specific condition in question
        ans = ans % 1000000007;
        memory[steps] = ans;
        return memory[steps];

    }


    public static long countDistinctWayToClimbStairReverse(long nStairs) {
        int stepCount = (int) nStairs;
        // Write your code here.
        long[] memory = new long[stepCount + 1];
        Arrays.fill(memory, -1);
        return getCountRecMemReverse(stepCount, memory);
    }

    public static long getCountRecMemReverse(int steps, long[] memory) {
        if (steps == 0) {
            return 1;
        }
        if (steps <= 2) {
            return steps;
        }

        if (memory[steps] != -1) {
            return memory[steps];
        }

        long ans = getCountRecMemReverse(steps - 1, memory) + getCountRecMemReverse(steps - 2, memory);
        //specific condition in question
        ans = ans % 1000000007;
        memory[steps] = ans;
        return memory[steps];

    }

    public static long countDistinctWayToClimbStair(long nStairs) {
        int stepCount = (int) nStairs;
        // Write your code here.
        long[] memory = new long[stepCount + 1];
        if (stepCount == 0) {
            return 1;
        }
        if (stepCount <= 2) {
            return stepCount;
        }
        memory[0] = 1;
        memory[1] = 1;
        memory[2] = 2;


        for (int i = 3; i <= nStairs; i++) {
            long ans = memory[i - 1] + memory[i - 2];
            ans = ans % 1000000007;
            memory[i] = ans;
        }

        return memory[stepCount];
    }


    public static long countDistinctWayToClimbStairSpaceOptimized(long nStairs) {
        int stepCount = ((int) nStairs);
        // Write your code here.
        if (stepCount == 0) {
            return 1;
        }
        if (stepCount <= 2) {
            return stepCount;
        }

        long prev2 = 1;
        long prev1 = 2;

        long current = 0;
        for (int i = 3; i <= stepCount; i++) {
            long ans = prev1 + prev2;
            ans = ans % 1000000007;
            current = ans;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }


    public static void main(String[] args) {
        System.out.println(countDistinctWayToClimbStair2(51));
        System.out.println(countDistinctWayToClimbStair(51));
        System.out.println(countDistinctWayToClimbStairSpaceOptimized(51));
    }


}

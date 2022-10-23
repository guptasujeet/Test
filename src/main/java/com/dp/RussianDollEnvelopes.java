package com.dp;

import java.util.Arrays;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length <= 1) {
            return envelopes.length;
        }

        int[][] memory = new int[envelopes.length + 1][envelopes.length];
        for (int[] ints : memory) {
            Arrays.fill(ints, -1);
        }

        //first sort by 1st index ,then reverse sort by 2nd index on matching 1st index
        Arrays.sort(envelopes, (a, b) -> {
            int ans = a[0] - b[0];
            if (ans == 0) {
                return b[1] - a[1];
            }
            return ans;
        });

        return calculateMaxEnvelopes(envelopes, 0, -1, memory);
    }

    public int maxEnvelopesTab(int[][] envelopes) {

        if (envelopes.length <= 1) {
            return envelopes.length;
        }

        //first sort by 1st index ,then reverse sort by 2nd index on matching 1st index
        Arrays.sort(envelopes, (a, b) -> {
            int ans = a[0] - b[0];
            if (ans == 0) {
                return b[1] - a[1];
            }
            return ans;
        });

        return calculateMaxEnvelopesTab(envelopes);
    }

    private int calculateMaxEnvelopesTab(int[][] envelopes) {
        int size = envelopes.length;

        int[] prev = new int[envelopes.length + 1];
        int[] curr = new int[envelopes.length + 1];

        for (int currentIndex = size - 1; currentIndex >= 0; currentIndex--) {
            for (int prevIndex = currentIndex - 1; prevIndex >= -1; prevIndex--) {

                int include = 0;
                if (prevIndex == -1 || envelopes[currentIndex][1] > envelopes[prevIndex][1]) {
                    include = 1 + prev[currentIndex + 1];
                }

                int exclude = prev[prevIndex + 1];
                curr[prevIndex + 1] = Math.max(include, exclude);
            }
            int[] temp = curr;
            curr = prev;
            prev = temp;
        }
        return prev[0];
    }

    public int maxEnvelopesTabUsingBinarySearch(int[][] envelopes) {

        if (envelopes.length <= 1) {
            return envelopes.length;
        }

        //first sort by 1st index ,then reverse sort by 2nd index on matching 1st index
        Arrays.sort(envelopes, (a, b) -> {
            int ans = a[0] - b[0];
            if (ans == 0) {
                return b[1] - a[1];
            }
            return ans;
        });

        return calculateMaxEnvelopesTabUsingBinarySearch(envelopes);
    }

    private int calculateMaxEnvelopesTabUsingBinarySearch(int[][] envelopes) {

        int[] memory = new int[envelopes.length];

        int len = 0;
        for (int[] envelope : envelopes) {
            int item = envelope[1];
            int index = Arrays.binarySearch(memory, 0, len, item);
            if (index < 0) {
                index = -(index + 1);
            }
            memory[index] = item;
            if (index == len) {
                len++;
            }
        }
        return len;
    }

    private int calculateMaxEnvelopes(int[][] envelopes, int currentIndex, int prevIndex, int[][] memory) {

        if (currentIndex >= envelopes.length) {
            return 0;
        }

        if (memory[currentIndex][prevIndex + 1] != -1) {
            return memory[currentIndex][prevIndex + 1];
        }

        int include = Integer.MIN_VALUE;
        if (prevIndex == -1 || envelopes[currentIndex][1] > envelopes[prevIndex][1]) {
            include = 1 + calculateMaxEnvelopes(envelopes, currentIndex + 1, currentIndex, memory);
        }

        int exclude = calculateMaxEnvelopes(envelopes, currentIndex + 1, prevIndex, memory);

        int max = Math.max(include, exclude);
        memory[currentIndex][prevIndex + 1] = max;
        return max;
    }


    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            else
                return a[0] - b[0];
        });

        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }

    public static void main(String[] args) {

        RussianDollEnvelopes envelopes = new RussianDollEnvelopes();
       /* System.out.println(envelopes.maxEnvelopes(new int[][]{
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        }));*/

        /*System.out.println(envelopes.maxEnvelopesTab(new int[][]{
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        }));*/

       /* System.out.println(envelopes.maxEnvelopesTab(new int[][]{
                {1, 1}, {1, 1}, {1, 1}
        }));*/

        /*System.out.println(envelopes.maxEnvelopesTabUsingBinarySearch(new int[][]{
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        }));*/

        System.out.println(envelopes.maxEnvelopesTabUsingBinarySearch(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {3, 5}, {4, 5}, {5, 5}, {5, 6}, {6, 7}, {7, 8}
        })); // 7

        System.out.println(envelopes.maxEnvelopes2(new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {3, 5}, {4, 5}, {5, 5}, {5, 6}, {6, 7}, {7, 8}
        })); // 7

        System.out.println(envelopes.maxEnvelopesTabUsingBinarySearch(new int[][]{
                {1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}
        })); // 3


        System.out.println(envelopes.maxEnvelopesTabUsingBinarySearch(new int[][]{
                {1, 1}, {1, 1}, {1, 1}
        }));

        System.out.println(envelopes.maxEnvelopesTabUsingBinarySearch(new int[][]{
                {4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}
        }));

        System.out.println(envelopes.maxEnvelopesTabUsingBinarySearch(new int[][]{
                {4, 5}, {6, 7}, {2, 3}
        }));
    }

}

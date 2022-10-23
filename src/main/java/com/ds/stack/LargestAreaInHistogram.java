package com.ds.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
//https://www.youtube.com/watch?v=lJLcqDsmYfg
public class LargestAreaInHistogram {

    public int largestRectangleArea(int[] heights) {

        if (heights.length == 1) {
            return heights[0];
        }

        int[] nextSmaller = nextSmallerElementIndex(heights);
        int[] prevSmaller = prevSmallerElementIndex(heights);

        int maxArea = Integer.MIN_VALUE;
        for (int currIndex = 0; currIndex < heights.length; currIndex++) {
            int backSideLength = prevSmaller[currIndex] == -1 ? 0 : prevSmaller[currIndex] + 1;
            int nextSideLength = nextSmaller[currIndex] == -1 ? heights.length - 1 : nextSmaller[currIndex] - 1;

            int totalLength = nextSideLength - backSideLength + 1;
            int area = totalLength * heights[currIndex];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }


    private int[] nextSmallerElementIndex(int[] arr) {
        // Write your code here.
        int[] ansList = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = arr.length - 1; i >= 0; i--) {
            int current = arr[i];
            while (stack.peek() != -1 && arr[stack.peek()] >= current) { // second is value
                stack.pop();
            }
            int ans = stack.peek();
            stack.push(i);
            ansList[i] = ans;
        }
        return ansList;
    }

    private int[] prevSmallerElementIndex(int[] arr) {
        // Write your code here.
        int[] ansList = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < arr.length; i++) {
            int current = arr[i];
            while (stack.peek() != -1 && arr[stack.peek()] >= current) { // second is value
                stack.pop();
            }
            int ans = stack.peek();
            stack.push(i);
            ansList[i] = ans;
        }
        return ansList;
    }

    private ArrayList<Integer> getNextMinimumElementIndex(ArrayList<Integer> containers) {
        int size = containers.size();

        LinkedList<Pair> stack = new LinkedList<>();
        stack.push(new Pair(-1, -1));// for last element
        ArrayList<Integer> minElementIndex = new ArrayList<>();

        for (int i = size - 1; i >= 0; i--) {
            int currElement = containers.get(i);

            while (stack.peek().index != -1 && stack.peek().element >= currElement) {
                stack.pop();
            }
            int ans = stack.peek().index;
            stack.push(new Pair(currElement, i));
            minElementIndex.add(0, ans);
        }
        return minElementIndex;
    }

    private ArrayList<Integer> getPrevMinimumElementIndex(ArrayList<Integer> containers) {
        int size = containers.size();

        LinkedList<Pair> stack = new LinkedList<>();
        stack.push(new Pair(-1, -1));// for last element
        ArrayList<Integer> minElementIndex = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int currElement = containers.get(i);

            while (stack.peek().index != -1 && stack.peek().element >= currElement) {
                stack.pop();
            }
            int ans = stack.peek().index;
            stack.push(new Pair(currElement, i));
            minElementIndex.add(ans);
        }
        return minElementIndex;
    }

    static class Pair {
        int element;
        int index;

        public Pair(int element, int index) {
            this.element = element;
            this.index = index;
        }
    }

    public static void main(String[] args) {

        LargestAreaInHistogram area = new LargestAreaInHistogram();
        int[] ints1 = {2, 1, 5, 6, 2, 3};

        System.out.println(area.largestRectangleArea(ints1));//10
        System.out.println(area.largestRectangleArea(new int[]{0}));//0
        System.out.println(area.largestRectangleArea(new int[]{0, 9}));//9


        System.out.println(Arrays.toString(area.nextSmallerElementIndex(ints1))); //1, -1, 4, 4, -1, -1

        ArrayList<Integer> ints11 = new ArrayList<>();
        Arrays.stream(ints1).forEach(ints11::add);

        System.out.println(area.getNextMinimumElementIndex(ints11)); //1, -1, 4, 4, -1, -1

        System.out.println(Arrays.toString(area.prevSmallerElementIndex(ints1))); //[-1, -1, 1, 2, 1, 4]
        System.out.println(area.getPrevMinimumElementIndex(ints11)); //[-1, -1, 1, 2, 1, 4]

        System.out.println(Arrays.toString(area.nextSmallerElementIndex(new int[]{1, 5, 4, 3}))); //
        System.out.println(Arrays.toString(area.prevSmallerElementIndex(new int[]{1, 5, 4, 3}))); //


        int[] ints2 = {47, 69, 67, 97, 86, 34, 98, 16, 65, 95, 66, 69, 18, 1, 99, 56, 35, 9, 48, 72, 49, 47, 1, 72, 87, 52, 13, 23, 95, 55, 21, 92, 36, 88, 48, 39, 84, 16, 15, 65, 7, 58, 2, 21, 54, 2, 71, 92, 96, 100, 28, 31, 24, 10, 94, 5, 81, 80, 43, 35, 67, 33, 39, 81, 69, 12, 66, 87, 86, 11, 49, 94, 38, 44, 72, 44, 18, 97, 23, 11, 30, 72, 51, 61, 56, 41, 30, 71, 12, 44, 81, 43, 43, 27};
        System.out.println(area.largestRectangleArea(ints2));

        ArrayList<Integer> ints22 = new ArrayList<>();
        Arrays.stream(ints2).forEach(ints22::add);


        System.out.println(Arrays.toString(area.prevSmallerElementIndex(ints2)));
        System.out.println(area.getPrevMinimumElementIndex(ints22));

        System.out.println(Arrays.toString(area.nextSmallerElementIndex(ints2)));
        System.out.println(area.getNextMinimumElementIndex(ints22));


    }
}

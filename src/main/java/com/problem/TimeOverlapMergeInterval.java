package com.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class TimeOverlapMergeInterval {

    @Data
    @AllArgsConstructor
    static class Interval {
        int start;
        int end;
    }


    private static Interval[] timings = new Interval[4];


    public static Stack<Interval> getOverlappedTiming() {

        Arrays.sort(timings, Comparator.comparingInt(o -> o.start));

        Stack<Interval> stack = new Stack<>();

        stack.push(timings[0]);

        for (int i = 1; i < timings.length; i++) {

            Interval top = stack.peek();

            if (top.end < timings[i].start) {
                stack.push(timings[i]);
            } else if (top.end < timings[i].end) {
                top.end = timings[i].end;
                stack.pop();
                stack.push(top);
            }
        }

        return stack;
    }


    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (stack.peek()[1] >= interval[0]) {
                if (stack.peek()[1] < interval[1]) {
                    stack.peek()[1] = interval[1];
                }
            } else {
                stack.push(interval);
            }
        }

        return stack.toArray(new int[stack.size()][2]);
    }

    public static void main(String[] args) {
        timings[0] = new Interval(9, 15);
        timings[1] = new Interval(10, 16);
        timings[2] = new Interval(17, 18);
        timings[3] = new Interval(18, 19);

        System.out.println(getOverlappedTiming());


        System.out.println("-----------");

        TimeOverlapMergeInterval mergeInterval = new TimeOverlapMergeInterval();

        int[][] result1 = mergeInterval.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        //[[1,6],[8,10],[15,18]]
        for (int[] ints : result1) {
            System.out.println(Arrays.toString(ints));
        }
    }

}

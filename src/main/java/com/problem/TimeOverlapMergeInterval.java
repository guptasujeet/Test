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


    public Stack<Interval> getOverlappedTiming(Interval[] timings) {

        //first sort by start time
        Arrays.sort(timings, Comparator.comparingInt(o -> o.start));

        Stack<Interval> stack = new Stack<>();
        //put first interval in stack
        stack.push(timings[0]);

        //loop from index 1 , as 0 already in stack
        for (int i = 1; i < timings.length; i++) {
            Interval top = stack.peek();
            //if prev end is less than current start
            //push to stack
            if (top.end < timings[i].start) {
                stack.push(timings[i]);
            } else if (top.end < timings[i].end) {
                //if previous end is less that current end
                //update previous end to current end
                //and previous stat will remain same
                top.end = timings[i].end;
                //remove it from the stack
                stack.pop();
                //and push it again , so that comparator will work properly
                stack.push(top);
            }
        }

        return stack;
    }


    public int[][] merge(int[][] intervals) {

        //first sort the intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        Stack<int[]> stack = new Stack<>();
        //push the first intervals
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            //check if next interval end time is greater than
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
        TimeOverlapMergeInterval mergeInterval = new TimeOverlapMergeInterval();

        final Interval[] timings = new Interval[4];

        timings[0] = new Interval(9, 15);
        timings[1] = new Interval(10, 16);
        timings[2] = new Interval(17, 18);
        timings[3] = new Interval(18, 19);

        System.out.println(mergeInterval.getOverlappedTiming(timings));

        System.out.println("-----------");

        int[][] result1 = mergeInterval.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        //[[1,6],[8,10],[15,18]]
        for (int[] ints : result1) {
            System.out.println(Arrays.toString(ints));
        }
    }

}

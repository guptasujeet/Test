package com.problem;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class TimeOverlapMergeIntervalLocalDate {

    @Data
    @AllArgsConstructor
    static class Interval {
        LocalDateTime start;
        LocalDateTime end;
    }


    public Stack<Interval> getOverlappedTiming(Interval[] timings) {

        //first sort by start time
        Arrays.sort(timings, Comparator.comparing(o -> o.start));

        Stack<Interval> stack = new Stack<>();
        //put first interval in stack
        stack.push(timings[0]);

        //loop from index 1 , as 0 already in stack
        for (int i = 1; i < timings.length; i++) {
            Interval top = stack.peek();
            //if prev end is less than current start
            //push to stack
            if (top.end.isBefore(timings[i].start)) {
                stack.push(timings[i]);
            } else if (top.end.isBefore(timings[i].end)) {
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


    public static void main(String[] args) {
        TimeOverlapMergeIntervalLocalDate mergeInterval = new TimeOverlapMergeIntervalLocalDate();

        final Interval[] timings = new Interval[4];
        LocalDate today = LocalDate.now();
        timings[0] = new Interval(LocalDateTime.of(today, LocalTime.of(9, 0)), LocalDateTime.of(today, LocalTime.of(15, 0)));
        timings[1] = new Interval(LocalDateTime.of(today, LocalTime.of(10, 0)), LocalDateTime.of(today, LocalTime.of(16, 0)));
        timings[2] = new Interval(LocalDateTime.of(today, LocalTime.of(17, 0)), LocalDateTime.of(today, LocalTime.of(18, 0)));
        timings[3] = new Interval(LocalDateTime.of(today, LocalTime.of(18, 0)), LocalDateTime.of(today, LocalTime.of(19, 0)));

        mergeInterval.getOverlappedTiming(timings).forEach(System.out::println);

        System.out.println("-----------");
    }

}

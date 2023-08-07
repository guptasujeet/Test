package com.ds.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


//https://www.youtube.com/watch?v=RrxpTWqj97A&list=PLDzeHZWIZsToMrLX5-7qNyS6Db9VwTSvm&index=4
// https://www.interviewbit.com/blog/find-median-in-a-stream/

/*
    The median is the middle value of a sorted list of integers.
    If the size of the list is even, the median is the average of the two middle elements.
 */
public class MedianInAStream {

    public static int[] findMedian(int[] arr, int n) {
        //left and right can be considered as max heap and right as min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(e -> (int) e).reversed());

        int[] ans = new int[arr.length];
        int median = 0;

        for (int i = 0; i < n; i++) {
            int element = arr[i];
            median = calcMedian(minHeap, maxHeap, element, median);
            ans[i] = median;
        }
        return ans;
    }


    public static int calcMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap,
                                 int element, int median) {

        if (minHeap.size() == maxHeap.size()) {
            if (element < median) {
                maxHeap.add(element);
                return maxHeap.peek();
            } else {
                minHeap.add(element);
                return minHeap.peek();
            }
        } else if (minHeap.size() < maxHeap.size()) {
            if (element < median) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(element);
            } else {
                minHeap.add(element);
            }
        } else if (minHeap.size() > maxHeap.size()) { //always true
            if (element < median) {
                maxHeap.add(element);
            } else {
                maxHeap.add(minHeap.poll());
                minHeap.add(element);
            }
        }
        return (minHeap.peek() + maxHeap.peek()) / 2;
    }


    public static void main(String[] args) {

        int[] data = new int[]{1, 7, 4, 0, 9};

        System.out.println(Arrays.toString(findMedian(data, data.length))); //[1, 4, 4, 2, 4]
    }

}

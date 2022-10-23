package com.problem;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestLargestElement {


    private static int getKthLastElement(int[] data, int k) {
        PriorityQueue<Integer> priorityQueue = processDataInMaxHeap(data, k);

        return priorityQueue.peek();
    }

    private static Integer[] getKLastElements(int[] data, int k) {
        //zero indexing

        PriorityQueue<Integer> priorityQueue = processDataInMaxHeap(data, k);
        Integer[] lastElements = new Integer[k];
        return priorityQueue.toArray(lastElements);
    }

    //priority queue will act as max heap
    private static PriorityQueue<Integer> processDataInMaxHeap(int[] data, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < data.length; i++) {
            if (i < k) {
                priorityQueue.add(data[i]);
            } else {
                if (priorityQueue.peek() > data[i]) {
                    priorityQueue.poll();
                    priorityQueue.add(data[i]);
                }
            }
        }
        return priorityQueue;
    }


    public static void main(String[] args) {
        int[] data = {10, 6, 20, 4, 8, 15, 25, 7, 12};
        System.out.println(getKthLastElement(data, 4));
        System.out.println(Arrays.toString(getKLastElements(data, 4)));
    }


}

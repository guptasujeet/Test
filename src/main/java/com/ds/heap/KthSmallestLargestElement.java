package com.ds.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestLargestElement {


    private static int getKthLargestElement(int[] data, int k) {
        PriorityQueue<Integer> priorityQueue = processDataInMaxHeap(data, k);

        return priorityQueue.peek();
    }

    private static Integer[] getKSmallestElements(int[] data, int k) {
        //zero indexing

        PriorityQueue<Integer> priorityQueue = processDataInMaxHeap(data, k);
        Integer[] lastElements = new Integer[k];
        return priorityQueue.toArray(lastElements);
    }

    //priority queue will act as max heap
    private static PriorityQueue<Integer> processDataInMaxHeap(int[] data, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < data.length; i++) {
            //for all elements till k put them in queue
            if (i < k) {
                priorityQueue.add(data[i]);
            } else {
                // for more elements check if data in queue top is greater than
                // current then remove that and add current
                // else do nothing
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
        System.out.println(getKthLargestElement(data, 4)); //8
        System.out.println(Arrays.toString(getKSmallestElements(data, 4))); //[8, 7, 4, 6]

        System.out.println(getKthLargestElement(data, 6)); //12
        System.out.println(Arrays.toString(getKSmallestElements(data, 6))); //12, 8, 10, 4, 6, 7]
    }


}

package com.ds.queue;

import java.util.Arrays;

//https://www.geeksforgeeks.org/efficiently-implement-k-queues-single-array/
public class KQueues {


    private final int[] arr;
    private final int[] next;
    private final int[] front;
    private final int[] rear;

    private int freeSpot;


    public KQueues(int k, int n) {
        this.arr = new int[n];
        this.next = new int[n];
        this.front = new int[k];
        this.rear = new int[k];

        this.freeSpot = 0;

        Arrays.fill(front, -1);
        Arrays.fill(rear, -1);

        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                next[i] = -1;
            } else {
                next[i] = i + 1;
            }
        }
    }


    private void enqueue(int data, int qNum) {
        if (freeSpot != -1) {
            int index = freeSpot;
            if (front[qNum] == -1) {
                front[qNum] = index;
            } else {
                next[rear[qNum]] = freeSpot;
            }
            rear[qNum] = index;
            arr[index] = data;
            freeSpot = next[index];
            next[index] = -1;
        }
    }

    private int dequeue(int qNum) {
        int qFront = front[qNum];
        int qRear = rear[qNum];
        if (qFront == -1 || qFront > qRear) {
            return -1;
        }

        int data = arr[qFront];
        front[qNum] = next[qFront];
        next[qFront] = freeSpot;
        freeSpot = qFront;
        return data;
    }

    public static void main(String[] args) {
        // Let us create 3 queue in an array of size 10
        int k = 3, n = 10;
        KQueues ks = new KQueues(k, n);


        // Let us put some items in queue number 2
        ks.enqueue(15, 2);
        ks.enqueue(45, 2);

        // Let us put some items in queue number 1
        ks.enqueue(17, 1);
        ks.enqueue(49, 1);
        ks.enqueue(39, 1);

        // Let us put some items in queue number 0
        ks.enqueue(11, 0);
        ks.enqueue(9, 0);
        ks.enqueue(7, 0);

        System.out.println("Dequeued element from queue 2 is " + ks.dequeue(2));
        System.out.println("Dequeued element from queue 1 is " + ks.dequeue(1));
        System.out.println("Dequeued element from queue 0 is " + ks.dequeue(0));
        System.out.println("Dequeued element from queue 2 is " + ks.dequeue(2));


        //Dequeued element from queue 2 is 15
        //Dequeued element from queue 1 is 17
        //Dequeued element from queue 0 is 11
        //Dequeued element from queue 2 is 45
    }


}

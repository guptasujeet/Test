package com.ds.queue;

//https://www.codingninjas.com/codestudio/problems/queue-using-array-or-singly-linked-list_2099908

import java.util.Arrays;

public class CustomQueue {


    private final int SIZE = 10000;
    int[] arr;
    int front = 0;
    int rear = 0;

    CustomQueue() {
        // Implement the Constructor
        arr = new int[SIZE];
        Arrays.fill(arr, -1);
    }

    /*----------------- Public Functions of Queue -----------------*/

    boolean isEmpty() {
        // Implement the isEmpty() function
        return front == rear;
    }

    void enqueue(int data) {
        // Implement the enqueue() function
        if (isEmpty()) {
            front = 0;
            rear = 0;
        }
        if (rear < SIZE) {
            arr[rear++] = data;
        }
    }

    int dequeue() {
        // Implement the dequeue() function
        if (!isEmpty()) {
            int temp = arr[front];
            arr[front] = -1;
            front++;
            return temp;
        }
        return -1;
    }

    int front() {
        // Implement the front() function
        return arr[front];
    }


    public static void main(String[] args) {

        CustomQueue queue = new CustomQueue();
        queue.enqueue(17);
        queue.enqueue(23);
        queue.enqueue(11);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

    }
}

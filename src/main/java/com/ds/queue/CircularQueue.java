package com.ds.queue;

//https://www.codingninjas.com/codestudio/problems/circular-queue_1170058?
//https://www.youtube.com/watch?v=W7uB9-TKfTg&list=PLDzeHZWIZsTrhXYYtx4z8-u8zA-DzuVsj&index=7
public class CircularQueue {
    // Initialize your data structure.

    private final int[] arr;
    private int front = 0;
    private int rear = 0;

    public CircularQueue(int n) {
        this.arr = new int[n + 1];
    }

    /*
       Enqueues 'X' into the queue. Returns true if it gets pushed into the stack,
       and false otherwise.
    */
    public boolean enqueue(int value) {
        // Write your code here.

        //check if not full
        if (isFull()) {
            return false;
        }
        arr[rear++] = value;
        if (rear == arr.length && front != 0) {
            rear = 0;
        }
        return true;

    }

    private boolean isFull() {
        if (front == 0 && rear == arr.length - 1) {
            return true;
        }
        if (front == (rear + 1) % (arr.length)) {
            return true;
        }
        return false;
    }

    private boolean isEmpty() {
        if (front == rear) {
            return true;
        }
        return false;
    }

    /*
      Dequeues top element from queue. Returns -1 if the stack is empty, otherwise
      returns the popped element.
    */
    public int dequeue() {
        // Write you code here.
        if (isEmpty()) {
            return -1;
        }
        if (front == arr.length && rear != 0) {
            front = 0;
        }
        return arr[front++];
    }


    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(3);
        System.out.println(circularQueue.enqueue(2));
        System.out.println(circularQueue.enqueue(3));
        System.out.println(circularQueue.dequeue());
        System.out.println(circularQueue.enqueue(4));
        System.out.println(circularQueue.enqueue(6));
        System.out.println(circularQueue.enqueue(7));
        System.out.println(circularQueue.dequeue());
    }
}

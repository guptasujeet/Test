package com.concurrency.synchronization;


public class SynchronizedAddSubtract {
    //private volatile long counter = 0; //volatile not working
    private volatile int counter = 0; // volatile not working

    public static void main(String[] args) throws InterruptedException {

        SynchronizedAddSubtract addSubtract = new SynchronizedAddSubtract();

        Thread addThread = new Thread(addSubtract.new AddTask());
        Thread subtractThread = new Thread(addSubtract.new SubtractTask());

        long startMillis = System.currentTimeMillis();
        addThread.start();
        subtractThread.start();

        addThread.join();
        subtractThread.join();

        System.out.println("Time taken in ms : " + (System.currentTimeMillis() - startMillis));
        System.out.println("Counter : " + addSubtract.counter); //should always be zero


    }

    class AddTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                add();
            }
        }
    }

    class SubtractTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                sub();
            }
        }
    }

    private /*synchronized*/ void sub() {
        counter--;
    }

    private /*synchronized*/ void add() {
        counter++;
    }

}

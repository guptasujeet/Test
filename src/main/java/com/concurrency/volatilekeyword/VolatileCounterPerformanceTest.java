package com.concurrency.volatilekeyword;

/**
 * https://www.youtube.com/watch?v=SC2jXxOPe5E
 */

public class VolatileCounterPerformanceTest {

    //comment uncomment & check time taken
    private volatile int a = 0;
    /*private int dummy1 = 0;
    private int dummy2 = 0;
    private int dummy3 = 0;
    private int dummy4 = 0;
    private int dummy5 = 0;
    private int dummy6 = 0;
    private int dummy7 = 0;
    private int dummy8 = 0;*/
    private volatile int b = 0;


    public static void main(String[] args) throws InterruptedException {
        VolatileCounterPerformanceTest instance = new VolatileCounterPerformanceTest();
        Thread runnerA = new Thread(instance.new RunnerA());
        Thread runnerB = new Thread(instance.new RunnerB());

        runnerA.start();
        runnerB.start();

        runnerA.join();
        runnerB.join();
    }

    class RunnerA implements Runnable {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 100_000_000; i++) {
                a++;
            }
            System.out.println("Time taken Runner A : " + (System.currentTimeMillis() - startTime) + " ms, counter :  " + a);
        }
    }

    class RunnerB implements Runnable {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 100_000_000; i++) {
                b++; //setting a to 0
            }
            System.out.println("Time taken Runner B : " + (System.currentTimeMillis() - startTime) + " ms, counter : " + b);
        }
    }

}

package com.concurrency.volatilekeyword;

/**
 * https://www.youtube.com/watch?v=SC2jXxOPe5E
 */

public class VolatileRunningThreadExample {

    private /*volatile*/ boolean running = false;

    public static void main(String[] args) throws InterruptedException {

        VolatileRunningThreadExample instance = new VolatileRunningThreadExample();
        Thread thread = new Thread(instance.new IndefiniteRunner());

        thread.start();

        System.out.println("Main Thread Triggered Runner ");
        Thread.sleep(1000L);
        instance.running = true;
        System.out.println("Main Thread Set Running : true ");
        Thread.sleep(1000L);
        instance.running = false;
        System.out.println("Main Thread Set Running : false ");

        //without volatile runner will continue to run

    }

    class IndefiniteRunner implements Runnable {
        @Override
        public void run() {
            System.out.println("Inside Run method waiting for updated running status ");

            while (!running) {
                //wait for running to become true
            }

            System.out.println("Thread received running status ");

            while (running) {
                //do nothing
                System.out.println("Running ");
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //after running print below
            System.out.println("Thread received running status, now stopping ");
        }
    }

}

package com.concurrency.volatilekeyword;


import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.TimeUnit.SECONDS;

public class VolatileThreadStopTest {

    public static void main(String[] args) {

        MyTask task = new MyTask();

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        //making current thread sleep for sometimes
        /*try {
            Thread.sleep(SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //now setting running to false, thread should stop after this
        task.setRunning(false);

        System.out.println("After setting false");

        try {
            Thread.sleep(SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Exiting ");

    }


    static class MyTask implements Runnable {

        private /*volatile */ boolean running = true;
        //private long counter = 0;
        private AtomicLong counter = new AtomicLong();

        @Override
        public void run() {
            while (running) {
                System.out.println(counter.incrementAndGet());
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " : stopped @ " + counter);
        }


        public void setRunning(boolean running) {
            this.running = running;
        }
    }

}

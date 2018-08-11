package com.concurrency.synchronization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Sujeet on 22/10/17.
 */
public class ProducerConsumer1 {

    private static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {

        Queue<Integer> queue = new LinkedList<>();
        Thread producer = new Thread(new Producer1(queue));
        Thread consumer = new Thread(new Consumer1(queue));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }


    static class Producer1 implements Runnable {

        private final Random random = new Random();

        private final Queue<Integer> queue;

        Producer1(Queue<Integer> queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            int value = 0;
            while (true) {
                //System.out.println("inside producer");
                synchronized (queue) {
                    while (queue.size() == SIZE) {
                        System.out.println("producer waiting");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producer produced " + ++value);
                    queue.add(value);
                    queue.notifyAll();
                }

                try {
                    //System.out.println("producer sleeping");
                    Thread.sleep(random.nextInt(1000)); //slow down producer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer1 implements Runnable {

        private final Random random = new Random();

        private final Queue<Integer> queue;

        Consumer1(Queue<Integer> queue) {
            this.queue = queue;
        }


        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        System.out.println("consumer waiting");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Integer poll = queue.poll();
                    System.out.println("consumer got value " + poll);
                    queue.notifyAll();
                }
                try {
                    //System.out.println("consumer sleeping");
                    Thread.sleep(random.nextInt(2000)); //slow down consumer
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}

package com.concurrency.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerCondition {

    private final Lock lock = new ReentrantLock(true);
    private final Condition isEmpty = lock.newCondition();
    private final Condition isFull = lock.newCondition();
    private final List<Integer> buffer = new ArrayList<>();
    private final Random random = new Random();


    public static void main(String[] args) {

        ProducerConsumerCondition demo = new ProducerConsumerCondition();

        Thread producer = new Thread(demo::produce);
        Thread consumer = new Thread(demo::consume);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void produce() {
        int MAX_ELEMENT = 10;
        while (true) {
            try {
                System.out.println("Producer waiting for lock");
                lock.lock();
                System.out.println("Producer acquired lock");
                while (buffer.size() == MAX_ELEMENT) {
                    System.out.println("Producer is waiting");
                    isFull.await();
                    System.out.println("Producer waiting done");
                }
                int data = random.nextInt(10);
                System.out.println("Producer added data : " + data);
                buffer.add(data);
                isEmpty.signalAll();
                System.out.println("Producer signalled");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Producer released lock");
                lock.unlock();
                //lock released consumer will get invocation here
                try {
                    System.out.println("Producer sleeping");
                    TimeUnit.SECONDS.sleep(3); //intentionally sleeping before releasing lock
                    System.out.println("Producer sleeping done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void consume() {
        while (true) {
            try {
                System.out.println("Consumer waiting for lock");
                lock.lock();
                System.out.println("Consumer acquired lock");
                while (buffer.isEmpty()) {
                    System.out.println("Consumer is waiting");
                    isEmpty.await();
                    System.out.println("Consumer waiting done");
                }
                int data = buffer.remove(0);
                System.out.println("Consumer consumed data : " + data);
                isFull.signalAll();
                System.out.println("Consumer signalled");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Consumer released lock");
                lock.unlock();
                //lock release producer will get invocation here
                try {
                    System.out.println("Consumer sleeping");
                    TimeUnit.SECONDS.sleep(4); //intentionally sleeping before releasing lock
                    System.out.println("Consumer sleeping done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

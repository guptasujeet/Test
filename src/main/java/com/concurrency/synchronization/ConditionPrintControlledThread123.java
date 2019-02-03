package com.concurrency.synchronization;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionPrintControlledThread123 {


    private final Lock lock = new ReentrantLock(true);
    private final Condition onePrinter = lock.newCondition();
    private final Condition twoPrinter = lock.newCondition();
    private final Condition threePrinter = lock.newCondition();
    private int number = 0;

    public static void main(String[] args) throws InterruptedException {
        ConditionPrintControlledThread123 demo = new ConditionPrintControlledThread123();
        Thread oneModPrinter = new Thread(demo::printOneMod);
        oneModPrinter.setName("One Mod Printer ");

        Thread twoModPrinter = new Thread(demo::printTwoMod);
        twoModPrinter.setName("Two Mod Printer ");

        Thread threeModPrinter = new Thread(demo::printThreeMod);
        threeModPrinter.setName("Three Mod Printer ");

        CountDownLatch latch = new CountDownLatch(1);

        oneModPrinter.start();
        twoModPrinter.start();
        threeModPrinter.start();

        latch.await(); //just wait


    }

    private void printOneMod() {
        while (true) {
            try {
                lock.lock();
                while (number % 3 == 1) {
                    onePrinter.await();
                }
                int i = ++number;
                twoPrinter.signalAll();
                printThreadAndNumber(i);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void printTwoMod() {
        while (true) {
            try {
                lock.lock();
                while (number % 3 == 2) {
                    twoPrinter.await();
                }
                int i = ++number;
                threePrinter.signalAll();
                printThreadAndNumber(i);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void printThreeMod() {
        while (true) {
            try {
                lock.lock();
                while (number % 3 == 0) {
                    threePrinter.await();
                }
                int i = ++number;
                onePrinter.signalAll();
                printThreadAndNumber(i);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }


    private void printThreadAndNumber(int localNum) {
        System.out.println("Thread : " + Thread.currentThread().getName() + "\t : " + localNum);
    }


}

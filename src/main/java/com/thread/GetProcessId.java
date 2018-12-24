package com.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sujeet on 24/12/18.
 */
public class GetProcessId {
    /**
     * use below command to get processId of individual thread
     * <p>
     * jstack -l <PID> | grep MyThread | grep nid
     * exmpale
     * jstack -l 18990| grep MyThread | grep nid
     * jstack -l 18990| grep MyThread | grep nid | awk -F'nid=| runnable' '{printf "%d\n",$2}'
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Test Started");
        //trying to give same name to all three threads
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("MyThread").build();
        ExecutorService executorService = Executors.newFixedThreadPool(3, threadFactory);
        executorService.submit(GetProcessId::printProcessId);
        executorService.submit(GetProcessId::printProcessId);
        executorService.submit(GetProcessId::printProcessId);

        executorService.shutdown();

        //main thread
        printProcessId();
        System.out.println("Done");
    }

    private static void printProcessId() {
        try {
            String threadName = Thread.currentThread().getName();
            String name = ManagementFactory.getRuntimeMXBean().getName();
            System.out.println("Name : " + name);
            String processId = name.split("@")[0];

            System.out.println("ProcessId : " + processId + " \t Thread : " + threadName);

            TimeUnit.SECONDS.sleep(10);

            System.out.println("ProcessId : " + processId + " \t Thread : " + threadName);

            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

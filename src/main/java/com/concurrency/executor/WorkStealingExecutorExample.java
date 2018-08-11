package com.concurrency.executor;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.concurrency.executor.WorkStealingExecutorExample.logError;
import static com.concurrency.executor.WorkStealingExecutorExample.logMessage;

//example to process data in queue without thread dying

public class WorkStealingExecutorExample {

    private static final BlockingQueue<Integer> QUEUE = new LinkedBlockingQueue<>(100);
    private static final int NUM_OF_TASKS = 100;

    public static void main(String[] args) {

        WorkProcessor processor = new WorkProcessor(QUEUE);
        processor.initWorkers();
        logMessage("started submitting tasks");
        for (int i = 0; i < NUM_OF_TASKS; i++) {
            try {
                QUEUE.put(i);
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logMessage("all tasks submitted");
        processor.stopSubmissionAndWaitForCompletion();
        logMessage("Everything finished ");

    }

    static void logMessage(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " : " + msg);
    }

    static void logError(String msg, Throwable th) {
        String threadName = Thread.currentThread().getName();
        System.err.println(threadName + " : " + msg);
        th.printStackTrace();
    }


}


class WorkProcessor {

    private static final int NUM_THREAD = 4;

    private volatile boolean submissionInProgress = true;

    private final BlockingQueue<Integer> queue;
    private final ExecutorService executor;

    private final AtomicReference<Integer> anyOneValue = new AtomicReference<>(); //just testing atomic reference


    WorkProcessor(BlockingQueue<Integer> queue) {
        this.queue = queue;
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        builder.setNameFormat("Thread-%d");
        builder.setUncaughtExceptionHandler((t, e) -> logError("Error \t" + e.getMessage(), e));
        executor = Executors.newFixedThreadPool(NUM_THREAD, builder.build());
    }

    void initWorkers() {
        logMessage("starting workers");
        for (int i = 0; i < NUM_THREAD; i++) {
            executor.execute(this::createWorker);
        }
        logMessage("all workers started");
    }


    private void createWorker() {
        logMessage("started working");
        while (submissionInProgress || !queue.isEmpty()) {
            Integer value = null;
            try {
                value = queue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (value == null) {
                logMessage(" timeout continuing ");
                continue;
            }
            if (anyOneValue.get() == null) {
                logMessage("Any one value is null setting : " + value);
                anyOneValue.set(value);
            }
            doProcessingAllTask(value);
            //doProcessingThreadsDie(value);
        }
        logMessage(" finished processing");
    }

    public void stopSubmissionAndWaitForCompletion() {
        submissionInProgress = false;
        executor.shutdown();
        logMessage("SubmissionInProgress = false");
        try {
            logMessage("Waiting for executor to finish");
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            logError("", e);
        }
    }

    private void doProcessingThreadsDie(Integer value) {
        try {
            int processedValue = (value % 5);
            logMessage(" Processing " + value + ", sleeping for seconds " + processedValue);
            Thread.sleep(value % processedValue * 1000L);
        } catch (InterruptedException e) { //todo : here handling specific exception
            logError("", e);
        }
    }

    private void doProcessingAllTask(Integer value) {
        try {
            int processedValue = (value % 5);
            logMessage(" Processing " + value + ", sleeping for seconds " + processedValue);
            Thread.sleep(value % processedValue * 1000L);
        } catch (Throwable e) { //todo: here throwable
            logError("", e);
        }
    }


}



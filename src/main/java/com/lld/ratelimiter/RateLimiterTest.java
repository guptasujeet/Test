package com.lld.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterTest {

    public static void main(String[] args) throws InterruptedException {


        RateLimiter rateLimiter = new RateLimiter();

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        AtomicInteger allowedRequest = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i <= 30000; i++) {
            int counter = i;
            executorService.submit(() -> {
                /*try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                boolean allowed = rateLimiter.isAllowed("Client1", "testApi");
                if (allowed) {
                    allowedRequest.incrementAndGet();
                }
                System.out.println(counter + " -> " + allowed);
            });
        }


        executorService.shutdown();

        executorService.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();

        System.out.println("-----------");
        System.out.println("Total allowed requests -> " + allowedRequest + " time -> " + (end - start) + " ms");
        System.out.println("-----------");

    }

}

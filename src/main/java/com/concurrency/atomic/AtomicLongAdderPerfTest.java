package com.concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Sujeet on 04/02/18.
 */
public class AtomicLongAdderPerfTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Count Started");
        AtomicLongContainer atomicLongContainer = new AtomicLongContainer();
        LongAdderContainer longAdderContainer = new LongAdderContainer();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            //executorService.submit(atomicLongContainer::incrementBy);
            executorService.submit(longAdderContainer::incrementBy);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();

        System.out.println("Total time taken in ms :" + (end - start));
        //System.out.println("Final value is : " + atomicLongContainer.getCounts()); // 80 to 100 seconds
        System.out.println("Final value is : " + longAdderContainer.getCounts()); // 17 to 20 seconds


    }


}


class LongAdderContainer {
    private LongAdder counter = new LongAdder();


    public Long getCounts() {
        return counter.sum();
    }

    public void incrementBy() {
        for (int i = 0; i < 1_000_000_000; i++) {
            counter.add(1);
        }
    }
}

class AtomicLongContainer {

    private AtomicLong counter = new AtomicLong();


    public Long getCounts() {
        return counter.get();
    }

    public void incrementBy() {
        for (int i = 0; i < 1_000_000_000; i++) {
            counter.incrementAndGet();
        }
    }


}
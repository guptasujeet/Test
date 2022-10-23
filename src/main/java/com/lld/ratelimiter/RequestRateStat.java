package com.lld.ratelimiter;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

public class RequestRateStat {

    //private final NavigableMap<Long, AtomicInteger> rateStats = new TreeMap<>(Comparator.reverseOrder());
    //private final ConcurrentSkipListMap<Long, AtomicInteger> rateStats = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
    private final ConcurrentSkipListMap<Long, Integer> rateStats = new ConcurrentSkipListMap<>(Comparator.reverseOrder());

    private final Rule rule;
    private final long slidingTime;


    public RequestRateStat(Rule rule) {
        this.rule = rule;
        this.slidingTime = rule.getTimeUnit().toSeconds(1);
    }

    //public synchronized boolean isRequestAllowed(Long timeMillis) {
    public boolean isRequestAllowed(Long timeMillis) {
        //storing in seconds
        Long time = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        synchronized (this) {
            evictOlderEntries(time - slidingTime);
            int requestCount = rateStats.values().stream().mapToInt(e -> e).sum();
            //int requestCount = rateStats.values().stream().mapToInt(AtomicInteger::get).sum();

            if (requestCount >= rule.getReqPerTimeUnit()) {
                return false;
            }
            //compute is not guaranteed to be thread safe hence using putIfAbsent which is thread safe
            rateStats.compute(time, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }
        //rateStats.putIfAbsent(time, new AtomicInteger(0));
        //rateStats.get(time).incrementAndGet();

        return true;
    }

    private void evictOlderEntries(Long time) {
        while (rateStats.higherEntry(time) != null) {
            rateStats.remove(rateStats.lastKey());
        }
    }


}

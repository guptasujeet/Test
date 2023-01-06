package com.lld.ratelimiter;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

public class RequestRateStat {

    private final ConcurrentSkipListMap<Long, Integer> rateStats = new ConcurrentSkipListMap<>(Comparator.reverseOrder());

    private final Rule rule;
    private final long slidingTime;


    public RequestRateStat(Rule rule) {
        this.rule = rule;
        this.slidingTime = rule.getTimeUnit().toSeconds(1);
    }

    //public synchronized boolean isRequestAllowed(Long timeMillis) {
    public boolean isRequestAllowed(long timeMillis) {
        //storing in seconds
        long time = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
        synchronized (this) {
            //remove any entry older that sliding time
            evictOlderEntries(time - slidingTime);
            //count current requests
            int requestCount = rateStats.values().stream().mapToInt(e -> e).sum();

            //if more or equal then return false
            if (requestCount >= rule.getReqPerTimeUnit()) {
                return false;
            }

            rateStats.compute(time, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }

        return true;
    }

    private void evictOlderEntries(Long time) {
        while (rateStats.higherEntry(time) != null) {
            rateStats.remove(rateStats.lastKey());
        }
    }


}

package com.lld.ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiter {


    private Map<String, Map<String, RequestRateStat>> rateLimiterStats = new ConcurrentHashMap<>();

    private RequestRateStat getRequestStats(String clientId, String apiName) {

        Rule rule = new Rule(20, TimeUnit.MINUTES);
        Map<String, RequestRateStat> clientMap = rateLimiterStats.computeIfAbsent(clientId, k -> new ConcurrentHashMap<>());
        return clientMap.compute(apiName, (k, v) -> {
            if (v == null) {
                return new RequestRateStat(rule);
            }
            return v;
        });
    }

    public boolean isAllowed(String clientId, String apiName) {

        RequestRateStat stats = getRequestStats(clientId, apiName);
        return stats.isRequestAllowed(System.currentTimeMillis());
    }


}

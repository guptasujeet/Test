package com.lld.ratelimiter;

import java.util.concurrent.TimeUnit;

public class Rule {

    private final int reqPerTimeUnit;
    private final TimeUnit timeUnit;

    public Rule(int reqPerTimeUnit, TimeUnit timeUnit) {
        this.reqPerTimeUnit = reqPerTimeUnit;
        this.timeUnit = timeUnit;
    }


    public int getReqPerTimeUnit() {
        return reqPerTimeUnit;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}

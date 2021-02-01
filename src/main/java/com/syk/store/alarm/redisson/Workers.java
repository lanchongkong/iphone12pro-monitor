package com.syk.store.alarm.redisson;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author sunyukun
 * @since 2020/12/15 6:12 下午
 */
public class Workers implements Delayed {

    private long delayTime;
    String name;

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return delayTime;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}

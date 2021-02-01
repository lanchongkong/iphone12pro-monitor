package com.syk.store.alarm;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.syk.store.alarm.redisson.FuckEventListener;
import com.syk.store.alarm.redisson.RedisDelayedQueue;

/**
 * @author sunyukun
 * @since 2020/10/29 1:44 下午
 */
@EnableScheduling
@Service
public class AddQueue {

    private final RedisDelayedQueue redisDelayedQueue;

    public AddQueue(RedisDelayedQueue redisDelayedQueue) {
        this.redisDelayedQueue = redisDelayedQueue;
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void appleStoreMonitor() {
        redisDelayedQueue.addQueue(String.valueOf(System.currentTimeMillis()), 5, TimeUnit.SECONDS, FuckEventListener.class.getName());
    }

}

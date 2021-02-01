package com.syk.store.alarm.redisson;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * redisson延迟队列
 *
 * @author sunyukun
 * @since 2020/12/11 7:57 下午
 */
@Component
public class RedisDelayedQueue {

    private final RedissonClient redissonClient;

    public RedisDelayedQueue(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 添加队列
     *
     * @param t
     *            DTO传输类
     * @param delay
     *            时间数量
     * @param timeUnit
     *            时间单位
     * @param <T>
     *            泛型
     */
    public <T> void addQueue(T t, long delay, TimeUnit timeUnit, String queueName) {
        DelayQueue<Delayed> queue = new DelayQueue<Delayed>();


        RBlockingQueue<T> blockingFairQueue = redissonClient.getBlockingQueue(queueName);
        RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        delayedQueue.offer(t, delay, timeUnit);
        delayedQueue.destroy();
    }
}

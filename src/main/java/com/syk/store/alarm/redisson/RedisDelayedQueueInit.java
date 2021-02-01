package com.syk.store.alarm.redisson;

import java.util.Map;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 初始化队列监听
 */
@Component
public class RedisDelayedQueueInit implements ApplicationContextAware {

    private final RedissonClient redissonClient;

    public RedisDelayedQueueInit(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, RedisDelayedQueueListener> map = applicationContext.getBeansOfType(RedisDelayedQueueListener.class);
        map.entrySet().forEach(taskEventListenerEntry -> {
            String listenerName = taskEventListenerEntry.getValue().getClass().getName();
            startThread(listenerName, taskEventListenerEntry.getValue());
        });
    }

    /**
     * 启动线程获取队列
     *
     * @param queueName
     *            queueName
     * @param redisDelayedQueueListener
     *            任务回调监听实现
     * @param <T>
     *            泛型
     */
    private <T> void startThread(String queueName, RedisDelayedQueueListener redisDelayedQueueListener) {
        RBlockingQueue<T> blockingFairQueue = redissonClient.getBlockingQueue(queueName);
        // 由于此线程需要常驻，可以新建线程，不用交给线程池管理
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    T t = blockingFairQueue.take();
                    new Thread(() -> redisDelayedQueueListener.invoke(t)).start();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        thread.setName(queueName);
        thread.start();
    }
}

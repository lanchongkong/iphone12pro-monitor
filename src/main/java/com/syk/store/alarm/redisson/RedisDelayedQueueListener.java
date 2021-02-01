package com.syk.store.alarm.redisson;

/**
 * 队列事件监听接口
 *
 * @author sunyukun
 * @date 2020/12/11
 */
public interface RedisDelayedQueueListener<T> {

    /**
     * 执行方法
     */
    void invoke(T t);
}
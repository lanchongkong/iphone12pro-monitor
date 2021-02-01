package com.syk.store.alarm.redisson;

import org.springframework.stereotype.Component;

/**
 * 报警记录监听器
 *
 * @author sunyukun
 * @since 2020/12/11 8:06 下午
 */
@Component
public class FuckEventListener implements RedisDelayedQueueListener<String> {

    /**
     * 处理延迟队列的消息
     *
     * @param alarmRedisBo
     *            报警参数
     */
    @Override
    public void invoke(String alarmRedisBo) {
        System.out.println("current time is :" + System.currentTimeMillis());
        System.out.println(alarmRedisBo);
    }
}

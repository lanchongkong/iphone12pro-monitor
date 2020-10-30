package com.syk.store.alarm.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sunyukun
 * @since 2020/10/29 1:51 下午
 */
@ConfigurationProperties(prefix = "syk.alarm.monitor")
@Component
public class MonitorProperties {

    /**
     * 产品信息
     */
    private List<String> item;

    /**
     * 订阅店铺地区的地址
     */
    private String address;

    /**
     * 获取钉钉token地址
     */
    private String tokenUrl;

    /**
     * 钉钉app key
     */
    private String appKey;

    /**
     * 钉钉 appSecret
     */
    private String appSecret;

    /**
     * 目标命中群聊ID
     */
    private String chatId;

    /**
     * 全部群聊ID
     */
    private String allChatId;

    /**
     * 钉钉发送消息接口地址
     */
    private String messageUrl;

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getAllChatId() {
        return allChatId;
    }

    public void setAllChatId(String allChatId) {
        this.allChatId = allChatId;
    }
}

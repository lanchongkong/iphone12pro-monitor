package com.syk.store.alarm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sunyukun
 * @since 2020/10/29 1:47 下午
 */
@ConfigurationProperties(prefix = "syk.apple.store")
@Component
public class AppStoreProperties {

    /**
     * 获取店铺信息地址
     */
    private String storeUrl;

    /**
     * 获取库存信息地址
     */
    private String availabilityUrl;

    private String cron;

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getAvailabilityUrl() {
        return availabilityUrl;
    }

    public void setAvailabilityUrl(String availabilityUrl) {
        this.availabilityUrl = availabilityUrl;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}

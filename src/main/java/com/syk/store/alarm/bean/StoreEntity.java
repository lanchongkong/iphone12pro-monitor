package com.syk.store.alarm.bean;

/**
 * @author sunyukun
 * @since 2020/10/29 2:23 下午
 */
public class StoreEntity {

    private String storeNumber;

    private String city;

    private String storeName;

    private boolean enabled;

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

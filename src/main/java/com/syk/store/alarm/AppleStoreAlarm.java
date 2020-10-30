package com.syk.store.alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.syk.store.alarm.bean.StoreEntity;
import com.syk.store.alarm.constant.ItemEnum;
import com.syk.store.alarm.properties.AppStoreProperties;
import com.syk.store.alarm.properties.MonitorProperties;
import com.taobao.api.ApiException;

/**
 * @author sunyukun
 * @since 2020/10/29 1:44 下午
 */
@EnableScheduling
@Service
public class AppleStoreAlarm {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppleStoreAlarm.class);

    private final AppStoreProperties appStoreProperties;

    private final MonitorProperties monitorProperties;

    private final RestTemplate restTemplate;

    private Map<String, StoreEntity> storeInfos = new HashMap<>();

    private List<String> locations = new ArrayList<>();

    public AppleStoreAlarm(AppStoreProperties appStoreProperties, MonitorProperties monitorProperties,
        RestTemplate restTemplate) {
        this.appStoreProperties = appStoreProperties;
        this.monitorProperties = monitorProperties;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "${syk.apple.store.cron:0/10 * * * * ?}")
    public void appleStoreMonitor() throws ApiException {
        LOGGER.info("start to monitor");
        String accessKey = this.getAccessKey();
        if (storeInfos.isEmpty()) {
            LOGGER.info("开始请求店铺信息");
            this.sendMessage("开始监听", accessKey, monitorProperties.getChatId());
            this.sendMessage("开始监听", accessKey, monitorProperties.getAllChatId());
            JSONObject store = restTemplate.getForObject(appStoreProperties.getStoreUrl(), JSONObject.class);
            List<?> stores = store.getObject("stores", List.class);
            stores.parallelStream().forEach(storeJson -> {
                StoreEntity storeEntity = JSONObject.parseObject(JSONObject.toJSONString(storeJson), StoreEntity.class);
                storeInfos.put(storeEntity.getStoreNumber(), storeEntity);
            });
        }

        if (CollectionUtils.isEmpty(locations)) {
            locations.addAll(Arrays.asList(monitorProperties.getAddress().split(",")));
        }

        JSONObject stock = restTemplate.getForObject(appStoreProperties.getAvailabilityUrl(), JSONObject.class);

        for (Map.Entry<String, StoreEntity> storeEntry : storeInfos.entrySet()) {
            JSONObject detailInfo = stock.getJSONObject("stores").getJSONObject(storeEntry.getKey());
            // 遍历12种型号
            for (ItemEnum itemEnum : ItemEnum.values()) {
                // 获取对应的信号库存信息
                JSONObject storeStock = detailInfo.getJSONObject(itemEnum.getCode()).getJSONObject("availability");
                if (storeStock.getBoolean("contract") || storeStock.getBoolean("unlocked")) {
                    // 有库存
                    String allItemMsg = String.format("产品：%s,在地区%s,店铺%s有库存！！！", itemEnum.getName(),
                        storeEntry.getValue().getCity(), storeEntry.getValue().getStoreName());
                    LOGGER.info(allItemMsg);
                    this.sendMessage(allItemMsg, accessKey, monitorProperties.getAllChatId());
                    // 对比是否命中目标
                    if (locations.contains(storeEntry.getValue().getCity())) {
                        for (String target : monitorProperties.getItem()) {
                            if (itemEnum.getName().contains(target)) {
                                String msg = String.format("城市：%s,店铺：%s,产品：%s 有库存", storeEntry.getValue().getCity(),
                                    storeEntry.getValue().getStoreName(), itemEnum.getName());
                                LOGGER.info("target find，send message{}", msg);
                                this.sendMessage(msg, accessKey, monitorProperties.getChatId());
                            }
                        }
                    }
                }
            }
        }
        LOGGER.info("monitor end");
    }

    private String getAccessKey() throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient(monitorProperties.getTokenUrl());
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(monitorProperties.getAppKey());
        request.setAppsecret(monitorProperties.getAppSecret());
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        if (response.isSuccess()) {
            return response.getAccessToken();
        }
        return null;
    }

    private void sendMessage(String msgInfo, String accessToken, String chatId) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(monitorProperties.getMessageUrl());
        OapiChatSendRequest request = new OapiChatSendRequest();
        request.setChatid(chatId);

        OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
        msg.setMsgtype("text");
        OapiChatSendRequest.Text text = new OapiChatSendRequest.Text();
        text.setContent(msgInfo);
        msg.setText(text);

        request.setMsg(msg);
        OapiChatSendResponse response = client.execute(request, accessToken);
    }

}

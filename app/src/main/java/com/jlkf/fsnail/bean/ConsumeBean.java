package com.jlkf.fsnail.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class ConsumeBean implements Serializable{

    public String businessMan;
    public String type;
    public String serviceName;
    public String productName;
    public String productPrice;
    public String useCardMoney;
    public String consumeTime;

    public String getBusinessMan() {
        return businessMan;
    }

    public void setBusinessMan(String businessMan) {
        this.businessMan = businessMan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getUseCardMoney() {
        return useCardMoney;
    }

    public void setUseCardMoney(String useCardMoney) {
        this.useCardMoney = useCardMoney;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }
}

package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class ConsumeBean extends BaseHttpBean{


    /**
     * data : [{"use_card_price":0,"goodName":"商品1","create_time":1529377310000,"service":"spa2服务","sName":"店1","goodPrice":111},{"use_card_price":20,"goodName":"商品1","create_time":1528712137000,"service":"spa1服务","sName":"店1","goodPrice":111}]
     * pageNo : null
     */


    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * use_card_price : 0.0
         * goodName : 商品1
         * create_time : 1529377310000
         * service : spa2服务
         * sName : 店1
         * goodPrice : 111.0
         */

        private double use_card_price;
        private String goodName;
        private long create_time;
        private String service;
        private String sName;
        private double goodPrice;

        public double getUse_card_price() {
            return use_card_price;
        }

        public void setUse_card_price(double use_card_price) {
            this.use_card_price = use_card_price;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getSName() {
            return sName;
        }

        public void setSName(String sName) {
            this.sName = sName;
        }

        public double getGoodPrice() {
            return goodPrice;
        }

        public void setGoodPrice(double goodPrice) {
            this.goodPrice = goodPrice;
        }
    }
}

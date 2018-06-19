package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class CardDetailBean extends BaseHttpBean{


    /**
     * data : [{"band_optime":1528777832000,"use_card_price":0,"payUser":"user1","cardName":"折扣券","isBandUserPhone":"13125632145","discount":0.8,"type":1,"isBandUser":"user1","price":2000,"payUserPhone":"13125632145","name":"类型2","remain_price":0.2,"id":2,"pay_optime":1528173029000,"hava_use_price":0.1}]
     * totalPage : null
     * totalRecord : null
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
         * band_optime : 1528777832000
         * use_card_price : 0.0
         * payUser : user1
         * cardName : 折扣券
         * isBandUserPhone : 13125632145
         * discount : 0.8
         * type : 1
         * isBandUser : user1
         * price : 2000.0
         * payUserPhone : 13125632145
         * name : 类型2
         * remain_price : 0.2
         * id : 2
         * pay_optime : 1528173029000
         * hava_use_price : 0.1
         */

        private long band_optime;
        private double use_card_price;
        private String payUser;
        private String cardName;
        private String isBandUserPhone;
        private double discount;
        private int type;
        private String isBandUser;
        private double price;
        private String payUserPhone;
        private String name;
        private double remain_price;
        private int id;
        private long pay_optime;
        private double hava_use_price;

        public long getBand_optime() {
            return band_optime;
        }

        public void setBand_optime(long band_optime) {
            this.band_optime = band_optime;
        }

        public double getUse_card_price() {
            return use_card_price;
        }

        public void setUse_card_price(double use_card_price) {
            this.use_card_price = use_card_price;
        }

        public String getPayUser() {
            return payUser;
        }

        public void setPayUser(String payUser) {
            this.payUser = payUser;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getIsBandUserPhone() {
            return isBandUserPhone;
        }

        public void setIsBandUserPhone(String isBandUserPhone) {
            this.isBandUserPhone = isBandUserPhone;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getIsBandUser() {
            return isBandUser;
        }

        public void setIsBandUser(String isBandUser) {
            this.isBandUser = isBandUser;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getPayUserPhone() {
            return payUserPhone;
        }

        public void setPayUserPhone(String payUserPhone) {
            this.payUserPhone = payUserPhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getRemain_price() {
            return remain_price;
        }

        public void setRemain_price(double remain_price) {
            this.remain_price = remain_price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getPay_optime() {
            return pay_optime;
        }

        public void setPay_optime(long pay_optime) {
            this.pay_optime = pay_optime;
        }

        public double getHava_use_price() {
            return hava_use_price;
        }

        public void setHava_use_price(double hava_use_price) {
            this.hava_use_price = hava_use_price;
        }
    }
}

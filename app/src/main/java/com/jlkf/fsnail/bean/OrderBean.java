package com.jlkf.fsnail.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class OrderBean extends BaseHttpBean{
    /**
     * data : [{"startPrice":800,"gtname":"护理1","orderType":1,"useVoucher":200,"orderNumber":"001","quantity":1,"shopName":"店1","finalPrice":600,"orderTime":1528450773000,"goodName":"商品1","bname":"品牌1","phone":"13125632145","name":"user1","payment":1,"status":1}]
     * code : 200
     * msg : order list success
     * totalPage : 1
     * totalRecord : 1
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
         * startPrice : 初始价格
         * gtname : 商品类别
         * orderType : 下单方式
         * useVoucher : 代金券使用
         * orderNumber : 订单编号
         * quantity : 数量
         * shopName : 商铺
         * finalPrice : 最终价格
         * orderTime : 订单时间
         * goodName : 商品名称
         * bname : 品牌
         * phone : 顾客电话
         * name : 顾客
         * payment : 支付方式
         * status : 订单状态
         */

        private int startPrice;
        private String gtname;
        private int orderType;
        private int useVoucher;
        private String orderNumber;
        private int quantity;
        private String shopName;
        private int finalPrice;
        private long orderTime;
        private String goodName;
        private String bname;
        private String phone;
        private String name;
        private int payment;
        private int status;

        public int getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(int startPrice) {
            this.startPrice = startPrice;
        }

        public String getGtname() {
            return gtname;
        }

        public void setGtname(String gtname) {
            this.gtname = gtname;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getUseVoucher() {
            return useVoucher;
        }

        public void setUseVoucher(int useVoucher) {
            this.useVoucher = useVoucher;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public int getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(int finalPrice) {
            this.finalPrice = finalPrice;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getBname() {
            return bname;
        }

        public void setBname(String bname) {
            this.bname = bname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}

package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class BookBean extends BaseHttpBean{

    /**
     * data : [{"optime":"2018-06-14","goodsId":1,"type":"类型1","customerName":"van","customerPhone":"13815425632","uName":"fufu","createTime":"2018-06-14 15:57:00","service":"spa1服务","brandId":1,"typeId":1,"id":28,"serviceId":1,"brand":"品牌1","goodsName":"商品1","staffId":1,"status":4},{"optime":"2018-06-14","type":"类型1","customerName":"hah","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:28:18","service":"spa1服务","brandId":1,"startTime":"11:28:17","typeId":1,"id":22,"serviceId":1,"brand":"品牌1","staffId":1,"status":1},{"optime":"2018-06-14","type":"类型1","customerName":"wangsheng","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:27:23","service":"spa2服务","brandId":1,"startTime":"11:27:22","typeId":1,"id":21,"serviceId":2,"brand":"品牌1","staffId":1,"status":1},{"optime":"2018-06-15","type":"类型1","customerName":"wangsheng","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:13:39","service":"spa1服务","brandId":1,"startTime":"11:13:38","typeId":1,"id":20,"serviceId":1,"brand":"品牌1","staffId":1,"status":1},{"optime":"2018-06-15","goodsId":1,"type":"类型1","customerName":"vanq","customerPhone":"13815425632","uName":"fufu","createTime":"2018-06-13 17:32:06","service":"spa1服务","brandId":1,"typeId":1,"id":10,"serviceId":1,"brand":"品牌1","goodsName":"商品1","staffId":1,"status":4},{"optime":"2018-06-13","goodsId":1,"type":"类型1","customerName":"van","customerPhone":"13815425632","uName":"liliw","createTime":"2018-06-13 17:26:31","service":"spa1服务","brandId":1,"typeId":1,"id":9,"serviceId":1,"brand":"品牌1","goodsName":"商品1","staffId":2,"status":1},{"optime":"2018-06-01","goodsId":3,"type":"类型1","customerName":"ghjk","customerPhone":"13700000000","uName":"liliw","createTime":"2018-05-31 17:29:07","service":"spa2服务","brandId":4,"startTime":"17:25:28","typeId":1,"id":5,"serviceId":2,"brand":"品牌4","goodsName":"商品3","staffId":2,"status":1},{"optime":"2018-05-31","goodsId":1,"type":"类型1","customerName":"user1","customerPhone":"13125632145","uName":"liliw","createTime":"2018-05-31 09:52:07","service":"spa1服务","brandId":3,"startTime":"23:51:38","typeId":1,"id":1,"serviceId":1,"brand":"品牌3","goodsName":"商品1","staffId":2,"status":1}]
     * pageNo : null
     */


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * optime : 2018-06-14
         * goodsId : 1
         * type : 类型1
         * customerName : van
         * customerPhone : 13815425632
         * uName : fufu
         * createTime : 2018-06-14 15:57:00
         * service : spa1服务
         * brandId : 1
         * typeId : 1
         * id : 28
         * serviceId : 1
         * brand : 品牌1
         * goodsName : 商品1
         * staffId : 1
         * status : 4
         * startTime : 11:28:17
         */

        private String optime;
        private int goodsId;
        private String type;
        private String customerName;
        private String customerPhone;
        private String uName;
        private String createTime;
        private String service;
        private int brandId;
        private int typeId;
        private int id;
        private int serviceId;
        private String brand;
        private String goodsName;
        private int staffId;
        private int status;
        private String startTime;
        private int customerId;

        public String getuName() {
            return uName;
        }

        public void setuName(String uName) {
            this.uName = uName;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getUName() {
            return uName;
        }

        public void setUName(String uName) {
            this.uName = uName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getServiceId() {
            return serviceId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }
}

package com.jlkf.fsnail.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/6/2.
 */

public class BookedServiceBean  extends  BaseHttpBean{
    /**
     * data : [{"appointTime":"11:15:00","optime":"2018-06-16","goodsId":1,"type":"类型2","customerName":"van","customerPhone":"13815425632","uName":"liliw","createTime":"2018-06-15 17:31:31","service":"spa3服务","brandId":2,"customerId":10,"typeId":2,"id":36,"serviceId":3,"brand":"品牌2","goodsName":"商品1","staffId":2,"status":4},{"appointTime":"18:47:02","optime":"2018-06-14","type":"类型1","customerName":"wangsheng","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:27:23","service":"spa2服务","brandId":1,"customerId":8,"typeId":1,"id":21,"serviceId":2,"brand":"品牌1","staffId":1,"status":1},{"appointTime":"16:46:50","optime":"2018-06-15","type":"类型1","customerName":"wangsheng","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:13:39","service":"spa1服务","brandId":1,"customerId":8,"typeId":1,"id":20,"serviceId":1,"brand":"品牌1","staffId":1,"status":1}]
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
         * appointTime : 11:15:00
         * optime : 2018-06-16
         * goodsId : 1
         * type : 类型2
         * customerName : van
         * customerPhone : 13815425632
         * uName : liliw
         * createTime : 2018-06-15 17:31:31
         * service : spa3服务
         * brandId : 2
         * customerId : 10
         * typeId : 2
         * id : 36
         * serviceId : 3
         * brand : 品牌2
         * goodsName : 商品1
         * staffId : 2
         * status : 4
         */

        private String appointTime;
        private String optime;
        private int goodsId;
        private String type;
        private String customerName;
        private String customerPhone;
        private String uName;
        private String createTime;
        private String service;
        private int brandId;
        private int customerId;
        private int typeId;
        private int id;
        private int serviceId;
        private String brand;
        private String goodsName;
        private int staffId;
        private int status;

        public String getAppointTime() {
            return appointTime;
        }

        public void setAppointTime(String appointTime) {
            this.appointTime = appointTime;
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

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
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
    }


}
                                                  
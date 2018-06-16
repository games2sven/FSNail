package com.jlkf.fsnail.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class ServiceBean extends BaseHttpBean{


    /**
     * data : [{"optime":"2018-06-14","type":"类型2","customerName":"1235","customerPhone":"1112","uName":"liliw","createTime":"2018-06-14 12:22:01","service":"spa2服务","brandId":1,"startTime":"12:22:01","typeId":2,"id":24,"serviceId":2,"brand":"品牌1","staffId":2,"status":1},{"optime":"2018-06-14","type":"类型2","customerName":"wangsheng","customerPhone":"18813678570","uName":"liliw","createTime":"2018-06-14 11:31:07","service":"spa1服务","brandId":1,"startTime":"11:31:07","typeId":2,"id":23,"serviceId":1,"brand":"品牌1","staffId":2,"status":1},{"optime":"2018-06-14","type":"类型1","customerName":"hah","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:28:18","service":"spa1服务","brandId":1,"startTime":"11:28:17","typeId":1,"id":22,"serviceId":1,"brand":"品牌1","staffId":1,"status":1},{"optime":"2018-06-14","type":"类型1","customerName":"wangsheng","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:27:23","service":"spa2服务","brandId":1,"startTime":"11:27:22","typeId":1,"id":21,"serviceId":2,"brand":"品牌1","staffId":1,"status":1},{"optime":"2018-06-14","type":"类型1","customerName":"wangsheng","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:13:39","service":"spa1服务","brandId":1,"startTime":"11:13:38","typeId":1,"id":20,"serviceId":1,"brand":"品牌1","staffId":1,"status":1},{"optime":"2018-06-14","type":"类型1","customerName":"wangs","customerPhone":"18813678570","uName":"fufu","createTime":"2018-06-14 11:11:39","service":"spa1服务","brandId":1,"startTime":"11:11:39","typeId":1,"id":19,"serviceId":1,"brand":"品牌1","staffId":1,"status":1}]
     * pageNo : null
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * optime : 2018-06-14
         * type : 类型2
         * customerName : 1235
         * customerPhone : 1112
         * uName : liliw
         * createTime : 2018-06-14 12:22:01
         * service : spa2服务
         * brandId : 1
         * startTime : 12:22:01
         * typeId : 2
         * id : 24
         * serviceId : 2
         * brand : 品牌1
         * staffId : 2
         * status : 1
         */

        private String optime;
        private String type;
        private String customerName;
        private String customerPhone;
        private String uName;
        private String createTime;
        private String service;
        private int brandId;
        private String startTime;
        private int typeId;
        private int id;
        private int serviceId;
        private String brand;
        private int staffId;
        private int status;

        public String getOptime() {
            return optime;
        }

        public void setOptime(String optime) {
            this.optime = optime;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
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
                                                  
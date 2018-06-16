package com.jlkf.fsnail.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/6/9.
 */

public class ShopCartBean extends  BaseHttpBean {


    /**
     * data : [{"customerPhone":"13125632541","appointTime":"17:29:12","uName":"liliw","createTime":1527906969000,"service":"spa1","objectCheck":2341,"price":50,"id":3,"customerName":"user3","status":1},{"customerPhone":"13256524565","appointTime":"17:28:53","uName":"liliw","createTime":1527906966000,"service":"spa2","objectCheck":1234,"price":65,"id":2,"customerName":"user2","status":1},{"customerPhone":"13125632145","appointTime":"09:51:57","uName":"liliw","createTime":1527906964000,"service":"spa1","objectCheck":2563,"price":50,"id":1,"customerName":"user1","status":1}]
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
        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        /**
         * customerPhone : 13125632541
         * appointTime : 17:29:12
         * uName : liliw
         * createTime : 1527906969000
         * service : spa1
         * objectCheck : 2341
         * price : 50
         * id : 3
         * customerName : user3
         * status : 1
         */
        private boolean isSelect=true;
        private String customerPhone;
        private String appointTime;
        private String uName;
        private long createTime;
        private String service;
        private int objectCheck;
        private int price;
        private int id;
        private String customerName;
        private int status;

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getAppointTime() {
            return appointTime;
        }

        public void setAppointTime(String appointTime) {
            this.appointTime = appointTime;
        }

        public String getUName() {
            return uName;
        }

        public void setUName(String uName) {
            this.uName = uName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public int getObjectCheck() {
            return objectCheck;
        }

        public void setObjectCheck(int objectCheck) {
            this.objectCheck = objectCheck;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        return "ShopCartBean{" +
                "data=" + data.size() +
                '}'+super.toString();
    }
}

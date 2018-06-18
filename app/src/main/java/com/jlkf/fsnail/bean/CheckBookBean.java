package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class CheckBookBean extends BaseHttpBean{


    /**
     * data : [{"nick_name":"momr","customer_phone":"13815425632","customer_name":"van","appoint_time":"17:15:41","serviceName":"spa1服务","staffId":2,"serId":9}]
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
         * nick_name : momr
         * customer_phone : 13815425632
         * customer_name : van
         * appoint_time : 17:15:41
         * serviceName : spa1服务
         * staffId : 2
         * serId : 9
         */

        private String nick_name;
        private String customer_phone;
        private String customer_name;
        private String appoint_time;
        private String serviceName;
        private int staffId;
        private int serId;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }

        public String getAppoint_time() {
            return appoint_time;
        }

        public void setAppoint_time(String appoint_time) {
            this.appoint_time = appoint_time;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public int getSerId() {
            return serId;
        }

        public void setSerId(int serId) {
            this.serId = serId;
        }
    }
}

package com.jlkf.fsnail.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class StaffManagerBean extends BaseHttpBean {


    /**
     * data : [{"u_name":"rua","phone":"13200000000","nick_name":"789","id":3,"status":0},{"u_name":"liliw","phone":"13100000000","nick_name":"456","id":2,"status":4},{"u_name":"www","phone":"13000000000","nick_name":"123","id":1,"status":0}]
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
         * u_name : rua
         * phone : 13200000000
         * nick_name : 789
         * id : 3
         * status : 0
         */

        private String u_name;
        private String phone;
        private String nick_name;
        private int id;
        private int status;

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
                                                  
package com.jlkf.fsnail.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/6/17.
 */

public class StaffWorklyBean extends  BaseHttpBean {

    /**
     * data : [{"week":1,"start":"09:30:26","end":"18:30:48","id":1,"status":1},{"week":2,"start":"09:30:26","end":"18:30:48","id":2,"status":1},{"week":3,"start":"09:30:26","end":"18:30:48","id":3,"status":1},{"week":4,"start":"09:30:26","end":"18:30:48","id":4,"status":1},{"week":5,"start":"09:30:26","end":"18:30:48","id":5,"status":1}]
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
         * week : 1
         * start : 09:30:26
         * end : 18:30:48
         * id : 1
         * status : 1
         */
        public   boolean   isOpen;
        private int week;
        private String start;
        private String end;
        private int id;
        private int status;

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
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

    @Override
    public String toString() {
        return "StaffWorklyBean{" +
                "data=" + data.size() +
                '}'+super.toString();
    }
}

package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class GoodsTypeBean extends BaseHttpBean{


    /**
     * data : [{"gtypeName":"护理1","id":1},{"gtypeName":"美甲1","id":2},{"gtypeName":"护理2","id":3},{"gtypeName":"美甲2","id":4}]
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
         * gtypeName : 护理1
         * id : 1
         */

        private String gtypeName;
        private int id;

        public String getGtypeName() {
            return gtypeName;
        }

        public void setGtypeName(String gtypeName) {
            this.gtypeName = gtypeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

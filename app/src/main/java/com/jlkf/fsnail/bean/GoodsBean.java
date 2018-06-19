package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class GoodsBean extends BaseHttpBean{


    /**
     * data : [{"goodName":"商品1","remark":"product1","id":1,"type":"护理1","goodPrice":111,"brand":"品牌1"},{"goodName":"商品2","remark":"product2","id":2,"type":"护理1","goodPrice":11,"brand":"品牌2"},{"goodName":"商品3","remark":"product3","id":3,"type":"美甲1","goodPrice":22,"brand":"品牌1"},{"goodName":"商品4","remark":"product4","id":4,"type":"护理2","goodPrice":55,"brand":"品牌1"},{"goodName":"商品5","remark":"product5","id":5,"type":"护理2","goodPrice":77,"brand":"品牌3"}]
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
         * goodName : 商品1
         * remark : product1
         * id : 1
         * type : 护理1
         * goodPrice : 111.0
         * brand : 品牌1
         */

        private String goodName;
        private String remark;
        private int id;
        private String type;
        private double goodPrice;
        private String brand;

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getGoodPrice() {
            return goodPrice;
        }

        public void setGoodPrice(double goodPrice) {
            this.goodPrice = goodPrice;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
    }
}

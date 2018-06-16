package com.jlkf.fsnail.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class GoodsBean extends BaseHttpBean{

    /**
     * data : [{"goodName":"商品1","remark":"product1","type":"护理1","goodPrice":111,"brand":"品牌1"}]
     * code : 200
     * msg : goods list success
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
         * goodName : 商品1
         * remark : product1
         * type : 护理1
         * goodPrice : 111
         * brand : 品牌1
         */

        private String goodName;
        private String remark;
        private String type;
        private int goodPrice;//金额
        private String brand;//品牌

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getGoodPrice() {
            return goodPrice;
        }

        public void setGoodPrice(int goodPrice) {
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

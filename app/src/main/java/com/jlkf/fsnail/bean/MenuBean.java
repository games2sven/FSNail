package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class MenuBean extends BaseHttpBean{


    /**
     * data : [{"id":1,"typename":"类型1","priceList":[{"price":50,"name":"spa1服务","id":1},{"price":65,"name":"spa2服务","id":2},{"price":40,"name":"spa3服务","id":3}]},{"id":2,"typename":"类型2","priceList":[{"price":55,"name":"spa4服务","id":4},{"price":25,"name":"spa5服务","id":5}]},{"id":3,"typename":"类型3","priceList":[]},{"id":4,"typename":"类型4","priceList":[]}]
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
         * id : 1
         * typename : 类型1
         * priceList : [{"price":50,"name":"spa1服务","id":1},{"price":65,"name":"spa2服务","id":2},{"price":40,"name":"spa3服务","id":3}]
         */

        private int id;
        private String typename;
        private List<PriceListBean> priceList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public List<PriceListBean> getPriceList() {
            return priceList;
        }

        public void setPriceList(List<PriceListBean> priceList) {
            this.priceList = priceList;
        }

        public static class PriceListBean {
            /**
             * price : 50
             * name : spa1服务
             * id : 1
             */

            private int price;
            private String name;
            private int id;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}

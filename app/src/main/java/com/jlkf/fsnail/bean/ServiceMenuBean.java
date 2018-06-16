package com.jlkf.fsnail.bean;

import java.util.List;

/**
 * Created by Lenovo on 2018/6/8.
 */

public class ServiceMenuBean extends  BaseHttpBean {


    /**
     * data : {"service":[{"price":50,"name":"spa1服务","id":1,"tId":1},{"price":65,"name":"spa2服务","id":2,"tId":1},{"price":40,"name":"spa3服务","id":3,"tId":1},{"price":55,"name":"spa4服务","id":4,"tId":2},{"price":25,"name":"spa5服务","id":5,"tId":2}],"goods":[{"goodName":"商品1","brandId":1,"id":1,"goodPrice":111},{"goodName":"商品2","brandId":2,"id":2,"goodPrice":11},{"goodName":"商品3","brandId":1,"id":3,"goodPrice":22},{"goodName":"商品4","brandId":1,"id":4,"goodPrice":55},{"goodName":"商品5","brandId":3,"id":5,"goodPrice":77}],"staff":[{"u_name":"fufu","id":1},{"u_name":"liliw","id":2},{"u_name":"rua","id":3},{"u_name":"fuze","id":5}],"type":[{"id":1,"name":"类型1","serviceList":[{"price":50,"name":"spa1服务","id":1}]},{"id":2,"name":"类型2","serviceList":[{"price":65,"name":"spa2服务","id":2}]},{"id":3,"name":"类型3","serviceList":[{"price":40,"name":"spa3服务","id":3}]},{"id":4,"name":"类型4","serviceList":[{"price":55,"name":"spa4服务","id":4}]}],"brand":[{"id":1,"brandname":"品牌1","goodList":[{"goodname":"商品1","id":1,"goodPrice":111},{"goodname":"商品3","id":3,"goodPrice":22},{"goodname":"商品4","id":4,"goodPrice":55}]},{"id":2,"brandname":"品牌2","goodList":[{"goodname":"商品2","id":2,"goodPrice":11}]},{"id":3,"brandname":"品牌3","goodList":[{"goodname":"商品5","id":5,"goodPrice":77}]},{"id":4,"brandname":"品牌4","goodList":[]}],"customer":[{"name":"viva","id":7},{"name":"user6","id":6},{"name":"user5","id":5},{"name":"user4","id":4},{"name":"user3","id":3},{"name":"user2","id":2},{"name":"user1","id":1}]}
     * totalPage : null
     * totalRecord : null
     * pageNo : null
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ServiceBean> service;
        private List<GoodsBean> goods;
        private List<StaffBean> staff;
        private List<TypeBean> type;
        private List<BrandBean> brand;
        private List<CustomerBean> customer;

        public List<ServiceBean> getService() {
            return service;
        }

        public void setService(List<ServiceBean> service) {
            this.service = service;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<StaffBean> getStaff() {
            return staff;
        }

        public void setStaff(List<StaffBean> staff) {
            this.staff = staff;
        }

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public List<BrandBean> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandBean> brand) {
            this.brand = brand;
        }

        public List<CustomerBean> getCustomer() {
            return customer;
        }

        public void setCustomer(List<CustomerBean> customer) {
            this.customer = customer;
        }

        public static class ServiceBean {
            /**
             * price : 50
             * name : spa1服务
             * id : 1
             * tId : 1
             */

            private int price;
            private String name;
            private int id;
            private int tId;

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

            public int getTId() {
                return tId;
            }

            public void setTId(int tId) {
                this.tId = tId;
            }

            @Override
            public String toString() {
                return name;
            }
        }

        public static class GoodsBean {
            /**
             * goodName : 商品1
             * brandId : 1
             * id : 1
             * goodPrice : 111
             */

            private String goodName;
            private int brandId;
            private int id;
            private int goodPrice;
            @Override
            public String toString() {
                return goodName;
            }
            public String getGoodName() {
                return goodName;
            }

            public void setGoodName(String goodName) {
                this.goodName = goodName;
            }

            public int getBrandId() {
                return brandId;
            }

            public void setBrandId(int brandId) {
                this.brandId = brandId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoodPrice() {
                return goodPrice;
            }

            public void setGoodPrice(int goodPrice) {
                this.goodPrice = goodPrice;
            }
        }

        public static class StaffBean {
            /**
             * u_name : fufu
             * id : 1
             */

            private String uName;
            private int id;

            public String getU_name() {
                return uName;
            }

            public void setU_name(String u_name) {
                this.uName = u_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return  uName;
            }
        }

        public static class TypeBean {
            /**
             * id : 1
             * name : 类型1
             * serviceList : [{"price":50,"name":"spa1服务","id":1}]
             */

            private int id;
            private String name;
            private List<ServiceListBean> serviceList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
            @Override
            public String toString() {
                return  name;
            }


            public List<ServiceListBean> getServiceList() {
                return serviceList;
            }

            public void setServiceList(List<ServiceListBean> serviceList) {
                this.serviceList = serviceList;
            }

            public static class ServiceListBean {
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

                @Override
                public String toString() {
                    return  name ;
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

        public static class BrandBean {
            /**
             * id : 1
             * brandname : 品牌1
             * goodList : [{"goodname":"商品1","id":1,"goodPrice":111},{"goodname":"商品3","id":3,"goodPrice":22},{"goodname":"商品4","id":4,"goodPrice":55}]
             */

            private int id;
            private String brandname;
            private List<GoodListBean> goodList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return brandname;
            }

            public String getBrandname() {
                return brandname;
            }

            public void setBrandname(String brandname) {
                this.brandname = brandname;
            }

            public List<GoodListBean> getGoodList() {
                return goodList;
            }

            public void setGoodList(List<GoodListBean> goodList) {
                this.goodList = goodList;
            }

            public static class GoodListBean {
                /**
                 * goodname : 商品1
                 * id : 1
                 * goodPrice : 111
                 */

                private String goodname;
                private int id;
                private int goodPrice;


                @Override
                public String toString() {
                    return  goodname ;
                }

                public String getGoodname() {
                    return goodname;
                }

                public void setGoodname(String goodname) {
                    this.goodname = goodname;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGoodPrice() {
                    return goodPrice;
                }

                public void setGoodPrice(int goodPrice) {
                    this.goodPrice = goodPrice;
                }
            }
        }

        public static class CustomerBean {

            /**
             * name : viva
             * id : 7
             */


            private String name;
            private int id;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            private String phone;

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

            @Override
            public String toString() {
                return name;
            }
        }
    }
}

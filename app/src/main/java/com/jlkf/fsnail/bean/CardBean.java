package com.jlkf.fsnail.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class CardBean extends BaseHttpBean{


    /**
     * data : [{"payUser":"user1","cardName":"折扣券","price":2000,"isBandUserPhone":"13125632145","name":"类型2","id":2,"type":1,"pay_optime":1528173029000,"isBandUser":"user1"},{"payUser":"user1","cardName":"优惠券","price":1000,"isBandUserPhone":"13125632145","name":"类型1","id":1,"type":1,"pay_optime":1528612444000,"isBandUser":"user1"}]
     * code : 200
     * msg : Elenco dei dipendenti
     * totalPage : 1
     * totalRecord : 2
     * pageNo : null
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * payUser : user1
         * cardName : 折扣券
         * price : 2000
         * isBandUserPhone : 13125632145
         * name : 类型2
         * id : 2
         * type : 1
         * pay_optime : 1528173029000
         * isBandUser : user1
         */

        private String payUser;
        private String cardName;
        private int price;
        private String isBandUserPhone;
        private String name;
        private int id;
        private int type;
        private long pay_optime;
        private String isBandUser;

        public String getPayUser() {
            return payUser;
        }

        public void setPayUser(String payUser) {
            this.payUser = payUser;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getIsBandUserPhone() {
            return isBandUserPhone;
        }

        public void setIsBandUserPhone(String isBandUserPhone) {
            this.isBandUserPhone = isBandUserPhone;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getPay_optime() {
            return pay_optime;
        }

        public void setPay_optime(long pay_optime) {
            this.pay_optime = pay_optime;
        }

        public String getIsBandUser() {
            return isBandUser;
        }

        public void setIsBandUser(String isBandUser) {
            this.isBandUser = isBandUser;
        }
    }
}

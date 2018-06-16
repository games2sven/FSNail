package com.jlkf.fsnail.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class UserBean extends BaseHttpBean{
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "data=" + data +
                '}';
    }

    /**
     * Data
     */
    public class Data implements Serializable {

        private int id; //员工ID
        private int shopId; //店铺ID
        private String nickName;//姓名
        private String uName;//昵称
        private String address;//地址
        private String abilityLevel;//能力水品
        private String email;//邮箱
        private String phone;//手机
        private String password;//
        private int type;//1店长 2员工
        private String createTime;//创建时间

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getuName() {
            return uName;
        }

        public void setuName(String uName) {
            this.uName = uName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAbilityLevel() {
            return abilityLevel;
        }

        public void setAbilityLevel(String abilityLevel) {
            this.abilityLevel = abilityLevel;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", shopId=" + shopId +
                    ", nickName='" + nickName + '\'' +
                    ", uName='" + uName + '\'' +
                    ", address='" + address + '\'' +
                    ", abilityLevel='" + abilityLevel + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", password='" + password + '\'' +
                    ", type=" + type +
                    ", createTime='" + createTime + '\'' +
                    '}';
        }
    }
}

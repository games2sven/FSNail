package com.jlkf.fsnail.bean;

public class StaffinfoBean extends  BaseHttpBean {


    /**
     * data : {"id":1,"shopId":1,"nickName":"wwe","uName":"fufu","address":"阿富汗","abilityLevel":"good","email":"wrnl@566.com","phone":"13865478965","password":"E10ADC3949BA59ABBE56E057F20F883E","type":2,"createTime":1527740706000}
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
        /**
         * id : 1
         * shopId : 1
         * nickName : wwe
         * uName : fufu
         * address : 阿富汗
         * abilityLevel : good
         * email : wrnl@566.com
         * phone : 13865478965
         * password : E10ADC3949BA59ABBE56E057F20F883E
         * type : 2
         * createTime : 1527740706000
         */

        private int id;
        private int shopId;
        private String nickName;
        private String uName;
        private String address;
        private String abilityLevel;
        private String email;
        private String phone;
        private String password;
        private int type;
        private long createTime;

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

        public String getUName() {
            return uName;
        }

        public void setUName(String uName) {
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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}

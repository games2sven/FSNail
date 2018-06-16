package com.jlkf.fsnail.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/29 0029.
 */

public class CustomerBean extends  BaseHttpBean {


    /**
     * data : [{"birthday":"1989-06-08","address":"guangdongsenz","isDisease":"2","create_time":1528437871000,"isLike":1,"remake":"newcustimer","isAllergy":"2","totalExpence":0,"howService":1,"takeDrugs":"2","lastExpence":0,"nailType":"1","phone":"13652452362","integral":20,"isReceiveService":"2018-6-1","name":"viva","registration":"2018-06-08","id":7,"email":"555@562.com"},{"birthday":"2000-02-09","address":"Basilicata","isDisease":"2","create_time":1527837933000,"isLike":1,"remake":"qwere","isAllergy":"2","totalExpence":0,"howService":1,"takeDrugs":"2","lastExpence":0,"nailType":"1","phone":"13965421452","integral":20,"name":"user6","registration":"2018-06-01","id":6,"email":"225@563.com"},{"birthday":"2011-02-15","address":"Foggia","isDisease":"2","create_time":1527837929000,"isLike":1,"remake":"asffa","isAllergy":"2","totalExpence":0,"howService":1,"takeDrugs":"2","lastExpence":0,"nailType":"1","phone":"13562326545","integral":20,"name":"user5","registration":"2018-06-01","id":5,"email":"562@582.com"},{"birthday":"1995-11-01","address":"Barletta-Andria-Trani","isDisease":"2","create_time":1527837925000,"isLike":1,"remake":"asas","isAllergy":"2","totalExpence":0,"howService":1,"takeDrugs":"2","lastExpence":0,"nailType":"1","phone":"13125632121","integral":20,"name":"user4","registration":"2018-06-01","id":4,"email":"886@456.com"},{"birthday":"1994-06-01","address":"L'Aquila","isDisease":"2","create_time":1527837922000,"isLike":1,"remake":"asdsda","isAllergy":"2","totalExpence":0,"howService":1,"takeDrugs":"2","lastExpence":0,"nailType":"1","phone":"13125632541","integral":20,"name":"user3","registration":"2018-06-01","id":3,"email":"598@562.com"}]
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
         * birthday : 1989-06-08
         * address : guangdongsenz
         * isDisease : 2
         * create_time : 1528437871000
         * isLike : 1
         * remake : newcustimer
         * isAllergy : 2
         * totalExpence : 0.0
         * howService : 1
         * takeDrugs : 2
         * lastExpence : 0.0
         * nailType : 1
         * phone : 13652452362
         * integral : 20
         * isReceiveService : 2018-6-1
         * name : viva
         * registration : 2018-06-08
         * id : 7
         * email : 555@562.com
         */

        private String birthday;
        private String address;
        private String isDisease;
        private long create_time;
        private int isLike;
        private String remake;
        private String isAllergy;
        private double totalExpence;
        private int howService;
        private String takeDrugs;
        private double lastExpence;
        private String nailType;
        private String phone;
        private int integral;
        private String isReceiveService;
        private String name;
        private String registration;
        private int id;
        private String email;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIsDisease() {
            return isDisease;
        }

        public void setIsDisease(String isDisease) {
            this.isDisease = isDisease;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getIsLike() {
            return isLike;
        }

        public void setIsLike(int isLike) {
            this.isLike = isLike;
        }

        public String getRemake() {
            return remake;
        }

        public void setRemake(String remake) {
            this.remake = remake;
        }

        public String getIsAllergy() {
            return isAllergy;
        }

        public void setIsAllergy(String isAllergy) {
            this.isAllergy = isAllergy;
        }

        public double getTotalExpence() {
            return totalExpence;
        }

        public void setTotalExpence(double totalExpence) {
            this.totalExpence = totalExpence;
        }

        public int getHowService() {
            return howService;
        }

        public void setHowService(int howService) {
            this.howService = howService;
        }

        public String getTakeDrugs() {
            return takeDrugs;
        }

        public void setTakeDrugs(String takeDrugs) {
            this.takeDrugs = takeDrugs;
        }

        public double getLastExpence() {
            return lastExpence;
        }

        public void setLastExpence(double lastExpence) {
            this.lastExpence = lastExpence;
        }

        public String getNailType() {
            return nailType;
        }

        public void setNailType(String nailType) {
            this.nailType = nailType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getIsReceiveService() {
            return isReceiveService;
        }

        public void setIsReceiveService(String isReceiveService) {
            this.isReceiveService = isReceiveService;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegistration() {
            return registration;
        }

        public void setRegistration(String registration) {
            this.registration = registration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    @Override
    public String toString() {
        return "CustomerBean{" +
                "data=" + data.size() +
                '}'+super.toString();
    }
}

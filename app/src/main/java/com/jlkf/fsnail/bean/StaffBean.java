package com.jlkf.fsnail.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class StaffBean extends BaseHttpBean {

    /**
     * data : [{"phone":"13865478960","nickName":"wwe","id":1,"headerImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2933423226,3517601787&fm=27&gp=0.jpg"},{"phone":"13100000000","nickName":"momr","id":2,"headerImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2933423226,3517601787&fm=27&gp=0.jpg"},{"phone":"13200000000","nickName":"ailibi","id":3,"headerImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2933423226,3517601787&fm=27&gp=0.jpg"},{"phone":"13865478965","nickName":"froter","id":5,"headerImg":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2933423226,3517601787&fm=27&gp=0.jpg"},{"phone":"18813678570","nickName":"旺盛","id":6}]
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
         * phone : 13865478960
         * nickName : wwe
         * id : 1
         * headerImg : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2933423226,3517601787&fm=27&gp=0.jpg
         */

        private String phone;
        private String nickName;
        private int id;
        private String headerImg;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHeaderImg() {
            return headerImg;
        }

        public void setHeaderImg(String headerImg) {
            this.headerImg = headerImg;
        }
    }
}
                                                  
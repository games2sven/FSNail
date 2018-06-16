package com.jlkf.fsnail.bean;

import com.google.gson.annotations.SerializedName;

public class UserAgreementBean extends  BaseHttpBean {

    /**
     * data : {"id":2,"content":"用户协议 1.123456 2.123456 3.123456","pisture":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528355707&di=1159cec3919f96dbba5212fbc320c30c&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.99danji.com%2Fuploadfile%2Fimage%2F20150810%2F1439193272737323.jpg","type":null,"createTime":null}
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
         * id : 2
         * content : 用户协议 1.123456 2.123456 3.123456
         * pisture : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528355707&di=1159cec3919f96dbba5212fbc320c30c&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.99danji.com%2Fuploadfile%2Fimage%2F20150810%2F1439193272737323.jpg
         * type : null
         * createTime : null
         */

        private int id;
        private String content;
        private String pisture;
        private Object type;
        private Object createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPisture() {
            return pisture;
        }

        public void setPisture(String pisture) {
            this.pisture = pisture;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", content='" + content + '\'' +
                    ", pisture='" + pisture + '\'' +
                    ", type=" + type +
                    ", createTime=" + createTime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserAgreementBean{" +
                "data=" + data.toString() +
                '}';
    }
}

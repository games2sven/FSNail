package com.jlkf.fsnail.bean;

public class ADBean extends  BaseHttpBean {

    /**
     * data : {"id":1,"content":"锁屏广告","pisture":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527760710964&di=2262c40d5a80d488c17ba52e0209c308&imgtype=0&src=http%3A%2F%2F365jia.cn%2Fuploads%2F13%2F0904%2F52268d6b5b077.jpg","type":null,"createTime":null}
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
         * content : 锁屏广告
         * pisture : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527760710964&di=2262c40d5a80d488c17ba52e0209c308&imgtype=0&src=http%3A%2F%2F365jia.cn%2Fuploads%2F13%2F0904%2F52268d6b5b077.jpg
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
    }
}

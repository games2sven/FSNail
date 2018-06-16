package com.jlkf.fsnail.bean;

import java.io.Serializable;

/**
 * Created by sven on 2016/8/19.
 * 网络请求返回数据基类bean
 */
public class BaseHttpBean implements Serializable {


    /**
     * code : 200
     * msg : service list success
     * totalPage : 1
     * totalRecord : 6
     * pageNo : null
     */

    private int code;
    private String msg;
    private int totalPage;
    private int totalRecord;
    private Object pageNo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Object getPageNo() {
        return pageNo;
    }

    public void setPageNo(Object pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "BaseHttpBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", totalPage=" + totalPage +
                ", totalRecord=" + totalRecord +
                ", pageNo=" + pageNo +
                '}';
    }
}

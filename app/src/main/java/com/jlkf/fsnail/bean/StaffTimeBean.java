package com.jlkf.fsnail.bean;

public class StaffTimeBean {

    public  String  week;
    public  String  startTime;
    public  String  endTime;
     public   boolean   isOpen;

    public StaffTimeBean(String week, String startTime, String endTime, boolean isOpen) {
        this.week = week;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isOpen = isOpen;
    }
}

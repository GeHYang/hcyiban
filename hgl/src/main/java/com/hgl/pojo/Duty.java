package com.hgl.pojo;

import java.util.Date;

public class Duty {
    private Integer did;

    private String sname;

    private String time;

    private String signTime;

    private Integer state;

    private Integer count;

    public Duty(Integer did, String sname, String time, String signTime, Integer state, Integer count) {
        this.did = did;
        this.sname = sname;
        this.time = time;
        this.signTime = signTime;
        this.state = state;
        this.count = count;
    }

    public Duty() {
        super();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
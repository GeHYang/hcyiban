package com.htgl.pojo;

public class Duty {
    private Integer did;

    private String sname;

    private String time;

    private String sign_time;

    private Integer state;

    private Integer count;

    public Duty(Integer did, String sname, String time, String sign_time, Integer state, Integer count) {
        this.did = did;
        this.sname = sname;
        this.time = time;
        this.sign_time = sign_time;
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

    public String getsign_time() {
        return sign_time;
    }

    public void setsign_time(String sign_time) {
        this.sign_time = sign_time;
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
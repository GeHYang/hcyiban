package com.hgl.pojo;

import java.util.Date;

public class Meeting {
    private Integer mid;

    private Integer uid;

    private String mtitle;

    private String mcontent;

    private String startTime;

    private String endTime;

    private String mcode;

    public Meeting(Integer mid, Integer uid, String mtitle, String mcontent, String startTime, String endTime, String mcode) {
        this.mid = mid;
        this.uid = uid;
        this.mtitle = mtitle;
        this.mcontent = mcontent;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mcode = mcode;
    }

    public Meeting() {
        super();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle == null ? null : mtitle.trim();
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent == null ? null : mcontent.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode == null ? null : mcode.trim();
    }
}
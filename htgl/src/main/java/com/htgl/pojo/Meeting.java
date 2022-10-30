package com.htgl.pojo;

public class Meeting {
    private Integer mid;

    private Integer uid;

    private String mtitle;

    private String mcontent;

    private String start_time;

    private String end_time;

    private String mcode;

    public Meeting(Integer mid, Integer uid, String mtitle, String mcontent, String start_time, String end_time, String mcode) {
        this.mid = mid;
        this.uid = uid;
        this.mtitle = mtitle;
        this.mcontent = mcontent;
        this.start_time = start_time;
        this.end_time = end_time;
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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode == null ? null : mcode.trim();
    }
}
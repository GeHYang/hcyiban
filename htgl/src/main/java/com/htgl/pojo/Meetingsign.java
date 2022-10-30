package com.htgl.pojo;

import java.util.Date;

public class Meetingsign {
    private Integer msId;

    private Integer ms_mid;

    private Date ms_time;

    private Integer ms_uid;

    private String code;

    public Meetingsign(Integer msId, Integer ms_mid, Date ms_time, Integer ms_uid, String code) {
        this.msId = msId;
        this.ms_mid = ms_mid;
        this.ms_time = ms_time;
        this.ms_uid = ms_uid;
        this.code = code;
    }

    public Meetingsign() {
        super();
    }

    public Integer getMsId() {
        return msId;
    }

    public void setMsId(Integer msId) {
        this.msId = msId;
    }

    public Integer getMs_mid() {
        return ms_mid;
    }

    public void setMs_mid(Integer ms_mid) {
        this.ms_mid = ms_mid;
    }

    public Date getMs_time() {
        return ms_time;
    }

    public void setMs_time(Date ms_time) {
        this.ms_time = ms_time;
    }

    public Integer getMs_uid() {
        return ms_uid;
    }

    public void setMs_uid(Integer ms_uid) {
        this.ms_uid = ms_uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
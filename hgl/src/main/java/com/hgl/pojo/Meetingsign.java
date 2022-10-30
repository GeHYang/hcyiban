package com.hgl.pojo;

import java.util.Date;

public class Meetingsign {
    private Integer msId;

    private Integer msMid;

    private Date msTime;

    private Integer msUid;

    private String code;

    public Meetingsign(Integer msId, Integer msMid, Date msTime, Integer msUid, String code) {
        this.msId = msId;
        this.msMid = msMid;
        this.msTime = msTime;
        this.msUid = msUid;
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

    public Integer getMsMid() {
        return msMid;
    }

    public void setMsMid(Integer msMid) {
        this.msMid = msMid;
    }

    public Date getMsTime() {
        return msTime;
    }

    public void setMsTime(Date msTime) {
        this.msTime = msTime;
    }

    public Integer getMsUid() {
        return msUid;
    }

    public void setMsUid(Integer msUid) {
        this.msUid = msUid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}
package com.hgl.pojo;

import java.util.Date;

public class Meetingleave {
    private Integer mlId;

    private Integer mId;

    private String mlContent;

    private String mlImg;

    private Integer uid;

    private Integer vUid;

    private Date mlTime;

    private Integer state;

    public Meetingleave(Integer mId, String mlContent, String mlImg, Integer uid) {
        this.mId = mId;
        this.mlContent = mlContent;
        this.mlImg = mlImg;
        this.uid = uid;
    }

    public Meetingleave(Integer mlId, Integer mId, String mlContent, String mlImg, Integer uid, Integer vUid, Date mlTime, Integer state) {
        this.mlId = mlId;
        this.mId = mId;
        this.mlContent = mlContent;
        this.mlImg = mlImg;
        this.uid = uid;
        this.vUid = vUid;
        this.mlTime = mlTime;
        this.state = state;
    }

    public Meetingleave() {
        super();
    }

    public Integer getMlId() {
        return mlId;
    }

    public void setMlId(Integer mlId) {
        this.mlId = mlId;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getMlContent() {
        return mlContent;
    }

    public void setMlContent(String mlContent) {
        this.mlContent = mlContent == null ? null : mlContent.trim();
    }

    public String getMlImg() {
        return mlImg;
    }

    public void setMlImg(String mlImg) {
        this.mlImg = mlImg == null ? null : mlImg.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getvUid() {
        return vUid;
    }

    public void setvUid(Integer vUid) {
        this.vUid = vUid;
    }

    public Date getMlTime() {
        return mlTime;
    }

    public void setMlTime(Date mlTime) {
        this.mlTime = mlTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
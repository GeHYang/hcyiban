package com.htgl.pojo;

import java.util.Date;

public class Meetingleave {
    private Integer mlId;

    private Integer mId;

    private String ml_content;

    private String ml_img;

    private Integer uid;

    private Integer v_uid;

    private Date ml_time;

    private Integer state;

    public Meetingleave(Integer mId, String ml_content, String ml_img, Integer uid) {
        this.mId = mId;
        this.ml_content = ml_content;
        this.ml_img = ml_img;
        this.uid = uid;
    }

    public Meetingleave(Integer mlId, Integer mId, String ml_content, String ml_img, Integer uid, Integer v_uid, Date ml_time, Integer state) {
        this.mlId = mlId;
        this.mId = mId;
        this.ml_content = ml_content;
        this.ml_img = ml_img;
        this.uid = uid;
        this.v_uid = v_uid;
        this.ml_time = ml_time;
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

    public String getMl_content() {
        return ml_content;
    }

    public void setMl_content(String ml_content) {
        this.ml_content = ml_content;
    }

    public String getMl_img() {
        return ml_img;
    }

    public void setMl_img(String ml_img) {
        this.ml_img = ml_img;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getV_uid() {
        return v_uid;
    }

    public void setV_uid(Integer v_uid) {
        this.v_uid = v_uid;
    }

    public Date getMl_time() {
        return ml_time;
    }

    public void setMl_time(Date ml_time) {
        this.ml_time = ml_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
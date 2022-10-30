package com.hgl.pojo;

import java.util.Date;

public class Dutyleave {
    private Integer dlId;

    private Integer uid;

    private String content;

    private String img;

    private String time;

    private Date dlTime;

    private Integer vid;

    private Integer state;
    private String comment;

    public Dutyleave(Integer dlId, Integer uid, String content, String img, String time, Date dlTime, Integer vid, Integer state, String comment) {
        this.dlId = dlId;
        this.uid = uid;
        this.content = content;
        this.img = img;
        this.time = time;
        this.dlTime = dlTime;
        this.vid = vid;
        this.state = state;
        this.comment = comment;
    }

    public Dutyleave(Integer uid, String content, String img, String time) {
        this.uid = uid;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public Dutyleave() {
        super();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDlId() {
        return dlId;
    }

    public void setDlId(Integer dlId) {
        this.dlId = dlId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Date getDlTime() {
        return dlTime;
    }

    public void setDlTime(Date dlTime) {
        this.dlTime = dlTime;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
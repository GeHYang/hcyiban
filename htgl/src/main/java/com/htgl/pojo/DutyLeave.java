package com.htgl.pojo;

import java.util.Date;

public class DutyLeave {
    private Integer dlId;

    private Integer uid;

    private String content;

    private String img;

    private String time;

    private Date dl_time;

    private Integer vid;

    private Integer state;
    private String comment;

    public DutyLeave(Integer dlId, Integer uid, String content, String img, String time, Date dl_time, Integer vid, Integer state, String comment) {
        this.dlId = dlId;
        this.uid = uid;
        this.content = content;
        this.img = img;
        this.time = time;
        this.dl_time = dl_time;
        this.vid = vid;
        this.state = state;
        this.comment = comment;
    }

    public DutyLeave(Integer uid, String content, String img, String time) {
        this.uid = uid;
        this.content = content;
        this.img = img;
        this.time = time;
    }

    public DutyLeave() {
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

    public Date getDl_time() {
        return dl_time;
    }

    public void setDl_time(Date dl_time) {
        this.dl_time = dl_time;
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
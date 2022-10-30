package com.htgl.pojo;

public class Account {
    private Integer aid;
    private String sid;
    private String password;
    private String headIcon;
    private Integer state;

    public Account() {
    }

    public Account(Integer aid, String sid, String password, String headIcon, Integer state) {
        this.aid = aid;
        this.sid = sid;
        this.password = password;
        this.headIcon = headIcon;
        this.state = state;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

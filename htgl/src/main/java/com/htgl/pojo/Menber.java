package com.htgl.pojo;

public class Menber {
    private String sid;
    private String sname;
    private String dname;
    private String tname;
    private String cname;
    private String qq;
    private String phone;

    public Menber() {
    }

    public Menber(String sid, String sname, String dname, String tname, String cname, String qq, String phone) {
        this.sid = sid;
        this.sname = sname;
        this.dname = dname;
        this.tname = tname;
        this.cname = cname;
        this.qq = qq;
        this.phone = phone;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
                "sid:" + (sid == null ? "" : '\'' + sid + '\'') +
                ", sname:" + (sname == null ? "" : '\'' + sname + '\'') +
                ", dname:" + (dname == null ? "" : '\'' + dname + '\'') +
                ", tname:" + (tname == null ? "" : '\'' + tname + '\'') +
                ", cname:" + (cname == null ? "" : '\'' + cname + '\'') +
                ", qq:" + (qq == null ? "" : '\'' + qq + '\'') +
                ", phone:" + (phone == null ? "" : '\'' + phone + '\'') +
                '}';
    }
}

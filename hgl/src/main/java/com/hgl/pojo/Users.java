package com.hgl.pojo;

public class Users {
    private Integer uid;

    private String sid;

    private String ybUid;

    private String sname;

    private Integer did;

    private Integer tid;

    private String cname;

    private String qq;

    private String phone;

    private Integer state;

    private Integer vqx;

    public Users(Integer uid, String sid, String ybUid, String sname, Integer did, Integer tid, String cname, String qq, String phone, Integer state, Integer vqx) {
        this.uid = uid;
        this.sid = sid;
        this.ybUid = ybUid;
        this.sname = sname;
        this.did = did;
        this.tid = tid;
        this.cname = cname;
        this.qq = qq;
        this.phone = phone;
        this.state = state;
        this.vqx = vqx;
    }

    public Users() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getYbUid() {
        return ybUid;
    }

    public void setYbUid(String ybUid) {
        this.ybUid = ybUid == null ? null : ybUid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getVqx() {
        return vqx;
    }

    public void setVqx(Integer vqx) {
        this.vqx = vqx;
    }
}
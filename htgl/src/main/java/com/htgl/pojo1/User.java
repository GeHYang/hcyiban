package com.htgl.pojo1;

public class User implements Comparable<User> {
    String sid;
    String sname;
    String tid;
    int num;
    int preTime = 2;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getPreTime() {
        return preTime;
    }

    public void setPreTime(int preTime) {
        this.preTime = preTime;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "User{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", tid='" + tid + '\'' +
                ", num=" + num +
                ", preTime=" + preTime +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return this.getNum() > user.getNum() ? 1 : 0;
    }

    public int compareToTid(User user){
        int r1 = Integer.parseInt(this.getTid());
        int r2 = Integer.parseInt(user.getTid());
        return r1 > r2 ? 1 : 0;
    }
}

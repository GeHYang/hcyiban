package com.htgl.pojo1;

import java.util.ArrayList;
import java.util.List;

public class DutyTime implements Comparable<DutyTime> {
    private String time;//时间段
    private String day;//哪天

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<User> freeUsers = new ArrayList<User>();//该时间段无课成员
    public List<User> dutyUsers = new ArrayList<User>();//该时间段值班的成员

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int compareToTime(DutyTime dutyTime){
        int r1 = Integer.parseInt(this.getTime());
        int r2 = Integer.parseInt(dutyTime.getTime());
        return r1 > r2 ? 1 : 0;
    }

    @Override
    public int compareTo(DutyTime dutyTime) {
        return this.freeUsers.size() > dutyTime.freeUsers.size() ? 1 : 0;
    }
}

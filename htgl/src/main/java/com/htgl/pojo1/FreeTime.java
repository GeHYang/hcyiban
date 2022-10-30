package com.htgl.pojo1;

public class FreeTime {
    String sid;
    String []values;

    public FreeTime(){

    }
    public FreeTime(String sid, String []values){
        this.sid = sid;
        this.values = values;
    }
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}

package com.hgl.utils.response.entity;

/**
 * 响应数据类
 */
public class ResponseData extends Response {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                super.toString() +
                ", \"data\": " +
                data.toString() +
                "}";

    }
}

package com.htgl.utils.response.entity;

/**
 * 响应实体父类
 */
public class Response {

    public Response() {
    }

    public Response(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "\"code\":" + code;

    }
}

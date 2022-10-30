package com.htgl.utils.response.entity;

public enum ResponseEnum {
    SUCCESS(200,"成功"),
    FAIL(201,"失败"),
    SERVICE_ERROR(404, "服务器异常"),
    ILLEGAL_REQUEST(204, "非法请求"),
    ARGUMENT_VALID_ERROR(206, "参数校验错误"),

    LOGIN_ERROR(207, "用户名或密码错误"),
    LOGIN_AUTH(208, "未登录"),

    USER_EXIST(214, "用户已存在"),
    USER_NOT_EXIST(215, "用户不存在"),

    PERMISSION(209, "没有权限"),

    LOGIN_CODE(222, "长时间未操作,请重新登录"),
    CODE_ERROR(223, "验证码错误!"),
    TOKEN_ERROR(224, "Token无效!");

    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

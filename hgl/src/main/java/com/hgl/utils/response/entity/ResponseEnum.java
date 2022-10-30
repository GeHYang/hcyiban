package com.hgl.utils.response.entity;

public enum ResponseEnum {
    SUCCESS(200,"成功"),
    FAIL(201,"失败"),
    SERVICE_ERROR(404, "服务器异常"),
    ILLEGAL_REQUEST(204, "非法请求"),
    ARGUMENT_VALID_ERROR(206, "参数校验错误"),

    LOGIN_ERROR(207, "密码错误"),
    LOGIN_AUTH(208, "未登录"),

    USER_EXIST(214, "用户已存在"),
    USER_NOT_EXIST(215, "用户不存在"),

    PERMISSION(209, "没有权限"),

    LOGIN_CODE(222, "长时间未操作,请重新授权"),
    CODE_ERROR(223, "验证码错误!"),
    TOKEN_ERROR(224, "Token无效!"),

    DUTY_SIGN_EXISTS(1402, "已签到"),
    DUTY_SIGN_NOT_TIME(1401, "不在签到时间"),
    LEAVE_EXISTS(1501, "已提交过请假申请"),
    NOT_YB_MEMBER(1601, "未进行易班成员验证"),
    EXISTS_AUTH(1602, "已认证"),
    ;


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

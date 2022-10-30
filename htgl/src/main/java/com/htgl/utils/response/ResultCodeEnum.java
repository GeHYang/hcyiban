package com.htgl.utils.response;

public enum  ResultCodeEnum {

  SUCCESS(200,"成功"),
  FAIL(201,"失败"),
  SERVICE_ERROR(2012, "服务器异常"),
  ILLEGAL_REQUEST(204, "非法请求"),
  ARGUMENT_VALID_ERROR(206, "参数校验错误"),

  LOGIN_ERROR(207, "密码错误"),
  LOGIN_AUTH(208, "未登录"),
  PERMISSION(209, "没有权限"),

  LOGIN_CODE(222, "长时间未操作,绘画失效,请刷新页面后重试"),
  CODE_ERROR(223, "验证码错误!"),
  TOKEN_ERROR(224, "Token无效!")
  ;

  private Integer code;
  private String message;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  ResultCodeEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}

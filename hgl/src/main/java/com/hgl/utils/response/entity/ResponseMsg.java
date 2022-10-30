package com.hgl.utils.response.entity;

/**
 * 响应信息类
 */
public class ResponseMsg extends Response {
    private String msg;

    public ResponseMsg() {
        super();
    }

    public ResponseMsg(ResponseEnum responseEnum){
        super(responseEnum.getCode());
        this.msg = responseEnum.getMsg();
    }

    public ResponseMsg(int code, String msg) {
        super(code);
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{"
                + super.toString() + ","
                + "\"msg\":\""
                + msg + '\"'
                + "}";

    }
}

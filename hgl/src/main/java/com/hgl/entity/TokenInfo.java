package com.hgl.entity;

import java.util.Date;

/**
 * token响应类
 */
public class TokenInfo {
    private String token;
    private Date create_time; // 创建时间
    private Long expire_in; // 剩余时间

    public TokenInfo() {
    }

    public TokenInfo(String token, Date create_time, Long expire_in) {
        this.token = token;
        this.create_time = create_time;
        this.expire_in = expire_in;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(Long expire_in) {
        this.expire_in = expire_in;
    }
}

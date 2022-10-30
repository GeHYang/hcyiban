package com.hgl.service;

import com.hgl.utils.response.entity.Response;

public interface UserService {
    Response userAuth(String yb_uid, String sid, String sname);

    Response editUser(String sid, String sname, String yb_uid, String columnName, Object columnValue);
}

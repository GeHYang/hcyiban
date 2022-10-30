package com.hgl.service;

import com.hgl.utils.response.entity.Response;

public interface AuthService {
    Response getAccessToken(String verify_request);

    Response verifyToken(String token);
}

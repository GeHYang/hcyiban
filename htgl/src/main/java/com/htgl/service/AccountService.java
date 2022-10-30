package com.htgl.service;

import com.htgl.utils.response.entity.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface AccountService {
    Response login(String sid, String password) throws NoSuchAlgorithmException;

    Response resetPwd(String one_password, String two_password, String token) throws NoSuchAlgorithmException;

    Response resetHead(MultipartFile multipartFile, String token) throws IOException;

    Response verify(String token);

    Response editInterface(Integer vid, Boolean state);

    Response allView();
}

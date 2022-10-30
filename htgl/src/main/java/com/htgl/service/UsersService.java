package com.htgl.service;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.pojo.Users;
import com.htgl.utils.response.entity.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UsersService {
    PageResult queryUsers(QueryPageBean queryPageBean);

    Response addUser(Users users);

    Response delUserByUid(Integer uid);

    Response addMembersForExcel(MultipartFile file, String token) throws IOException, IllegalAccessException;
}

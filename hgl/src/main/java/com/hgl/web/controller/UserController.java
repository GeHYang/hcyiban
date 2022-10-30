package com.hgl.web.controller;

import com.hgl.service.UserService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    // 用户授权绑定
    @PostMapping
    public @ResponseBody Response userAuth(@RequestParam("yb_uid") String yb_uid, @RequestParam("sid") String sid,
                                           @RequestParam("sname") String sname){
        try {
            return userService.userAuth(yb_uid, sid, sname);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtils.response(ResponseEnum.FAIL);
        }
    }

    // 用户信息修改
    @PostMapping("/editUser")
    public @ResponseBody Response editUser(@RequestParam("sid") String sid, @RequestParam("sname") String sname,
                                           @RequestParam("yb_uid") String yb_uid, @RequestParam("columnName") String columnName,
                                           @RequestParam("columnValue") Object columnValue){
        try{
            return userService.editUser(sid, sname, yb_uid, columnName, columnValue);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

}

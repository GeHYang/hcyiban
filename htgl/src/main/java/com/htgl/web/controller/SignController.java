package com.htgl.web.controller;

import com.htgl.pojo.Sign;
import com.htgl.service.SignService;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableTransactionManagement
@Transactional
@RequestMapping("/sign")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SignController {

    @Autowired
    private SignService signService;
    // 签到
    @RequestMapping("/yibanMemberSign")
    public @ResponseBody Response sign(@RequestBody Sign sign){
        try{
            return signService.sign(sign);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }
}

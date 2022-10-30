package com.htgl.web.controller;

import com.htgl.service.AccountService;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@Transactional
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public Response login(String sid, String password){
        try{
            return accountService.login(sid, password);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error();
    }

    // 登录验证
    @RequestMapping("/verify")
    public Response verify(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        try{
            return accountService.verify(token);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error();
    }

    @PostMapping("/resetPwd")
    public Response resetPwd(String one_password, String two_password, HttpServletRequest req){
        try{
            // 获取token
            String token = req.getHeader("Authorization");
            return accountService.resetPwd(one_password, two_password, token);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }

    @RequestMapping("/resetHeadIcon")
    public Response resetPwd(MultipartHttpServletRequest req){
        try{
            // 获取token
            String token = req.getHeader("Authorization");
            // 获取图片
            MultipartFile multipartFile = req.getFile("file");
            return accountService.resetHead(multipartFile, token);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }

    /**
     * 开启/关闭接口
     * @param vid
     * @param state
     * @return
     */
    @PostMapping("/editInter")
    public Response on_off_interface(@RequestParam("vid") Integer vid, @RequestParam("state") Boolean state){
        try{
            return accountService.editInterface(vid, state);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }

    @GetMapping("/allView")
    public Response allView(){
        try{
            return accountService.allView();
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error();
    }

}

package com.hgl.web.controller;

import com.hgl.service.AuthService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证
 */
@RestController
@Transactional
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/{verify_request}")
    public Response getAccessToken(@PathVariable("verify_request") String verify_request){
        try{
            return authService.getAccessToken(verify_request);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    @GetMapping("/verify")
    public Response verify(HttpServletRequest request){
        try{
            // 获取请求token
            String token = request.getHeader("Authorization");
            if(token == null || "".equals(token)){
                return ResponseUtils.response(ResponseEnum.ILLEGAL_REQUEST);
            }
            return authService.verifyToken(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

}

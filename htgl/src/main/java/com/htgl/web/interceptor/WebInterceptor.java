package com.htgl.web.interceptor;

import com.htgl.utils.TokenUtil;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //编码转换
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");// 允许跨域
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        // 校验token
        // 判断是否为登录接口
        String uri = request.getRequestURI();
        if(uri.equals("/ht/user") && request.getMethod().toLowerCase().equals("post")){
            return true;
        }

        String token = request.getHeader("Authorization");
        if(token != null && !token.equals("")){
            // token存在，校验合法性
            boolean verify = TokenUtil.verify(token);
            if(verify) return true; // 放行
        }
        response.getWriter().println(ResponseUtils.response(ResponseEnum.PERMISSION));
        return false;
    }
}

package com.hgl.web.interceptor;

import com.hgl.utils.TokenUtil;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.ResponseEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ProjectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //编码转换
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, Accept, Origin, User-Agent, Content-Range, Content-Disposition, Content-Description");
        // 校验token
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

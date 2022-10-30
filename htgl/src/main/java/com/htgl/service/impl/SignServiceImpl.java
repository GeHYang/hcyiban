package com.htgl.service.impl;

import com.htgl.mapper.SignMapper;
import com.htgl.pojo.Sign;
import com.htgl.service.SignService;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;
    public Response sign(Sign sign) {
        // 校验数据
        if(sign.getSid() == null || "".equals(sign.getSid()) ||
                sign.getSname() == null || "".equals(sign.getSname())){
            return ResponseUtils.error("请填写学号姓名");
        }
        // 判断是否已签到
        Sign sign1 = signMapper.existsSign(sign.getSid());
        // 每个小时只能签到一次
        //
        if(sign1 != null){
            // 已签到
            return ResponseUtils.respMsg("已签到");
        }
        // 签到
        signMapper.sign(sign);
        return ResponseUtils.respMsg("签到成功");
    }
}

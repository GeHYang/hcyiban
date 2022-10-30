package com.hgl.service.impl;

import com.hgl.mapper.UsersMapper;
import com.hgl.pojo.Users;
import com.hgl.service.UserService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    public Response userAuth(String yb_uid, String sid, String sname) {
        if(yb_uid == null || yb_uid.equals("") || sid == null || "".equals(sid) || sname == null || "".equals(sname))
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        // 通过易班id获取用户id及学号
        Users user = usersMapper.selectBySidAndSname(sid, sname);
        if(user == null){
            return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);
        } else if(user.getYbUid() != null && !"".equals(user.getYbUid())){
            // 已经认证
            return ResponseUtils.response(ResponseEnum.EXISTS_AUTH);
        }
        user.setYbUid(yb_uid);
        usersMapper.bindUserByYbUid(user);
        return ResponseUtils.success();
    }

    public Response editUser(String sid, String sname, String yb_uid, String columnName, Object columnValue) {
        if(sid == null || "".equals(sid) || sname == null || "".equals(sname) || yb_uid == null || "".equals(yb_uid)
            || columnName == null || "".equals(columnName)){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sid", sid);
        map.put("sname", sname);
        map.put("yb_uid", yb_uid);
        map.put("columnName", columnName);
        map.put("columnValue", columnValue);
        usersMapper.updateBySidAndYbUidAndSname(map);
        return ResponseUtils.response(ResponseEnum.SUCCESS);
    }
}

package com.hgl.service.impl;

import com.hgl.mapper.ViewMapper;
import com.hgl.service.ViewService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ViewServiceImpl implements ViewService {
    @Autowired
    private ViewMapper viewMapper;
    public JSONObject duty_edit() {
        Map map = viewMapper.queryView();
        if(map != null) {
            JSONObject jsonObject = new JSONObject(map);
            return jsonObject;
        }
        return null;
    }
}

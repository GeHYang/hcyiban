package com.hgl.web.controller;

import com.hgl.service.DutyService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 值班签到
 */
@RestController
@RequestMapping("/duty")
@Transactional
public class DutyController {

    @Autowired
    private DutyService dutyService;

    // 查询
    @GetMapping
    public Response selectByUidAndMonth(@RequestParam("uid") Integer uid, @RequestParam("month") String month,
                                        @RequestParam("flag") Integer flag){
        try{
            Map map = new HashMap();
            map.put("uid", uid);
            map.put("month", month);
            if(flag == 1)
                return dutyService.selectByUidAndMonth(map);
            if(flag == 2)
                return dutyService.selectByUidAndDate(map);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
    // 签到
    @PostMapping
    /**
     * @RequestBody： JSON字符串
     * RequestParam:
     */
    public Response dutySign(@RequestParam("uid") Integer uid){
        try{
            return dutyService.dutySign(uid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
    // 值班补签
    @PostMapping("/repair")
    public Response repair(@RequestParam("uid") Integer uid, @RequestParam("repair_datetime") String repair_datetime){
        try{
            return dutyService.repair(uid, repair_datetime);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    @PostMapping("/dutyLeave")
    public Response dutyLeave(MultipartHttpServletRequest request){
        try{
            // 获取非文件数据
            Map map = request.getParameterMap();
            // 获取文件数据, 转换为json
            String info = Arrays.toString((String[]) map.get("info"));
            JSONArray ja = new JSONArray(info);
            JSONObject json = (JSONObject) ja.get(0);
            // 获取文件
            MultipartFile file = request.getFile("file");
            return dutyService.dutyLeave(file, json);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    /**
     * 课程表上传
     * @param data
     * @return
     */
    @PostMapping("/freetime")
    public Response freetime(@RequestParam("data") String data){
        JSONObject object = new JSONObject(data);
        try {
            return dutyService.freetime(object);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
}

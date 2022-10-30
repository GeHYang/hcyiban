package com.hgl.service;

import com.hgl.utils.response.entity.Response;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface DutyService {
    Response selectByUid(Integer uid);

    Response selectByUidAndMonth(Map map);
    Response selectByUidAndDate(Map map);

    Response findLastSignTimeByUid(Integer uid);

    Response dutySign(Integer uid);
    Response repair(Integer uid, String repair_datetime);

    Response agreeSign(Integer did);

    Response dutyLeave(MultipartFile file, JSONObject json);

    Response freetime(JSONObject object);
}

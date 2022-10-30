package com.hgl.service;

import com.hgl.utils.response.entity.Response;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

/**
 * 会议
 */
public interface MeetingService {
    Response selectAll() throws ParseException;

    Response signMeeting(Integer mid, Integer uid, String code);

    Response getAllMid();

    Response meetingLeave(MultipartFile file, JSONObject json);
}

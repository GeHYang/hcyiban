package com.hgl.web.controller;

import com.hgl.service.MeetingService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/meeting")
@Transactional
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping
    public Response getMeetings(){
        try{
            return meetingService.selectAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
    @PostMapping("/sign")
    public Response signMeeting(@RequestParam("mid") Integer mid, @RequestParam("uid") Integer uid, @RequestParam("code") String code){
        try{
            return meetingService.signMeeting(mid, uid, code);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    @PostMapping("/mids")
    public Response getAllMid(){
        try{
            return meetingService.getAllMid();
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    @PostMapping("/meetingLeave")
    public Response meetingLeave(@RequestParam("file")MultipartFile file, @RequestParam("info")String info){
        try{
            JSONObject json = new JSONObject(info);
            return meetingService.meetingLeave(file, json);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

}

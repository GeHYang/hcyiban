package com.htgl.web.controller;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.pojo.Meeting;
import com.htgl.service.MeetingService;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    @PostMapping("/query")
    public PageResult queryMeeting(@RequestBody QueryPageBean queryPageBean){
        try{
            return meetingService.queryMeeting(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }
    @DeleteMapping("/{mid}")
    public Response delMeeting(@PathVariable("mid") Integer mid){
        try{
            return meetingService.delMeeting(mid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
    @PostMapping("/add")
    public Response addMeeting(@RequestBody Meeting meeting){
        try{
            return meetingService.addMeeting(meeting);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }
    // 获取所有会议标签
    @GetMapping
    public Response getAllMid(){
        try{
            return meetingService.getAllMid();
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }
    // 获取会议签到
    @PostMapping("/querySign")
    public PageResult queryMeetingSign(@RequestBody QueryPageBean queryPageBean){
        try{
            return meetingService.queryMeetingSign(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }
    // 获取会议请假
    @PostMapping("/queryLeave")
    public PageResult queryLeave(@RequestBody QueryPageBean queryPageBean){
        try{
            return meetingService.queryMeetingLeave(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }
    // 同意请假
    @PostMapping("/agree")
    public Response agreeLeave(@RequestParam("ml_id") Integer ml_id, @RequestParam("uid") Integer uid){
        try{
            return meetingService.agreeLeave(ml_id, uid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error();
    }

    @RequestMapping("/export")
    public Response export(QueryPageBean queryPageBean){
        try{
            return meetingService.export(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("错误");
    }
}

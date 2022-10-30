package com.hgl.service.impl;

import com.hgl.mapper.MeetingMapper;
import com.hgl.mapper.MeetingleaveMapper;
import com.hgl.mapper.UsersMapper;
import com.hgl.pojo.Meeting;
import com.hgl.pojo.Meetingleave;
import com.hgl.pojo.Meetingsign;
import com.hgl.pojo.Users;
import com.hgl.service.MeetingService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Value("${SAVE_PATH}")
    private String SAVE_PATH;// 存储路径
    @Value("${NAT_PATH}")
    private String NAT_PATH;
    @Autowired
    private MeetingMapper meetingMapper;
    @Autowired
    private UsersMapper usersMapper;
    public Response selectAll() throws ParseException {
        List<LinkedHashMap> meetingList = meetingMapper.selectAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0; i < meetingList.size(); i++){
            LinkedHashMap map = meetingList.get(i);
            long startTime = sdf.parse((String)map.get("start_time")).getTime();
            long endTime = sdf.parse((String)map.get("end_time")).getTime();
            long nowTime = new Date().getTime();
            if(nowTime - endTime > 0){
                meetingList.get(i).put("state","0"); // 已结束
            } else if(nowTime - startTime >= (30*60*60)){
                meetingList.get(i).put("state","1"); // 已结束
            } else{
                meetingList.get(i).put("state","2"); // 已结束
            }
        }
        return ResponseUtils.respData(meetingList);
    }
    public Response signMeeting(Integer mid, Integer uid, String code) {
        if(mid == 0 || uid == 0 || code == null || code.equals(""))
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        if(usersMapper.selectByPrimaryKey(uid) == null)
            return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);
        // 判断是否已签到
        Map<String, Integer> map = new HashMap<>();
        map.put("uid", uid);
        map.put("mid", mid);
        Integer res = meetingMapper.selectByUidAndMid(map);
        if(res > 0) return ResponseUtils.respMsg("已签到");

        Meetingsign meetingsign = new Meetingsign();
        meetingsign.setMsMid(mid);
        meetingsign.setMsUid(uid);
        meetingsign.setCode(code);
        meetingMapper.signMeeting(meetingsign);
        return ResponseUtils.success();
    }

    public Response getAllMid() {
        List<Integer> mids = meetingMapper.selectAllMid();
        return ResponseUtils.respData(mids);
    }

    public Response meetingLeave(MultipartFile file, JSONObject json) {
        // 检查参数
        if(json.getString("sid") == null || "".equals(json.getString("sid")) ||
                json.getString("sname") == null || "".equals(json.getString("sname")) ||
                json.getString("content") == null || "".equals(json.getString("content")) ||
                json.getInt("mid") == 0 || file == null
        ){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 判断用户是否存在
        Users user = usersMapper.selectBySidAndSname(json.getString("sid"), json.getString("sname"));
        if(user == null){// 用户不存在
            return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);
        }
        // 判断会议是否存在
        Meeting meeting = meetingMapper.selectByPrimaryKey(json.getInt("mid"));
        if(meeting == null) return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);

        // 判断是否已请假
        if(meetingMapper.existsMeetingLeave(user.getUid(), meeting.getMid()) > 0){
            return ResponseUtils.response(ResponseEnum.LEAVE_EXISTS);
        }

        // 1、时间戳生成图片名
        String fileName = new Date().getTime() + "";

        // 2、获取图片后缀
        String[] suffixs = file.getOriginalFilename().split("\\.");
        String suffix = suffixs[suffixs.length - 1];

        // 3、校验图片格式
        if(!"jpg".equals(suffix) && !"jpeg".equals(suffix) && !"png".equals(suffix)){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 4、拼接图片名
        fileName += "." + suffix;
        // 5、保存图片
        File fileS = new File(SAVE_PATH + "leaveImg/", fileName);
        try {
            file.transferTo(fileS);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtils.response(ResponseEnum.FAIL);
        }
        // 6、生成网络路径
        String imgPath = NAT_PATH + fileName;
        Meetingleave meetingleave = new Meetingleave(json.getInt("mid"), json.getString("content"), imgPath, user.getUid());
        meetingMapper.meetingLeave(meetingleave);
        return ResponseUtils.success();
    }
}

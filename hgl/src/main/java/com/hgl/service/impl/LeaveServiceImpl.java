package com.hgl.service.impl;

import com.hgl.mapper.LeaveMapper;
import com.hgl.service.LeaveService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;
    public Response getAllLeaveByUid(Integer uid) {
        List dutyLeave = leaveMapper.getDutyLeaveByUid(uid);
        List meetingLeave = leaveMapper.getMeetingLeaveByUid(uid);
        Map<String, Object> map = new HashMap<>();
        map.put("dutyLeave", dutyLeave);
        map.put("meetingLeave", meetingLeave);
        return ResponseUtils.respData(map);
    }
}

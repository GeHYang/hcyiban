package com.htgl.service;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.pojo.Meeting;
import com.htgl.utils.response.entity.Response;

import java.io.IOException;
import java.text.ParseException;

public interface MeetingService {
    PageResult queryMeeting(QueryPageBean queryPageBean);

    Response delMeeting(Integer mid);

    Response addMeeting(Meeting meeting) throws ParseException;

    Response getAllMid();

    PageResult queryMeetingSign(QueryPageBean queryPageBean);

    PageResult queryMeetingLeave(QueryPageBean queryPageBean);

    Response agreeLeave(Integer ml_id, Integer uid);

    Response export(QueryPageBean queryPageBean) throws IOException;
}

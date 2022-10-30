package com.htgl.service.impl;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.mapper.MeetingMapper;
import com.htgl.pojo.Meeting;
import com.htgl.service.MeetingService;
import com.htgl.utils.QRCodeUtil;
import com.htgl.utils.poi.ExportExcelUtil;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;
    @Value("${static_path}")
    private String static_path;
    @Value("${net_path}")
    private String net_path;
    public PageResult queryMeeting(QueryPageBean queryPageBean) {
        return new PageResult(meetingMapper.queryMeetingCount(queryPageBean), meetingMapper.queryMeeting(queryPageBean));
    }

    public Response delMeeting(Integer mid) {
        if(mid == 0) return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        meetingMapper.delMeeting(mid);
        return ResponseUtils.success();
    }

    public Response addMeeting(Meeting meeting) throws ParseException {
        if(meeting == null || meeting.getEnd_time() == null || "".equals(meeting.getEnd_time())
                || meeting.getStart_time() == null || "".equals(meeting.getStart_time()) ||
                meeting.getMcontent() == null || "".equals(meeting.getMcontent()) ||
                meeting.getMtitle() == null || meeting.getMtitle().equals("")){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 生成二维码
        // 生成二维码
        String preUrl = net_path + "/qrcodes/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse(meeting.getStart_time());
        String fileName = parse.getTime() + ".png";
        String mcode = QRCodeUtil.CodeCreate(static_path + "qrcodes/", preUrl, 500, 500, fileName, "png");
        meeting.setMcode(mcode);
        meetingMapper.addMeeting(meeting);
        return ResponseUtils.response(ResponseEnum.SUCCESS);
    }

    public Response getAllMid() {
        List<Integer> mids = meetingMapper.getAllMid();
        return ResponseUtils.respData(mids);
    }

    public PageResult queryMeetingSign(QueryPageBean queryPageBean) {
        return new PageResult(meetingMapper.queryMeetingSignCount(queryPageBean), meetingMapper.queryMeetingSign(queryPageBean));
    }

    public PageResult queryMeetingLeave(QueryPageBean queryPageBean) {
        return new PageResult(meetingMapper.queryMeetingLeaveCount(queryPageBean), meetingMapper.queryMeetingLeave(queryPageBean));
    }

    public Response agreeLeave(Integer ml_id, Integer uid) {
        if(ml_id == 0 || uid == 0) return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        meetingMapper.agreeLeave(ml_id, uid);
        return ResponseUtils.success();
    }

    public Response export(QueryPageBean queryPageBean) throws IOException {
        List list = meetingMapper.export(queryPageBean);
        return exportMeetingCount(list);
    }

    // 会议统计导出
    private Response exportMeetingCount(List list) throws IOException {

        OutputStream out = null;
        String[] header = new String[]{"签到编号", "会议编号", "学号", "姓名", "签到时间"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(new Date()) + ".xlsx";
        out = new FileOutputStream(new File(static_path + "excels/meeting_" +
                fileName));
        ExportExcelUtil.exportExcelByRows("测试", header, list, out);
        out.close();
        return ResponseUtils.respMsg(net_path + "/excels/meeting_" + fileName);
    }
}

package com.hgl.service.impl;

import com.hgl.mapper.DutyMapper;
import com.hgl.mapper.FreeTimeMapper;
import com.hgl.mapper.UsersMapper;
import com.hgl.pojo.Duty;
import com.hgl.pojo.Dutyleave;
import com.hgl.pojo.FreeTime;
import com.hgl.pojo.Users;
import com.hgl.service.DutyService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 值班签到
 */
@Service
public class DutyServiceImpl implements DutyService {

    @Value("${SAVE_PATH}")
    private String SAVE_PATH;// 存储路径
    @Value("${NAT_PATH}")
    private String NAT_PATH;
    @Autowired
    private DutyMapper dutyMapper;
    @Autowired
    private FreeTimeMapper freeTimeMapper;
    @Autowired
    private UsersMapper usersMapper;

    public Response selectByUid(Integer uid) {
        return null;
    }

    public Response selectByUidAndMonth(Map map) {
        // 初次校验
        if((Integer) map.get("uid") == 0 || (map.get("month") == null || "".equals(map.get("month")))){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        List<Duty> dutyList = dutyMapper.selectByUidAndMonth(map);
        return ResponseUtils.respData(dutyList);
    }

    public Response selectByUidAndDate(Map map) {
        // 初次校验
        if((Integer) map.get("uid") == 0 || (map.get("month") == null || "".equals(map.get("month")))){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        List<Duty> dutyList = dutyMapper.selectByUidAndDate(map);
        return ResponseUtils.respData(dutyList);
    }

    public Response findLastSignTimeByUid(Integer uid) {
        return null;
    }

    /**
     * 值班签到
     * @param uid
     * @return
     */
    public Response dutySign(Integer uid) {
        if(uid == 0) return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        // 判断用户是否存在
        Users user = usersMapper.selectByPrimaryKey(uid);
        if(user == null) return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);
        // 获取该成员最后一次签到时间
        Date lastSignTimeByUid = dutyMapper.findLastSignTimeByUid(uid);
        Date end_time = lastSignTimeByUid;
        // 定义标志
        if(end_time != null){
            Date date = new Date();
            Long l = date.getTime() - end_time.getTime();
            // 判断是否已签到
            if(l < 60*60*1000){// 间隔一个小时，判定为已签到
                return ResponseUtils.response(ResponseEnum.DUTY_SIGN_EXISTS);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String signTime = sdf.format(new Date());
        // 所有时间段
        String[]times = {"08:00-09:25","09:50-12:00","14:40-16:05",
                "16:30-17:55","19:40-21:05"};
        // 切割判断
        String t = signTime.split(" ")[0];
        String t2 = signTime.split(" ")[0];
        String t1 = signTime.split(" ")[1];

        t1 = t1.split(":")[0];// 获得小时
        if(t1.equals("08") || t1.equals("07")){
            t += " " + times[0];
        } else if(t1.equals("09") || t1.equals("10")){
            t += " " + times[1];
        } else if(t1.equals("14") || t1.equals("15")){
            t += " " + times[2];
        } else if(t1.equals("16")){
            t += " " + times[3];
        } else if(t1.equals("19") || t1.equals("20")){
            t += " " + times[4];
        }

        //时间校验
        Date date = null;
        boolean b = false;
        try {
            int count = 2;
            date = sdf.parse(signTime);// 转成date
            for(int i = 0; i < times.length; i++){
                Date date1 = sdf.parse(t2 + " " + times[i].split("-")[0] + ":00");// 添加秒数
                long d = date.getTime();// 提交签到的时间
                long d1 = date1.getTime();// 当天签到的时间段

                if(d - d1 <= (20 * 60 * 1000) && d - d1 >= -(10 * 60 * 1000)){
                    // 判断是否在签到时间开始前10分钟或开始后20分钟
                    b = true;
                    if(i == 1){
                        count = 3;
                    } else{
                        count = 2;
                    }
                    break;
                }
            }
            if(b){// 符合签到条件
                Duty duty = new Duty();
                duty.setSignTime(signTime);
                duty.setTime(t);// 设置时间段
                duty.setCount(count);
                duty.setSname(uid + "");
                duty.setState(1);
                dutyMapper.dutySign(duty);
                return ResponseUtils.response(ResponseEnum.SUCCESS);
            } else{
                return ResponseUtils.response(ResponseEnum.DUTY_SIGN_NOT_TIME);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    public Response repair(Integer uid, String repair_datetime) {
        if(uid == 0 || (repair_datetime == null || repair_datetime.equals(""))) return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        // 判断用户是否存在
        Users user = usersMapper.selectByPrimaryKey(uid);
        if(user == null) return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String signTime = repair_datetime;
        // 所有时间段
        String[]times = {"08:00-09:25","09:50-12:00","14:40-16:05",
                "16:30-17:55","19:40-21:05"};
        // 切割判断
        String t = signTime.split(" ")[0];
        String t2 = signTime.split(" ")[0];
        String t1 = signTime.split(" ")[1];

        t1 = t1.split(":")[0];// 获得小时
        if(t1.equals("08")){
            t += " " + times[0];
        } else if(t1.equals("09") || t1.equals("10")){
            t += " " + times[1];
        } else if(t1.equals("14") || t1.equals("15")){
            t += " " + times[2];
        } else if(t1.equals("16")){
            t += " " + times[3];
        } else if(t1.equals("19") || t1.equals("20")){
            t += " " + times[4];
        }

        //时间校验
        Date date = null;
        boolean b = false;
        try {
            int count = 2;
            date = sdf.parse(signTime);// 转成date
            for(int i = 0; i < times.length; i++){
                Date date1 = sdf.parse(t2 + " " + times[i].split("-")[0] + ":00");// 添加秒数
                long d = date.getTime();// 提交签到的时间
                long d1 = date1.getTime();// 当天签到的时间段

                if(d - d1 <= (20 * 60 * 1000) && d - d1 >= -(10 * 60 * 1000)){
                    // 判断是否在签到时间开始前10分钟或开始后20分钟
                    b = true;
                    if(i == 1){
                        count = 3;
                    } else{
                        count = 2;
                    }
                    break;
                }
            }
            if(b){// 符合签到条件
                Duty duty = new Duty();
                duty.setSignTime(signTime);
                duty.setTime(t);// 设置时间段
                duty.setCount(count);
                duty.setSname(uid + "");
                duty.setState(2);// 补签
                dutyMapper.dutySign(duty);
                return ResponseUtils.response(ResponseEnum.SUCCESS);
            } else{
                return ResponseUtils.response(ResponseEnum.DUTY_SIGN_NOT_TIME);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    public Response agreeSign(Integer did) {
        return null;
    }

    // 值班请假
    public Response dutyLeave(MultipartFile file, JSONObject json) {
        if(json.get("sid") == null || "".equals(json.get("sid").toString().trim()) ||
            json.get("sname") == null || "".equals(json.get("sname").toString().trim()) ||
            json.get("content") == null || "".equals(json.get("content").toString().trim()) ||
            json.get("time") == null || "".equals(json.get("time")) ||
            file == null){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 判断用户是否存在
        Users user = usersMapper.selectBySid(json.get("sid").toString());
        if(user == null){// 用户不存在
            return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);
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
        // 7、创建请假对象
        Dutyleave dutyleave = new Dutyleave(user.getUid(), json.getString("content"), imgPath, json.getString("time"));
        // 存储数据库
        dutyMapper.dutyLeave(dutyleave);
        return ResponseUtils.response(ResponseEnum.SUCCESS);
    }

    public Response freetime(JSONObject object) {
        JSONObject info = object.getJSONObject("info");
        JSONObject times = object.getJSONObject("times");
        if(info.getString("sid") == null || "".equals(info.getString("sid"))
                || info.getString("sname") == null || "".equals(info.getString("sname"))){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        Users users = usersMapper.selectBySid(info.getString("sid"));
        if(users == null){
            return ResponseUtils.response(ResponseEnum.USER_NOT_EXIST);
        }
        if(!users.getSname().equals(info.getString("sname"))){
            return ResponseUtils.respMsg(114, "学号与姓名不匹配");
        }
        FreeTime freeTime = new FreeTime(info.getString("sid"), times.getString("mon"), times.getString("tue"),
                times.getString("wed"), times.getString("thurs"), times.getString("fri"), times.getString("sat"));
        // 判断是否已存在
        if(freeTimeMapper.queryFreeTimeBySid(freeTime.getSid()) != null){
            // 删除空闲时间
            freeTimeMapper.delFreeTime(freeTime.getSid());
        }
        freeTimeMapper.addFreeTime(freeTime);
        return ResponseUtils.success();
    }
}

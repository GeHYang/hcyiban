package com.htgl.mapper;

import com.htgl.entity.QueryPageBean;
import com.htgl.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface MeetingMapper {
    List queryMeeting(QueryPageBean queryPageBean);
    Integer queryMeetingCount(QueryPageBean queryPageBean);
    boolean addMeeting(Meeting meeting);
    @Select("call delMeeting(${mid});")
    void delMeeting(@Param("mid") Integer mid);

    List queryMeetingSign(QueryPageBean queryPageBean);
    Integer queryMeetingSignCount(QueryPageBean queryPageBean);
    List<Integer> getAllMid();
    List queryMeetingLeave(QueryPageBean queryPageBean);
    Integer queryMeetingLeaveCount(QueryPageBean queryPageBean);
    @Update("update meetingLeave set state = 1, v_uid = ${uid} where ml_id = ${ml_id}")
    boolean agreeLeave(@Param("ml_id") Integer ml_id, @Param("uid") Integer uid);

    List export(QueryPageBean queryPageBean);
}

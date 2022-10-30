package com.hgl.mapper;

import com.hgl.pojo.Meeting;
import com.hgl.pojo.Meetingleave;
import com.hgl.pojo.Meetingsign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface MeetingMapper {
    Meeting selectByPrimaryKey(Integer mid);
    List selectAll();

    boolean signMeeting(Meetingsign meetingsign);

    List<String> selectByUidForMid(Integer uid);

    List<Integer> selectAllMid();

    Integer selectByUidAndMid(Map<String, Integer> map);

    boolean meetingLeave(Meetingleave meetingleave);

    @Select("select count(*) from meetingleave where m_id = ${mid} and uid = ${uid}")
    int existsMeetingLeave(@Param("uid") Integer uid, @Param("mid") Integer mid);
}
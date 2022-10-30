package com.hgl.mapper;

import com.hgl.pojo.Meetingleave;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingleaveMapper {
    Meetingleave selectByPrimaryKey(Integer mlId);
}
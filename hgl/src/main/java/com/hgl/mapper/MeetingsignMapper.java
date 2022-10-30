package com.hgl.mapper;

import com.hgl.pojo.Meetingsign;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingsignMapper {
    int insert(Meetingsign record);

    int insertSelective(Meetingsign record);

    Meetingsign selectByPrimaryKey(Integer msId);
}
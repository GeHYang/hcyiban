package com.hgl.mapper;

import com.hgl.pojo.FreeTime;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface FreeTimeMapper {


    void addFreeTime(FreeTime freeTime);

    // 学号查空闲情况
    @Select("select * from mon where sid = #{sid}")
    Map queryFreeTimeBySid(@Param("sid") String sid);

    // 更新空闲情况
    void delFreeTime(@Param("sid") String sid);

}

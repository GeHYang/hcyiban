package com.htgl.mapper;

import com.htgl.pojo.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignMapper {

    // 签到
    boolean sign(Sign sign);
    // 判断是否已签到
    Sign existsSign(@Param("sid") String sid);

}

package com.hgl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveMapper {
    List getDutyLeaveByUid(Integer uid);
    List getMeetingLeaveByUid(Integer uid);
}

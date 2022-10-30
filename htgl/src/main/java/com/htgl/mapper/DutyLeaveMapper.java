package com.htgl.mapper;

import com.htgl.entity.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DutyLeaveMapper {
    List queryDutyLeave(QueryPageBean queryPageBean);
    Integer queryDutyLeaveCount(QueryPageBean queryPageBean);
    // 同意请假
    @Update("update dutyLeave set state = 1, vid = ${vid} where dl_id = ${dl_id}")
    boolean agreeDutyLeave(@Param("dl_id") Integer dl_id, @Param("vid") Integer vid);
}

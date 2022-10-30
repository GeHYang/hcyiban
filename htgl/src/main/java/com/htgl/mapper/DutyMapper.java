package com.htgl.mapper;

import com.htgl.entity.QueryPageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DutyMapper {
    List queryDuty(QueryPageBean queryPageBean);

    Integer queryDutyCount(QueryPageBean queryPageBean);
    List queryDutyRepair(QueryPageBean queryPageBean);

    Integer queryDutyRepairCount(QueryPageBean queryPageBean);

    // 同意补签
    @Update("update duty set `state` = 1 where did = ${did}")
    boolean agreeRepair(@Param("did") Integer did);
    // 获取所有月份
    @Select("select DISTINCT date_format(sign_time, '%Y-%m') from duty")
    List<String> findAllMonth();

    List queryDutyByCount(QueryPageBean queryPageBean);

    Integer queryDutyByCountNum(QueryPageBean queryPageBean);

    List export(QueryPageBean queryPageBean);
}

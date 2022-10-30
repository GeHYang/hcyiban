package com.hgl.mapper;

import com.hgl.pojo.Duty;
import com.hgl.pojo.Dutyleave;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface DutyMapper {

    List<Duty> selectByUid(Integer uid);

    List<Duty> selectByUidAndMonth(Map map);

    List<Duty> selectByUidAndDate(Map map);

    Date findLastSignTimeByUid(Integer uid);

    boolean dutySign(Duty duty);

    boolean agreeSign(Integer did);

    boolean dutyLeave(Dutyleave dutyleave);

}
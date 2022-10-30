package com.hgl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface ViewMapper {

    @Select("select * from `view` where vqx = 1")
    Map queryView();

}

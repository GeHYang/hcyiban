package com.htgl.mapper;

import com.htgl.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户
 */
@Mapper
public interface AccountMapper {
    /**
     * 登录
     * @param map : keys[sid, password]
     * @return
     */
    Account queryAccount(Map<String, String> map);

    @Update("update account set state = ${state} where aid = ${aid}")
    boolean setState(@Param("aid") Integer aid, @Param("state") Integer state);

    boolean resetPassword(Map map);

    boolean resetHeadIcon(Map map);

    @Update("update `view` set vqx = ${state} where vid = ${vid}")
    void editView(@Param("vid") Integer vid, @Param("state") Integer state);
    // 获取所有视图
    @Select("select * from `view`")
    List<LinkedHashMap<String, Object>> allView();
}

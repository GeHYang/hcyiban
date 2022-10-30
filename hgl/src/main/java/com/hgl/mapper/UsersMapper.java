package com.hgl.mapper;

import com.hgl.pojo.Users;
import com.hgl.pojo.UsersExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {
    long countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Map<String, Object> selectUidAndSidByYbUid(@Param("yb_uid") String yb_uid);

    Users selectBySid(String sid);

    @Select("select uid, sid, yb_uid as ybUid, sname, did, tid, cname, qq, phone, state, vqx from users where sid = #{sid} and sname = #{sname}")
    Users selectBySidAndSname(@Param("sid") String sid, @Param("sname") String sname);

    @Select("select uid, sid, yb_uid as ybUid, sname, did, tid, cname, qq, phone, state, vqx from users where yb_uid = #{yb_uid}")
    Users findUserByYbUid(@Param("yb_uid") String yb_uid);

    boolean bindUserByYbUid(Users user);

    boolean updateBySidAndYbUidAndSname(Map map);
}
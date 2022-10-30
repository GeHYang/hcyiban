package com.htgl.mapper;

import com.htgl.entity.QueryPageBean;
import com.htgl.pojo.Menber;
import com.htgl.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UsersMapper {
    /**
     * 查询
     * @param queryPageBean
     * @return
     */
    List queryUsers(QueryPageBean queryPageBean);

    /**
     * 条件查询数量
     * @param queryPageBean
     * @return
     */
    Integer queryUsersCount(QueryPageBean queryPageBean);

    /**
     * 添加成员
     * @param users
     * @return
     */
    boolean addUser(Users users);

    void addUser1(Menber menber);

    /**
     * 通过id删除成员
     * @param uid
     * @return
     */
    @Select("call delUser(${uid});")
    void delUserByUid(Integer uid);

    /**
     * 通过学号删除成员
     * @param sid
     * @return
     */
    @Select("call delUser((select uid from users where sid = #{sid}));")
    void delUserBySid(String sid);
    // 学号查成员
    Map queryUserBySid(String sid);

    @Select("select * from users where sid = #{sid}")
    Users queryUserBySid1(@Param("sid") String sid);
}

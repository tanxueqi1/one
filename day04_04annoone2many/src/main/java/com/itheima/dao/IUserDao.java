package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中，针对CRUD一共有四个注解
 * @Select @Update @Delete @Insert
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap",value = {
            @Result(id=true,property = "userId",column = "id"),
            @Result(property = "userName",column = "username"),
            @Result(property = "userSex",column = "sex"),
            @Result(property = "userBirthday",column = "birthday"),
            @Result(property = "userAddress",column = "address"),
            @Result(property = "accounts",column = "id",many =@Many(select = "com.itheima.dao.IAccountDao.findByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    @Select("select * from user where id=#{dsfsdfsdf}")
    @ResultMap(value = {"userMap"})
    User findById(Integer uid);
    @Select("select * from user where username like concat('%',concat(#{sdff},'%'))")
    @ResultMap("userMap")
    List<User> findByUsername(String name);


}

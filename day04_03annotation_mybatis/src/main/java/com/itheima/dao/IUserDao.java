package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 在mybatis中，针对CRUD一共有四个注解
 * @Select @Update @Delete @Insert
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user values(null,#{username},#{birthday},#{sex},#{address})")
    void saveUser(User user);
    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void updateUser(User user);
    @Delete("delete from user where id=#{dsfsdfds}")
    void deleteUser(Integer uid);
    @Select("select * from user where id=#{dsfsdfsdf}")
    User findById(Integer uid);
    @Select("select * from user where username like concat('%',concat(#{sdff},'%'))")
    List<User> findByUsername(String name);
    @Select("select count(*) from user")
    Integer findTotal();

}

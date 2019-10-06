package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     */
    public List<User> findAll();

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);


}

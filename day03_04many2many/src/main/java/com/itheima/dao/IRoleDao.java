package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;

public interface IRoleDao {

    /**
     * 查询所有用户
     */
    public List<Role> findAll();

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

}

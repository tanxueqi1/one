package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有用户
     */
    public List<User> findAll();

    /**
     * 保存方法
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据queryvo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合,查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

    /**
     * 查询id>65的user
     * @param id
     * @return
     */
    List<User> findUserGTId(Integer id);

}

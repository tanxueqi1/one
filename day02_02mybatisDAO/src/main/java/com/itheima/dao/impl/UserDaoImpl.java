package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        //根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll");//参数就是能获取配置信息的key: namespace+方法名称
        //释放资源
        session.close();
        return users;
    }

    @Override
    public void saveUser(User user) {
        SqlSession session = factory.openSession();
        session.insert("com.itheima.dao.IUserDao.saveUser",user);
        session.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        SqlSession session = factory.openSession();
        session.update("com.itheima.dao.IUserDao.updateUser",user);
        session.commit();
        session.close();
    }

    @Override
    public void deleteUser(Integer userId) {
        SqlSession session = factory.openSession();
        session.delete("com.itheima.dao.IUserDao.deleteUser",userId);
        session.commit();
        session.close();
    }

    @Override
    public User findById(Integer userId) {
        SqlSession session = factory.openSession();
        User user = session.selectOne("com.itheima.dao.IUserDao.findById", userId);
        session.close();
        return user;
    }

    @Override
    public List<User> findByName(String name) {
        SqlSession session = factory.openSession();
        List<User> list = session.selectList("com.itheima.dao.IUserDao.findByName", name);
        session.close();
        return list;
    }

    @Override
    public int findTotal() {
        SqlSession session = factory.openSession();
        Integer count = session.selectOne("com.itheima.dao.IUserDao.findTotal");
        session.close();
        return count;
    }
}

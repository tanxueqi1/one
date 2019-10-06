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
        //使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        //使用session执行查询所有方法  IUserDao.xml中的namespace+id
        List<User> list = session.selectList("com.itheima.dao.IUserDao.findAll");
        session.close();
        return list;
    }
}

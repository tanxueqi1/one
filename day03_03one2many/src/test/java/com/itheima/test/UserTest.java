package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao mapper;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mapper = session.getMapper(IUserDao.class);
    }
    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception{
        //提交事务
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception {

        List<User> users = mapper.findAll();
        for (User user: users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void testfindOne() throws Exception {
        //执行根据Id查询对象
        User user = mapper.findById(56);
        System.out.println(user);
    }

}

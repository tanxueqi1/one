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


public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }
    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception{
        in.close();
    }


    @Test
    public void testSecondLevelCache() throws Exception {
        SqlSession session = factory.openSession();
        IUserDao mapper = session.getMapper(IUserDao.class);
        User user = mapper.findById(41);
        System.out.println(user);
        session.close();//一级缓存消失

        SqlSession session1 = factory.openSession();
        IUserDao mapper1 = session1.getMapper(IUserDao.class);
        User user1 = mapper1.findById(41);
        session1.close();
        System.out.println(user1);
        System.out.println(user == user1);
    }


}

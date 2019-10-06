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
import java.util.List;


public class UserTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao mapper;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
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

    /**
     * 测试一级缓存  sql查询语句只会执行一次
     * @throws Exception
     */
    @Test
    public void testFirstLevelCache() throws Exception {
        User user = mapper.findById(41);
        System.out.println(user);
        User user1 = mapper.findById(41);
        System.out.println(user1);
        System.out.println(user == user1);
    }

    /**
     * 测试一级缓存  sqlsession关闭，一级缓存消失
     * @throws Exception
     */
    @Test
    public void testFirstLevelCache1() throws Exception {
        User user = mapper.findById(41);
        System.out.println(user);
       /* session.close();
        session = factory.openSession();*/
        session.clearCache();//等同于上2句
        mapper = session.getMapper(IUserDao.class);
        User user1 = mapper.findById(41);
        System.out.println(user1);
        System.out.println(user == user1);
    }

    /**
     * 测试缓存的同步
     * @throws Exception
     */
    @Test
    public void testClearCache() throws Exception {
        //根据id查询用户
        User user = mapper.findById(41);
        System.out.println(user);
       //更新用户信息
        user.setUserName("update user clear cache9");
        user.setUserAddress("广东省深圳市宝安区1");
        mapper.updateUser(user);
        User user1 = mapper.findById(41);
        System.out.println(user1);
        System.out.println(user);
        System.out.println(user == user1);
    }



}

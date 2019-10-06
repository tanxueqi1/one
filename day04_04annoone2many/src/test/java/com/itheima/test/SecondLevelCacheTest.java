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
    public void testFindOne() throws Exception {
        SqlSession session = factory.openSession();
        IUserDao mapper = session.getMapper(IUserDao.class);;
        User user = mapper.findById(67);
        System.out.println(user);
        session.close();//释放一级缓存

        SqlSession session1 = factory.openSession();//再次打开session
        IUserDao mapper1 = session1.getMapper(IUserDao.class);
        User user1 = mapper1.findById(67);
        System.out.println(user1);

    }


}

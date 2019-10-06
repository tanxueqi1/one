package com.itheima.test;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
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

public class AccountAnnoTest {

    private InputStream in;
    private SqlSession session;
    private IAccountDao mapper;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mapper = session.getMapper(IAccountDao.class);
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
        List<Account> accounts = mapper.findAll();
        for (Account account: accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }



}

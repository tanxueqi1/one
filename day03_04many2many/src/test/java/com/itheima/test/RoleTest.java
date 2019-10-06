package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
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


public class RoleTest {

    private InputStream in;
    private SqlSession session;
    private IRoleDao mapper;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        mapper = session.getMapper(IRoleDao.class);
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

        List<Role> roles = mapper.findAll();
        for (Role role: roles) {
            System.out.println(role);
            List<User> users = role.getUsers();
            for (User user: users) {
                System.out.println(user.getUserName());
            }
        }
    }


}

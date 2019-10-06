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
import java.util.Date;
import java.util.List;

public class MybatisAnnoTest {

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
            System.out.println(user.getAddress());
        }
    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User();
        user.setUsername("mybatis annotation");
        user.setAddress("北京市昌平区");
        mapper.saveUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(68);
        user.setUsername("mybatis annotation update");
        user.setAddress("北京市海淀区");
        user.setSex("男");
        user.setBirthday(new Date());
        mapper.updateUser(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        mapper.deleteUser(56);
        mapper.deleteUser(57);
        mapper.deleteUser(58);
    }
    @Test
    public void testFindOne() throws Exception {
        User user = mapper.findById(67);
        System.out.println(user);
    }

    @Test
    public void testFindByUsername() throws Exception {
        List<User> users = mapper.findByUsername("mybatis");
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() throws Exception {
        Integer total = mapper.findTotal();
        System.out.println(total);
    }


}

package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
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


public class MybatisTest {

    private InputStream in;
    private IUserDao mapper;

    @Before//用于在测试方法执行之前执行
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //使用工厂对象,创建dao对象
        mapper = new UserDaoImpl(factory);

    }
    @After//用于在测试方法执行之后执行
    public void destroy() throws Exception{
        in.close();
    }

    @Test
    public void testFindAll() throws Exception {

        List<User> users = mapper.findAll();
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUsername("mybatis saveuser");
        user.setAddress("北京市顺义区");
        user.setBirthday(new Date());
        user.setSex("男");
        System.out.println("保存操作之前:"+user);
        //执行保存方法
        mapper.saveUser(user);
        System.out.println("保存操作之后:"+user);

    }


    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(54);
        user.setUsername("11mybatis updateuser");
        user.setAddress("北京市顺义区");
        user.setBirthday(new Date());
        user.setSex("男");
        //执行保存方法
        mapper.updateUser(user);
    }

    @Test
    public void testDelete() throws Exception {
        //执行删除方法
        mapper.deleteUser(65);
    }

    @Test
    public void testfindOne() throws Exception {
        //执行根据Id查询对象
        User user = mapper.findById(64);
        System.out.println(user);
    }

    @Test
    public void testfindByName() throws Exception {
        //执行根据名称模糊查询
        //List<User> users = mapper.findByName("王");
        List<User> users = mapper.findByName("%王%");
        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testfindTotal() throws Exception {
        //执行查询总条数
        int total = mapper.findTotal();
        System.out.println(total);
    }
}

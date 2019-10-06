package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
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


public class MybatisTest {

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
        }
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setUserName("mybatis autocommit");
        user.setUserAddress("北京市顺义区");
        user.setUserBirthday(new Date());
        user.setUserSex("男");
        System.out.println("保存操作之前:"+user);
        //执行保存方法
        mapper.saveUser(user);
        System.out.println("保存操作之后:"+user);

    }


    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setUserId(54);
        user.setUserName("mybatis updateuser");
        user.setUserAddress("北京市顺义区");
        user.setUserBirthday(new Date());
        user.setUserSex("女");
        //执行保存方法
        mapper.updateUser(user);
    }

    @Test
    public void testDelete() throws Exception {
        //执行删除方法
        mapper.deleteUser(53);
    }

    @Test
    public void testfindOne() throws Exception {
        //执行根据Id查询对象
        User user = mapper.findById(56);
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
    public void testfindByVo() throws Exception {
        //执行根据名称模糊查询
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = mapper.findUserByVo(vo);
        for (User u: users) {
            System.out.println(u);
        }
    }

    @Test
    public void testfindTotal() throws Exception {
        //执行查询总条数
        int total = mapper.findTotal();
        System.out.println(total);
    }

    @Test
    public void testfindByCondition() throws Exception {
        User user = new User();
        user.setUserName("老王");
        //user.setUserSex("男");
        List<User> list = mapper.findUserByCondition(user);
        for (User u :list) {
            System.out.println(u);
        }
    }

    @Test
    public void testfindInIds() throws Exception {
        List<Integer> idsss = new ArrayList<Integer>();
        idsss.add(41);
        idsss.add(43);
        idsss.add(46);
        QueryVo vo = new QueryVo();
        vo.setIds(idsss);
        List<User> list = mapper.findUserInIds(vo);
        for (User user: list) {
            System.out.println(user);
        }
    }

    @Test
    public void testfindUserGTId() throws Exception {
        List<User> list = mapper.findUserGTId(42);
        for (User user: list) {
            System.out.println(user);
        }
    }
}

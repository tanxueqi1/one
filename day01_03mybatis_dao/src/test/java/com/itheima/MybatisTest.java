package com.itheima;


import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MybatisTest {
    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //使用工厂生产dao对象
        IUserDao userDao = new UserDaoImpl(factory);
        List<User> list = userDao.findAll();
        for (User user: list) {
            System.out.println(user);
        }
        //释放资源
        is.close();

    }
}

package com.itheima;


import com.itheima.dao.IUserDao;
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
        //使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //使用SqlSession创建Dao接口的代理对象  根据字节码生成代理对象
        IUserDao mapper = session.getMapper(IUserDao.class);
        //使用代理对象执行方法
        List<User> list = mapper.findAll();
        for (User user: list) {
            System.out.println(user);
        }
        //释放资源
        session.close();
        is.close();

    }
}

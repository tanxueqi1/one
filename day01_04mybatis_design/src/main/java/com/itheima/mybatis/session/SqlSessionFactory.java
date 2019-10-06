package com.itheima.mybatis.session;

public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     */
    SqlSession openSession();
}

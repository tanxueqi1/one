package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.session.SqlSession;
import com.itheima.mybatis.session.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.Configuration;

/**
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}

package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.session.SqlSession;
import com.itheima.mybatis.sqlsession.Configuration;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        conn = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {

        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),conn));
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

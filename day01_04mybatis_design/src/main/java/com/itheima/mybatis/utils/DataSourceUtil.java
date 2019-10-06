package com.itheima.mybatis.utils;

import com.itheima.mybatis.sqlsession.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 用于创建数据源的工具类
 */
public class DataSourceUtil {
    /**
     * 用于创建数据源的工具类
     * @param cfg
     * @return
     */
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

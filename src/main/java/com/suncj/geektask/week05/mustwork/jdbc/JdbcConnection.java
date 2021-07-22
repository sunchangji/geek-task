package com.suncj.geektask.week05.mustwork.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Classname JdbcOperate
 * @Description TODO
 * @Date 2021/7/22 6:10 下午
 * @Created by sunchangji
 */
@Component
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConnection {

    @Autowired
    private JdbcProperties jdbcProperties;

    /**
     * 数据库连接类
     */
    public Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName(jdbcProperties.getDriver());
            connection= DriverManager.getConnection(jdbcProperties.getUrl(), jdbcProperties.getUsername(), jdbcProperties.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}

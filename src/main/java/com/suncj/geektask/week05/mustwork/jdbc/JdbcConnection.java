package com.suncj.geektask.week05.mustwork.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Classname JdbcOperate
 * @Description TODO
 * @Date 2021/7/22 6:10 下午
 * @Created by sunchangji
 */
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConnection {

    @Autowired
    private JdbcProperties jdbcProperties;
    @Autowired
    private HikariDataSource hikariDataSource;

    /**
     * 不使用连接池获取数据库连接
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

    /**
     * 连接池获取
     * @return
     * @throws SQLException
     */
    public Connection getHikariConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

}

package com.suncj.geektask.week05.mustwork.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hikari连接池方式
 *
 * @Classname HikariJdbcOperate
 * @Description TODO
 * @Date 2021/7/22 7:56 下午
 * @Created by sunchangji
 */
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class HikariCPConfig {
    @Autowired
    private JdbcProperties jdbcProperties;

    @Bean
    public HikariDataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(jdbcProperties.getDriver());
        config.setJdbcUrl(jdbcProperties.getUrl());
        config.setUsername(jdbcProperties.getUsername());
        config.setPassword(jdbcProperties.getPassword());
        //从池中借出的连接是否默认自动提交事务,默认 true
        config.setAutoCommit(true);
        //当我从池中借出连接时，愿意等待多长时间。如果超时，将抛出 SQLException，默认30000
        config.setConnectionTimeout(30000);
        //一个连接在池里闲置多久时会被抛弃,当 minimumIdle < maximumPoolSize 才生效
        //默认值 600000 ms，最小值为 10000 ms，0表示禁用该功能
        config.setIdleTimeout(600000);
        //当一个连接存活了足够久，HikariCP 将会在它空闲时把它抛弃
        config.setMaxLifetime(1800000);
        //池中至少要有多少空闲连接。
        config.setMinimumIdle(3);
        //池中最多容纳多少连接（包括空闲的和在用的）
        config.setMaximumPoolSize(10);
        //连接池名称,默认自动生成
        config.setPoolName("Hikari");
        return new HikariDataSource(config);
    }

}

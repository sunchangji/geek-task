package com.suncj.geektask.week05.mustwork.jdbc;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据库配置类
 * @Classname DbPropertiesBean
 * @Description TODO
 * @Date 2021/7/22 6:01 下午
 * @Created by sunchangji
 */
@Data
@ConfigurationProperties(prefix = "db.jdbc")
public class JdbcProperties {

    private String driver;

    private String url;

    private String username;

    private String password;
}

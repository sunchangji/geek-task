package com.suncj.geektask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * @Classname GeekTaskApplication
 * @Description TODO
 * @Date 2021/7/22 4:15 下午
 * @Created by sunchangji
 */
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@MapperScan(basePackages = "com.suncj.geektask.week07.mapper")

public class GeekTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekTaskApplication.class, args);
    }
}

package com.suncj.geektask.week05;

import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Classname spring自动根据xml注入bean
 * @Description TODO
 * @Date 2021/7/22 4:59 下午
 * @Created by sunchangji
 */
@Configuration
@ImportResource(locations = {"classpath*:applicationContext.xml"})
public class SpringAutoXmlSpringBean implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet(){
        //获取spring容器中xml配置的学生bean
        val student01 = applicationContext.getBean("student01");
        System.out.println("student01:"+student01);
        val student02 = applicationContext.getBean("student02");
        System.out.println("student02:"+student02);
        val student03 = applicationContext.getBean("student03");
        System.out.println("student03:"+student03);

        //获取spring容器中xml配置的班级bean
        val klass01 = applicationContext.getBean("klass01");
        System.out.println("klass01:"+klass01);
        val klass02 = applicationContext.getBean("klass02");
        System.out.println("klass02:"+klass02);

        //获取spring容器中xml配置的学校bean
        val school = applicationContext.getBean("school");
        System.out.println("school:"+school);
    }
}

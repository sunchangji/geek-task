package com.suncj.geektask.week05.mustwork;

import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname 手动使用XML的方式自己手动装配bean
 * @Description TODO
 * @Date 2021/7/22 4:23 下午
 * @Created by sunchangji
 */
public class ManualXmlSpringBean {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //获取spring容器中xml配置的学生bean
        val student01 = context.getBean("student01");
        System.out.println("student01:"+student01);
        val student02 = context.getBean("student02");
        System.out.println("student02:"+student02);
        val student03 = context.getBean("student03");
        System.out.println("student03:"+student03);

        //获取spring容器中xml配置的班级bean
        val klass01 = context.getBean("klass01");
        System.out.println("klass01:"+klass01);
        val klass02 = context.getBean("klass02");
        System.out.println("klass02:"+klass02);

        //获取spring容器中xml配置的学校bean
        val school = context.getBean("school");
        System.out.println("school:"+school);
    }
}

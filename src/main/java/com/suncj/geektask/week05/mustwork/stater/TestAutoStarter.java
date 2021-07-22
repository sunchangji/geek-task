package com.suncj.geektask.week05.mustwork.stater;

import com.suncj.geektask.week05.beans.Klass;
import com.suncj.geektask.week05.beans.School;
import com.suncj.geektask.week05.beans.Student;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自动注入类在com.suncj.stater.AutoDemoConfig
 * @Classname TestAutoStarter
 * @Description TODO
 * @Date 2021/7/22 8:51 下午
 * @Created by sunchangji
 */
@Component
public class TestAutoStarter implements InitializingBean {
    @Resource(name = "studentStarter")
    private Student studentStarter;
    @Resource(name = "klassStarter")
    private Klass klassStarter;
    @Resource(name = "schoolStarter")
    private School schoolStarter;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("studentStarter:" + studentStarter);
        System.out.println("klassStarter:" + klassStarter);
        System.out.println("schoolStarter:" + schoolStarter);
    }
}

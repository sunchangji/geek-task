package com.suncj.geektask.week05.mustwork.injectbean;

import com.suncj.geektask.week05.beans.Klass;
import com.suncj.geektask.week05.beans.School;
import com.suncj.geektask.week05.beans.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Classname 测试注解方式注入的bean获取
 * @Description TODO
 * @Date 2021/7/22 5:35 下午
 * @Created by sunchangji
 */
@Component
public class TestSpringAnnotationBean {
    @Resource(name = "studentA")
    private Student studentA;
    @Resource(name = "studentB")
    private Student studentB;
    @Resource(name = "studentC")
    private Student studentC;
    @Resource(name = "klassA")
    private Klass klassA;
    @Resource(name = "klassB")
    private Klass klassB;
    @Resource(name = "schoolA")
    private School schoolA;

    @PostConstruct
    public void init(){
        System.out.println("studentA:"+studentA);
        System.out.println("studentB:"+studentB);
        System.out.println("studentC:"+studentC);
        System.out.println("klassA:"+klassA);
        System.out.println("klassB:"+klassB);
        System.out.println("schoolA:"+schoolA);
    }
}

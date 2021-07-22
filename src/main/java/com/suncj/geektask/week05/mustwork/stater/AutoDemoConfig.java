package com.suncj.geektask.week05.mustwork.stater;

import com.google.common.collect.Lists;
import com.suncj.geektask.week05.beans.Klass;
import com.suncj.geektask.week05.beans.School;
import com.suncj.geektask.week05.beans.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这里本应该单独起一个项目写starter,然后使用的项目通过引入包,然后配置就可以
 * 为了方便写在了一个项目里面
 * @Classname AutoDemoConfig
 * @Description TODO
 * @Date 2021/7/22 8:30 下午
 * @Created by sunchangji
 */
@Configuration
@ConditionalOnProperty(prefix = "demo.bean",name = "isopen",havingValue = "true")
public class AutoDemoConfig {

    @Bean("studentStarter")
    @ConditionalOnClass
    public Student studentStarter(){
        Student st = new Student();
        st.setId(10L);
        st.setName("studentStarter");
        return st;
    }

    @Bean("klassStarter")
    @ConditionalOnClass
    public Klass klassStarter(@Qualifier("studentStarter")Student studentStarter){
        Klass klass = new Klass();
        klass.setStudents(Lists.newArrayList(studentStarter));
        return klass;
    }

    @Bean("schoolStarter")
    @ConditionalOnClass
    public School schoolStarter(@Qualifier("klassStarter")Klass klassStarter){
        School school = new School();
        school.setKlasses(Lists.newArrayList(klassStarter));
        return school;
    }


}

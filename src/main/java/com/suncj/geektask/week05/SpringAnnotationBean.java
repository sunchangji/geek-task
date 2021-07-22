package com.suncj.geektask.week05;

import com.google.common.collect.Lists;
import com.suncj.geektask.week05.beans.Klass;
import com.suncj.geektask.week05.beans.School;
import com.suncj.geektask.week05.beans.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname SpringAnnotationBean
 * @Description TODO
 * @Date 2021/7/22 5:12 下午
 * @Created by sunchangji
 */
@Configuration
public class SpringAnnotationBean {

    @Bean
    public Student studentA(){
        Student studentA = new Student();
        studentA.setId(6L);
        studentA.setName("studentA");
        return studentA;
    }

    @Bean(name = "studentB")
    public Student studentB(){
        Student studentB = new Student();
        studentB.setId(7L);
        studentB.setName("studentB");
        return studentB;
    }

    @Bean
    public Student studentC(){
        Student studentC = new Student();
        studentC.setId(8L);
        studentC.setName("studentC");
        return studentC;
    }

    @Bean
    public Klass klassA(@Qualifier("studentA")Student studentA,@Qualifier("studentB")Student studentB){
        Klass klassA = new Klass();
        klassA.setStudents(Lists.newArrayList(studentA,studentB));
        return klassA;
    }

    @Bean
    public Klass klassB(@Qualifier("studentC")Student studentC){
        Klass klassb = new Klass();
        klassb.setStudents(Lists.newArrayList(studentC));
        return klassb;
    }

    @Bean
    public School schoolA(@Qualifier("klassA")Klass klassA,@Qualifier("klassB")Klass klassB){
        School schoolA = new School();
        schoolA.setKlasses(Lists.newArrayList(klassA,klassB));
        return schoolA;
    }

}

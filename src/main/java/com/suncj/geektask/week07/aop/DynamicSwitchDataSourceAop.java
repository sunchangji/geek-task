package com.suncj.geektask.week07.aop;

import com.suncj.geektask.week07.DynamicDataSource;
import com.suncj.geektask.week07.annotation.DynamicSwitchDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
@Order(1)
public class DynamicSwitchDataSourceAop {

    //@within在类上设置
    //@annotation在方法上进行设置
    @Pointcut("@within(com.suncj.geektask.week07.annotation.DynamicSwitchDataSource)||@annotation(com.suncj.geektask.week07.annotation.DynamicSwitchDataSource)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DynamicSwitchDataSource annotationClass = method.getAnnotation(DynamicSwitchDataSource.class);//获取方法上的注解
        if (annotationClass == null) {
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSource.class);//获取类上面的注解
            if (annotationClass == null) return;
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotationClass.dataSource();
        if (!"".equals(dataSourceKey)) {
            //给当前的执行SQL的操作设置特殊的数据源的信息
            DynamicDataSource.setDataSource(dataSourceKey);
        }
        System.out.println("AOP动态切换数据源，className"+ joinPoint.getTarget().getClass().getName() + "methodName:" + method.getName() + ";dataSourceKey:" + dataSourceKey);

    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DynamicDataSource.clearDataSource();
    }

}

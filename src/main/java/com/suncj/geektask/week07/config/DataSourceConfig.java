package com.suncj.geektask.week07.config;

import com.suncj.geektask.week07.DynamicDataSource;
import com.suncj.geektask.week07.constant.Week07Constant;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class DataSourceConfig {
    @Autowired
    private HikariDBProperties hikariDBProperties;
    @Autowired
    private MybatisProperties mybatisProperties;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = new HashMap<>();

        targetDataSources.put(Week07Constant.MASTER, hikariDBProperties.getMaster());
        targetDataSources.put(Week07Constant.SLAVE, hikariDBProperties.getSlave());
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        return new DynamicDataSource(hikariDBProperties.getMaster(), targetDataSources);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setVfs(SpringBootVFS.class);
        if (mybatisProperties.resolveMapperLocations() != null) {
            factory.setMapperLocations(mybatisProperties.resolveMapperLocations());
        }
        if(mybatisProperties.getTypeAliasesPackage() != null && !mybatisProperties.getTypeAliasesPackage().isEmpty()){
            factory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        }

        if(mybatisProperties.getTypeHandlersPackage() != null && !mybatisProperties.getTypeHandlersPackage().isEmpty()){
            factory.setTypeAliasesPackage(mybatisProperties.getTypeHandlersPackage());
        }

        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        ExecutorType executorType = mybatisProperties.getExecutorType();
        if(null != executorType){
            return new SqlSessionTemplate(sqlSessionFactory(),executorType);
        }else {
            return new SqlSessionTemplate(sqlSessionFactory());
        }
    }
}

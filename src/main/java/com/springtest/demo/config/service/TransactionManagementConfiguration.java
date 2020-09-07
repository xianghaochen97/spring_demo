package com.springtest.demo.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * 事务管理
 *
 * @author xianghaochen
 * @date 2020/8/26 0:43
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {

    /**
     * 需要有DateSource才能进行事务管理，就是在DateSourceConfiguration中定义的Bean
     */
    @Autowired
    private DataSource dataSource;

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    // 配置注解
    // 开启事务管理注解，需要实现TransactionManagementConfigurer这个接口

    // 创建一个DataSourceTransactionManager的Bean，注入DataSource的成员变量
}

package com.springtest.demo.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author xianghaochen
 * @date 2020/8/8 17:10
 */
@Configuration
public class SessionFactoryConfiguraion {

    /**
     * mybatis-config.xml配置文件的路径
     */
    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;

    /**
     * mybatis mapper文件所在路径
     */
    @Value("${mapper_path}")
    private String mapperPath;

    /**
     * 实体类所在的package
     */
    @Value("${entity_package}")
    private String entityPackage;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // mybatis配置文件扫描路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));

        // mapper文件扫描路径
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));

        // 设置dataSource
        sqlSessionFactoryBean.setDataSource(dataSource);

        // 映射实体类  指定实体类扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);

        return sqlSessionFactoryBean;
    }
}

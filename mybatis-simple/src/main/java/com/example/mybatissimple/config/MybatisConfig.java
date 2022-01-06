package com.example.mybatissimple.config;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-05
 */
@Configuration
public class MybatisConfig {

    @Bean(name = "dataSource")
    public DataSource masterDataSource() {
        return new UnpooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/test", "root",
                "Abcdef@123456");
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

}

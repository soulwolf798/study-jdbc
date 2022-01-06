package com.example.mybatisstudy.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.example.mybatisstudy.constants.DataSourceConstants;
import com.example.mybatisstudy.datasouce.DynamicDataSource;
import com.smalljdbc.smalljdbc.datasource.V1.SmallDataSourceV1;


@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DynamicDataSourceConfig {

    @Bean(DataSourceConstants.DS_KEY_MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new SmallDataSourceV1();
    }

    @Bean(DataSourceConstants.DS_KEY_SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return new SmallDataSourceV1();
    }

    @Bean
    @Primary
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceConstants.DS_KEY_MASTER, masterDataSource());
        dataSourceMap.put(DataSourceConstants.DS_KEY_SLAVE, slaveDataSource());
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());

        return dynamicDataSource;
    }


}

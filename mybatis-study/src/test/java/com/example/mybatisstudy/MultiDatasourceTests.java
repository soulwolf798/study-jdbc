package com.example.mybatisstudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mybatisstudy.constants.DataSourceConstants;
import com.example.mybatisstudy.datasouce.DynamicDataSourceContextHolder;
import com.example.mybatisstudy.mapper.UserMapper;
import com.example.mybatisstudy.model.User;


@SpringBootTest
 class MultiDatasourceTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        /**
        * 没有添加注解的
        * */
        DynamicDataSourceContextHolder.setContextKey(DataSourceConstants.DS_KEY_MASTER);

        User user = userMapper.selectOne(1);

        System.out.print(user);
    }

}

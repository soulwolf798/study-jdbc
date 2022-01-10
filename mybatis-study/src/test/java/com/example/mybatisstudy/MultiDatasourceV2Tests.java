package com.example.mybatisstudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mybatisstudy.mapper.UserMapper;
import com.example.mybatisstudy.model.User;


@SpringBootTest
 class MultiDatasourceV2Tests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

        /**
        * 添加注解的
        * */
        User user = userMapper.selectOne(3);

        System.out.print(user);
    }

}

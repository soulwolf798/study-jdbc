package com.example.mybatissimple;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mybatissimple.mapper.UserMapper;
import com.example.mybatissimple.model.User;

@SpringBootTest
class MybaitsDemoV2Tests {


    @Autowired
    private UserMapper userMapper;

    /**
     * 借助springboot做了两件事
     * （1）装载数据源
     * （2）扫描并装配mapper接口和mapper.xml
     */
    @Test
    void contextLoads() {
        User user = userMapper.selectOne(1);

        System.out.print(user.getName());
    }


}

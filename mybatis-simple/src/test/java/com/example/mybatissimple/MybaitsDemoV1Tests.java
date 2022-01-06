package com.example.mybatissimple;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.mybatissimple.mapper.UserMapper;
import com.example.mybatissimple.model.User;


class MybaitsDemoV1Tests {

    public static void main(String[] args) {
        try {
            /**
             * 这是不借助spring任何能力的最简版mybatis执行代码
             * */

            // 获取xml配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

            // 解析xml配置文件，初始化mybatis内部各模块的值
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 创建sqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();

            // 获取接口的代理类
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = userMapper.selectOne(1);

            System.out.print(user.getName()+"\n");
        } catch (Exception e) {
            System.out.print(e);
        }

    }
}

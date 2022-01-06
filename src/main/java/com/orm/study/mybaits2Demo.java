package com.orm.study;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.orm.study.dao.UserMapper;
import com.orm.study.model.User;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-26
 */
public class mybaits2Demo {

    public static void main(String[] args) {
        try {

            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession sqlSession = sqlSessionFactory.openSession();

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            User user = userMapper.selectOne(1);

            System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

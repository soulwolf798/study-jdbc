package com.orm.study.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.orm.study.model.User;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-27
 */
@Repository
public interface UserMapper {

//    @Select("select * from user where id = #{id}")
    User selectOne(@Param("id") Integer id);
}

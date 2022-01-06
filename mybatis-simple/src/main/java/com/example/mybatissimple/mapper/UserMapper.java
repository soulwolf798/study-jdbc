package com.example.mybatissimple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mybatissimple.model.User;


/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-27
 */
@Mapper
public interface UserMapper {
     User selectOne(@Param("id") Integer id);
}

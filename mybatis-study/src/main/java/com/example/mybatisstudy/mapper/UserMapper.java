package com.example.mybatisstudy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mybatisstudy.model.User;


/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-27
 */
@Mapper
//@DS(DataSourceConstants.DS_KEY_SLAVE)
public interface UserMapper {

    User selectOne(@Param("id") Integer id);
}

package com.lc.mapper;

import com.lc.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //根据username 查用户
    Users querryByName(String name);

    //插入盐值
    int insertIntoSalt(String userName,String passWordSalt);

}

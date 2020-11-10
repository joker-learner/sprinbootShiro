package com.lc.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface RoleMapper {
    //根据username 查用户的角色名
    Set<String> querryForRoleByUsername(String username);

}

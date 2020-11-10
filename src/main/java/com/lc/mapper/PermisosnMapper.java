package com.lc.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface PermisosnMapper {

    //根据用户名查权限
    Set<String> querryPerMissionByName(String username);


}

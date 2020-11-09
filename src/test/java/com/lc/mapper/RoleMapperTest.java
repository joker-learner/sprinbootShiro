package com.lc.mapper;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoleMapperTest {

    @Resource
    private RoleMapper roleMapper;

    @Test
    void querryForRoleByUsername() throws Exception {
        Set<String> permisson = roleMapper.querryForRoleByUsername("李四");
    }
}
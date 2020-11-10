//package com.lc.mapper;
//
//import com.lc.SpringbootShiroApplication;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringbootShiroApplication.class)
//class RoleMapperTest {
//
//    @Resource
//    private RoleMapper roleMapper;
//
//    @Test
//    void querryForRoleByUsername() throws Exception {
//
//        Set<String> roleName = roleMapper.querryForRoleByUsername("李四");
//
//        for (String s : roleName) {
//            System.out.println(s);
//        }
//    }
//}
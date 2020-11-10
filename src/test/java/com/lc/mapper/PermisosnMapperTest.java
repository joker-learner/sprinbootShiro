package com.lc.mapper;

import com.lc.SpringbootShiroApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootShiroApplication.class)
class PermisosnMapperTest {

    @Resource
    private PermisosnMapper permisosnMapper;

    @Test
    void querryPerMissionByName() {
        Set<String> permissionName = permisosnMapper.querryPerMissionByName("刘聪");
        for (String s : permissionName
             ) {
            System.out.println(s);
        }
    }
}
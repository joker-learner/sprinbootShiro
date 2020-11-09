package com.lc.mapper;

import java.util.Set;

public interface RoleMapper {

    Set<String> querryForRoleByUsername(String username) throws Exception;

}

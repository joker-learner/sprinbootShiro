package com.lc.mapper;

import com.lc.pojo.Users;

public interface UserMapper {

  public Users querryByName(String name) throws Exception;

}

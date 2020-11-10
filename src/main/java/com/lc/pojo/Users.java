package com.lc.pojo;

import lombok.Data;

@Data
public class Users {

    private Long id;

    private String userName;

    private String passWord;

    private String passSalt;

}

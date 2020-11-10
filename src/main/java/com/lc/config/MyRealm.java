package com.lc.config;

import com.lc.mapper.PermisosnMapper;
import com.lc.mapper.RoleMapper;
import com.lc.mapper.UserMapper;
import com.lc.pojo.Users;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 *
 * 自定义realm（比对库）  需要自己去通过用户名查数据库，找到角色名  ， 找到权限名
 *
 */

public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermisosnMapper permisosnMapper;

    /**
     * @param principalCollection
     * @return 获取授权数据
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从principalCollection 拿到用户名
        String username = (String) principalCollection.iterator().next();
        Set<String> rolesNmae = roleMapper.querryForRoleByUsername(username);
        //根据username查找该用户的角色名  ，  根据角色查找用户权限名
        Set<String> pemissName = permisosnMapper.querryPerMissionByName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(rolesNmae);
        info.setStringPermissions(pemissName);

        return info;
    }

    /**
     * @param authenticationToken
     * @return
     * @throws AuthenticationException 获取认证信息
     */

    @Override
    //获取用户信息存在  AuthenticationToken authenticationToken
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        Users user = userMapper.querryByName(username);
        AuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassWord(), getName());
        return info;
    }

    @Override
    public String getName() {
        return "myreal";
    }
}

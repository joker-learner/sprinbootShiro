package com.lc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class ShireConfi {

    @Bean //加密规则 , 返给realm
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);  //一次hash
        return matcher;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
//    @Bean         //通过文件shiro.ini  指定用户权限
//    public IniRealm getrealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
//        IniRealm realm = new IniRealm("classpath:shiro.ini");
//        return realm;
//    }
    @Bean   //spring整合比对库，通过shiro规定的表名 ，格式意思就是它自己去查数据库找 找到角色名，权限名
    public JdbcRealm jdbcRealm(DataSource dataSource , HashedCredentialsMatcher matcher){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setCredentialsMatcher(matcher);
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        return jdbcRealm;
    }

    @Bean
//    public DefaultWebSecurityManager defaultSecurityManager(IniRealm realm) {
    public DefaultWebSecurityManager defaultSecurityManager(JdbcRealm jdbcRealm) {
        DefaultWebSecurityManager   defaultSecurityManager = new DefaultWebSecurityManager  ();
        defaultSecurityManager.setRealm(jdbcRealm);
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(defaultSecurityManager);
        Map<String, String> filterMap = new HashMap<>();  //拦截规则
        filterMap.put("/", "anon");  //anon  匿名可以访问
        filterMap.put("/index", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/static/**" , "anon");
        filterMap.put("/user/**","anon");
        filterMap.put("/torigister","anon");


//        filterMap.put("/layim" , "anon");
        filterMap.put("/**" , "authc");  //登录用户可以访问
        filter.setFilterChainDefinitionMap(filterMap);
        filter.setLoginUrl("login.html");

        return filter;
    }
}

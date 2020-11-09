package com.lc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
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

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
//    @Bean
//    public IniRealm getrealm() {
//        IniRealm realm = new IniRealm("classpath:shiro.ini");
//        return realm;
//    }
    @Bean   //spring整合Shiro
    public JdbcRealm jdbcRealm(DataSource dataSource){
        JdbcRealm jdbcRealm = new JdbcRealm();
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
        filterMap.put("/index.html", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/static/**" , "anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/**" , "authc");  //登录用户可以访问
        filter.setFilterChainDefinitionMap(filterMap);
        filter.setLoginUrl("/login.html");

        return filter;
    }
}

package com.lc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.AuthenticatingSecurityManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShireConfi {

    @Bean  //利用 缓存解决前段界面，授权时，每次从数据库查权限字段问题
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:/ehcache.xml");
        return ehCacheManager;
    }

    @Bean //加密规则 , 返给realm
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);  //一次hash
        return matcher;
    }

    @Bean   //开启shiro注解  @RequirePermission
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor =
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    @Bean   //让shiro的注解得到加载
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator
                = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    @Bean   //spring整合比对库，通过shiro规定的表名，格式意思就是它自己去查数据库找 找到角色名，权限名
    public JdbcRealm jdbcRealm(DataSource dataSource, HashedCredentialsMatcher matcher) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setCredentialsMatcher(matcher);
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        return jdbcRealm;
    }

    @Bean
    public DefaultWebSecurityManager defaultSecurityManager(JdbcRealm jdbcRealm , EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setCacheManager(ehCacheManager);
        defaultSecurityManager.setRealm(jdbcRealm);
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(defaultSecurityManager);
        Map<String, String> filterMap = new HashMap<>();  //拦截规则
        //map里添加  键：是访问路径  ， 值：是权限值  预定值 anon 、 authc
        filterMap.put("/", "anon");  //anon  匿名可以访问
        filterMap.put("/index", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/user/**", "anon");
        filterMap.put("/torigister", "anon");
        filterMap.put("/**", "authc");  //登录用户可以访问

        //点击退出后 自动跳的界面
        filterMap.put("/logout", "logout");

        //拥有sys:c:add 权限才能访问c_add.html 界面
        filterMap.put("/cangku/list", "perms[sys:c:save]");

        //登录界面
        filter.setLoginUrl("login.html");

        //如果没有权限则调到
        filter.setUnauthorizedUrl("/tolessparmis");
        filter.setFilterChainDefinitionMap(filterMap);
        filter.setLoginUrl("login.html");

        return filter;
    }
}

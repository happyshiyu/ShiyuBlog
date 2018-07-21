package cn.shiyu.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.shiyu.shiro.realm.JWTRealm;

import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfiguration {
	/**
	 * 注入 securityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(jwtRealm());
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 设置无权限时跳转的 url;
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");

		// 设置拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		
		// 用户，需要角色权限 “user”
		filterChainDefinitionMap.put("/user/**", "roles[user]");
		// 管理员，需要角色权限 “admin”
		filterChainDefinitionMap.put("/admin/**", "roles[admin]");
		// 开放登陆注册接口
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/register", "anon");
		filterChainDefinitionMap.put("/online", "anon");
		// 登出接口
		filterChainDefinitionMap.put("/logout", "logout");
		// 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainDefinitionMap.put("/**", "jwt");
/*		filterChainDefinitionMap.put("/**", "authc");*/
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 自定义身份认证 realm;
	 * <p>
	 * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm， 否则会影响 CustomRealm类 中其他类的依赖注入
	 */
	@Bean
	public JWTRealm jwtRealm() {
		JWTRealm realm = new JWTRealm();
		return realm;
	}
/*
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		// 采用MD5方式加密
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		// 设置加密次数
		hashedCredentialsMatcher.setHashIterations(1024);
		return hashedCredentialsMatcher;
	}*/
}
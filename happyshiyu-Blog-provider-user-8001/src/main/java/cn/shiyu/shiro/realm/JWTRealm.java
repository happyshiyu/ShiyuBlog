package cn.shiyu.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.shiyu.entity.User;
import cn.shiyu.mapper.UserMapper;
import cn.shiyu.shiro.utils.JWTUtil;

public class JWTRealm extends AuthorizingRealm {
	@Autowired
	UserMapper usermapper;
	
	/**
	 * 获取身份验证信息 Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
	 *
	 * @param authenticationToken
	 *            用户身份信息 token
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
//		String token = (String) authenticationToken.getCredentials();
		UsernamePasswordToken u = (UsernamePasswordToken) authenticationToken;
		String token = u.getUsername();
		String username = JWTUtil.getUsername(token);
		// 从数据库获取对应用户名的用户
		User user = usermapper.findByUsername(username);
		if (user == null) {
			throw new AccountException("不存在此用户");
		}
        return new SimpleAuthenticationInfo(token, token, "my_realm");
	}

	/**
	 * 获取授权信息
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("————权限认证————");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获得该用户角色
		String role = "admin";
		Set<String> set = new HashSet<>();
		// 需要将 role 封装到 Set 作为 info.setRoles() 的参数
		set.add(role);
		// 设置该用户拥有的角色
		info.setRoles(set);
		return info;
	}
}

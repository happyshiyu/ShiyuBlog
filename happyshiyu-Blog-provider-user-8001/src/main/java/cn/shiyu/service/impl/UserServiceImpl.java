package cn.shiyu.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shiyu.entity.User;
import cn.shiyu.mapper.UserMapper;
import cn.shiyu.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userMapper.findById(id);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userMapper.findAll();
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String MD5password = new SimpleHash("MD5", password, username, 1024).toHex();
		return userMapper.findByUsernameAndPassword(username, MD5password);
	}

	@Override
	public void addUser(User user) {
		/*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
		String username = user.getUsername();
		String password = user.getPassword();
        String MD5password = new SimpleHash("MD5", password, username, 1024).toHex();
        user.setPassword(MD5password);
		userMapper.addUser(user);
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}

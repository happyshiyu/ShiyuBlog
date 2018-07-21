package cn.shiyu.service;

import java.util.List;

import cn.shiyu.entity.User;

public interface UserService {
	public User findById(int id);
	
	public List<User> findAll();
	
	public User findByUsernameAndPassword(String username, String password);
	
	public void addUser(User user);
	
	public User findByUsername(String username);
}

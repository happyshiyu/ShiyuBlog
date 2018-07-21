package cn.shiyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.shiyu.entity.User;

@Mapper
public interface UserMapper {
	public User findById(int id);
	
	public List<User> findAll();
	
	public User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
	
	public User findByUsername(@Param("username") String username);
	
	public void addUser(User user);

}

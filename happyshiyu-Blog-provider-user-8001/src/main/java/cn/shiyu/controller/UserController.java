package cn.shiyu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.shiyu.entity.User;
import cn.shiyu.service.UserService;
import cn.shiyu.shiro.utils.JWTUtil;
import cn.shiyu.util.Result;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login")
	public Result login(@RequestBody User u) {
		String username = u.getUsername();
		String password = u.getPassword();
		User user = userService.findByUsernameAndPassword(username, password);
		if(user == null) {
			return new Result(400,"用户名或密码不正确",null);
		} else {
			return new Result(200,"登录成功",JWTUtil.sign(username, password));
		}
	}
	
	@RequestMapping(value="/online",method=RequestMethod.GET)
	public Result online(@RequestParam("token") String token) {
		if(token == null || "".equals(token)) 
			return new Result(400,"token错误",null);
		String username = JWTUtil.getUsername(token);
		User user = userService.findByUsername(username);
		if(user == null) {
			return new Result(400,"token错误",null);
		} else {
			return new Result(200,"用户在线",user);
		}
	}
	
	
	@RequestMapping(value="/hello")
	public String Hello() {
		return "hello";
	}
	
	@RequestMapping(value="/register")
	public Result register(@RequestBody User user) {
		user.setId((int) (System.currentTimeMillis()/10000));
		user.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		userService.addUser(user);
		try {
			
		} catch (Exception e) {
			return new Result(400,"失败",null);
		}
		
		return new Result(200,"注册成功",null);	
	}
	
}

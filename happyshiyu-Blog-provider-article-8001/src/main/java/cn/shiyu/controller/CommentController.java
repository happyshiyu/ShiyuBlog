package cn.shiyu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.shiyu.service.LikeService;
import cn.shiyu.util.Result;

/**
 * @author Happyshiyu
 * 评论和回复的Controller
 */
@RestController
@CrossOrigin
@RequestMapping(value="/like")
public class CommentController {
	
	@Autowired
	LikeService likeService;
	
	/**
	 * 点赞
	 * @param map
	 * @return
	 */
	@RequestMapping("/add")
	public Result like(Map<String,Integer> map) {
		Integer uid = map.get("uid");
		Integer aid = map.get("aid");
		boolean flag = false;
		try {
			//点赞后再次访问则取消点赞，返回true代表成功点赞，返回false代表取消点赞
			flag = likeService.like(uid, aid);
		} catch (Exception e) {
			return new Result(400,"参数列表错误",null);
		}
		return flag ? new Result(200,"点赞成功",null) : new Result(200,"取消点赞成功",null);
	}
	
}

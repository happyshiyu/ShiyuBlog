package cn.shiyu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import cn.shiyu.entity.Article;
import cn.shiyu.service.ArticleService;
import cn.shiyu.service.CountService;
import cn.shiyu.service.LikeService;
import cn.shiyu.util.POJOUtil;
import cn.shiyu.util.Result;

/**
 * @author Happyshiyu
 * 文章相关操作的Controller
 */
@RestController
@CrossOrigin
@RequestMapping(value="/article")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	@Autowired
	CountService countService;
	@Autowired
	LikeService likeService;
	
	/**
	 * 查看文章内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/content/{id}",method=RequestMethod.GET)
	public Result get(@PathVariable("id") int id, HttpServletRequest request) {
		Article article = articleService.findById(id);
		//增加访问量
		countService.increase(id,request);
		//获取文章访问量
		long readNumber = countService.getReadNumber(id);
		Map<String, Object> map = POJOUtil.getMap(article);
		map.put("readNumber", readNumber);
		//获取文章点赞数
		int likeNumber = likeService.getLikeCountByAid(id);
		map.put("likeNumber", likeNumber);
		return new Result(200,"查询成功",map);
	}
	
	/**
	 * 根据作者id分页查询
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	public Result getByAuthorId(@RequestBody Map<String,Integer> map) {
		List<Article> list = null;
		try {
			Integer authorId = map.get("authorId");
			Integer pageNum = map.get("pageNum") == null ? 1 : map.get("pageNum");
			Integer pageSize = map.get("pageSize") == null ? 10 : map.get("pageSize");
			list = articleService.findByAuthorId(authorId, pageNum, pageSize);
		} catch (Exception e) {
			return new Result(400,"查询失败，请检查数据格式",null);
		}
		return new Result(200,"查询成功",list);
	}
	
	/**
	 * 添加文章
	 * @param article
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result add(@RequestBody Article article) {
		int id = (int) (System.currentTimeMillis()/10000);
		article.setId(id);
		article.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			articleService.addArticle(article);
		} catch (Exception e) {
			return new Result(400,"新增失败",null);
		}
		return new Result(200,"新增成功",id);
	}
	
	/**
	 * 编辑文章
	 * @param article
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Result update(@RequestBody Article article) {
		int id = article.getId();
		try {
			articleService.updateArticle(article);
		} catch (Exception e) {
			return new Result(400,"修改失败",null);
		}
		return new Result(200,"修改成功",id);
	}
	
	
}

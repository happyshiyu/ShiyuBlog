package cn.shiyu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import cn.shiyu.service.CountService;
import cn.shiyu.util.IPUtil;
@Service
public class CountServiceImpl implements CountService{
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void increase(int id , HttpServletRequest request) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//获取ip地址
		String ip = IPUtil.getIpAddress(request);
		String key = "article:read:" + id;
		String field = ip + ":" + date;
		//阅读量加1
		stringRedisTemplate.opsForHash().increment(key, field, 1);
	}

	@Override
	public long getReadNumber(int id) {
		String key = "article:read:" + id;
		long size = stringRedisTemplate.opsForHash().size(key);
		return size;
	}

}

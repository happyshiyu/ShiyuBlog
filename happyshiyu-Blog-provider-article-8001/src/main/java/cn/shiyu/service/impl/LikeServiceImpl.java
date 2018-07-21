package cn.shiyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shiyu.entity.Like;
import cn.shiyu.mapper.LikeMapper;
import cn.shiyu.service.LikeService;
@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	private LikeMapper mapper;
	
	/* 
	 * @see cn.shiyu.service.LikeService#like(int, int)
	 * 点赞后再次访问则取消点赞，返回true代表成功点赞，返回false代表取消点赞
	 */
	@Override
	public boolean like(int uid, int aid) {
		Like like = mapper.findByUidAndAid(uid, aid);
		if(like == null) {
			mapper.addLike(uid, aid);
			return true;
		} else {
			mapper.deleteByUidAndAid(uid, aid);
			return false;
		}
	}

	@Override
	public boolean isLike(int uid, int aid) {
		Like like = mapper.findByUidAndAid(uid, aid);
		if(like == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	@Override
	public int getLikeCountByAid(int aid) {
		List<Like> list = mapper.findByAid(aid);
		if(list == null)
			return 0;
		return list.size();
	}

	

}

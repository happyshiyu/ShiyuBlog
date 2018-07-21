package cn.shiyu.service;


public interface LikeService {
	public int getLikeCountByAid(int aid);
	
	public boolean like(int uid, int aid);
	
	public boolean isLike(int uid, int aid);
}

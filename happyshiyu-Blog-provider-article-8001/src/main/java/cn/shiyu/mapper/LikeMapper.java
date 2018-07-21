package cn.shiyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.shiyu.entity.Like;

@Mapper
public interface LikeMapper {
	public List<Like> findByAid(int aid);
	
	public Like findByUidAndAid(@Param("uid") int uid, @Param("aid") int aid);
	
	public void addLike(@Param("uid") int uid, @Param("aid") int aid);
	
	public void deleteByUidAndAid(@Param("uid") int uid, @Param("aid") int aid);
	
}

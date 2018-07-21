package cn.shiyu.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.shiyu.entity.CommentChild;

@Mapper
public interface CommentChildMapper {
	public List<CommentChild> findByCommentId(int cid);
	
	public int addCommentChild(CommentChild c);
	
	public int deleteCommentChild(CommentChild c);

}

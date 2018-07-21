package cn.shiyu.mapper;


import org.apache.ibatis.annotations.Mapper;

import cn.shiyu.entity.Comment;

@Mapper
public interface CommentMapper {
	public Comment findByArticleId(int aid);
	
	public int addComment(Comment c);
	
	public int deleteComment(Comment c);

}

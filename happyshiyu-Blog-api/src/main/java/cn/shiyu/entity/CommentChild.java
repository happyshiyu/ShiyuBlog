package cn.shiyu.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)

public class CommentChild implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	//对应评论的ID
	private int commentId;
	//评论内容
	private String content;
	//回复的用户ID
	private int userId;
	//回复目标用户的id
	private int toId;
	//回复时间
	private String createTime;
	
}

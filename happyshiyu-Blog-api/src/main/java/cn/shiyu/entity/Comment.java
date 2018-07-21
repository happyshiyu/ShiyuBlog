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

public class Comment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	//对应文章的ID
	private int articleId;
	//评论内容
	private String content;
	//用户ID
	private int userId;
	//评论时间
	private String createTime;
	
}

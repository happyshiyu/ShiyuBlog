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
public class Article implements Serializable{
	
	private static final long serialVersionUID = -8533503603971563612L;
	
	private int id;
	//标题
	private String title;
	//摘要
	private String summary;
	//内容
	private String content;	
	//分类
	private String category;
	//标签
	private String label;
	//发布时间
	private String createTime;
	//作者id
	private int authorId;
}

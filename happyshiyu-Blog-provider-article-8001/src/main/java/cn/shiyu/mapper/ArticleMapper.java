package cn.shiyu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.shiyu.entity.Article;

@Mapper
public interface ArticleMapper {
	public Article findById(int id);
	
	public List<Article> findByAuthorId(@Param("id")int id, @Param("begin")int begin, @Param("pageSize")int pageSize);
	
	public List<Article> findAll();
	
	public int addArticle(Article article);
	
	public int updateArticle(Article article);

}

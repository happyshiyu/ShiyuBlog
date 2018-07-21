package cn.shiyu.service;

import java.util.List;


import cn.shiyu.entity.Article;

public interface ArticleService {
	public Article findById(int id);
	
	public List<Article> findByAuthorId(int id, int pageNum, int pageSize);
	
	public List<Article> findAll();
	
	public int addArticle(Article article);
	
	public int updateArticle(Article article);
}

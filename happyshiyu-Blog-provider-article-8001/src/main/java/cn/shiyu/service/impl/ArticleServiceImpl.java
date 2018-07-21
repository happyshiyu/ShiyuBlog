package cn.shiyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shiyu.entity.Article;
import cn.shiyu.mapper.ArticleMapper;
import cn.shiyu.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	ArticleMapper mapper;

	@Override
	public Article findById(int id) {
		return mapper.findById(id);
	}

	@Override
	public List<Article> findByAuthorId(int id, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return mapper.findByAuthorId(id, (pageNum-1)*pageSize, pageSize);
	}

	@Override
	public List<Article> findAll() {
		return mapper.findAll();
	}

	@Override
	public int addArticle(Article article) {
		return mapper.addArticle(article);
		
	}

	@Override
	public int updateArticle(Article article) {
		return mapper.updateArticle(article);
	}


}

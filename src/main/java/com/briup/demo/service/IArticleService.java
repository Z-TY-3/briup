package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Article;
import com.briup.demo.util.CustomerException;

/*
 * 文章相关内容的service接口
 */
/**
 * @author hero
 *
 */
public interface IArticleService {
	/*
	 * 新增文章
	 * 
	 */
	public void saveOrUpdateArticle(Article article) throws CustomerException;
	/*
	 * 删除文章   integer 防止误操作   id=0删除了  为空？判断  一般自增长是从1开始
	 */
	public void deleteArticleById(int id) throws CustomerException;
	
	/**
	 * 查询文章
	 * @param keyStr	搜索关键字
	 * @param condition  栏目框
	 * @return
	 * @throws CustomerException
	 */
	public List<Article> findArticalByCondition(
					String keyStr,String condition) throws CustomerException;
	
	/*
	 * 通过id查询文章     点击文章 显示详情页面  对应id点击数 +1 
	 */
	public Article findArticleById(int id) throws CustomerException;
	
	
	
}
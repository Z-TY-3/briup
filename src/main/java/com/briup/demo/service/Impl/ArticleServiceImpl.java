package com.briup.demo.service.Impl;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.ArticleExample.Criteria;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.StatusCodeUtil;


@Service
public class ArticleServiceImpl implements IArticleService {
	@Autowired
	private ArticleMapper ArticleMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void saveOrUpdateArticle(Article article) throws CustomerException {
			if (article==null) {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "空文章");
			}
			if (article.getId()==null) {
				//需要额外添加两条数据
				article.setPublishdate(new Date()); //当前日期就是发布时间
				article.setClicktimes(0);
				ArticleMapper.insert(article);
			}else {
//				if () {
//				}
				//作者修改     非作者修改    boolean类型
				article.setPublishdate(new Date());//不设计随意修改日期  复制到原创？
				ArticleMapper.updateByPrimaryKey(article);//更新发布 要修改日期
				//跟天气挂钩  跟接口挂钩
			}
	}
	
	@Override
	public void deleteArticleById(int id) throws CustomerException {
					ArticleMapper.deleteByPrimaryKey(id);
	}

	
	@Override
	public List<Article> findArticalByCondition(
						String keyStr, String condition) 
				throws CustomerException {
		//三种情况
		/*
		 *1.没有添加任何条件 则查询所有文章  
		 *2.没有指定栏目,但指定了查询的关键字，则根据关键字查询满足条件的所有文章
		 *3.指定栏目，同时指定查询的关键字，根据栏目和关键字查询满足条件的文章
		 *
		 *4.指定栏目，没有指定查询的关键字，则根据栏目和关键字查询满足条件文章
		 *4.指定栏目未指定关键字呢？？？  后台根据key值传          项目需求分析
		 */
//		if (condition) {}//代码嵌套10个if    low   龊 难搞啊
		keyStr =  keyStr==null?"":keyStr.trim();
		condition = condition==null?"":condition.trim();
		ArticleExample example = new ArticleExample();
		//往上放  下面都要  还是不要局部变量  满足什么条件依次添加准则
		if ("".equals(keyStr)&&"".equals(condition)) {		//都空
			//情况1
			return ArticleMapper.selectByExample(example);
		}else if (!"".equals(keyStr)&&"".equals(condition)) {//关键字为空 后面不用判断
			//搜索标题还是作者  还要一个参数    conditionalkey 指定是作者还是标题  
			//然后再查 if 等算法
			example.createCriteria().andTitleLike("%"+keyStr+"%");
			return ArticleMapper.selectByExample(example);
		}
		else if(!"".equals(condition)&&"".equals(keyStr)){	//栏目为空
			//需要的放再逻辑运算前
			//情况4
			//拿着栏目的名字  查找id
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);//不是模糊查询
			
			//如果栏目名称是唯一键  则不是list
			List<Category> category = //设计的时候只有一个
					categoryMapper.selectByExample(categoryExample);
			
			//如果 根据条件体查到栏目  category.size()>0
			if (category.size()>0) {
				//根据栏目信息找到里面所有文章
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
			}//如果 根据条件体    没有查到栏目
			else {
				throw new CustomerException(
						StatusCodeUtil.ERROR_CODE, "条件查询栏目 没有此栏目名字");
			}
			return ArticleMapper.selectByExample(example);

		}
		
		else {//2*2=4组合排列
			//作个逻辑语  看看到底哪个为空
			//情况3
			
			
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);//不是模糊查询
			
			//如果栏目名称是唯一键  则不是list
			List<Category> category = //设计的时候只有一个
					categoryMapper.selectByExample(categoryExample);
			
			//先消除笛卡尔 再模糊查找

			if (category.size()>0) {
				//集合对元素一个属性进行排序
				
				
			example.createCriteria()
					.andCategoryIdEqualTo(category.get(0).getId())
					.andTitleLike("%"+keyStr+"%");}
			//or的方式 拼接条件
//			example.or().andTitleLike("%"+keyStr+"%");
			else {
				throw new CustomerException(
						StatusCodeUtil.ERROR_CODE, "没有指定的搜索栏目");
			}
			return ArticleMapper.selectByExample(example);
		}		
		
	}

	/*
	 * 通过id查询文章     	点击文章 显示详情页面  对应id点击数 +1 
	 */
	@Override
	public Article findArticleById(int id) throws CustomerException {
		Article article = ArticleMapper.selectByPrimaryKey(id);
		
		Integer clicksTime=
					article.getClicktimes() == null?0:article.getClicktimes();
		article.setClicktimes(clicksTime+1);//持久化到dao层
		ArticleMapper.updateByPrimaryKey(article);
//		this.saveOrUpdateArticle(article);  //持久化  	更新  日期要变
		return article;
	}

	
	
	
	
	
}

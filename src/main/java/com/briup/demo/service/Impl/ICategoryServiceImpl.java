package com.briup.demo.service.Impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.CategoryExample.Criteria;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.Message;
import com.briup.demo.util.StatusCodeUtil;


/*
 * 操作栏目的service功能类
 */
@Service
public class ICategoryServiceImpl implements com.briup.demo.service.ICategoryService {
	@Autowired 
	private CategoryMapper CategoryMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryExMapper CategoryExMapper;
	
	@Override
	public List<Category> findAllCategory() throws CustomerException {
			CategoryExample example = new CategoryExample();
			return CategoryMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdateCategory(Category c) throws CustomerException {
		if (c==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "栏目为空");
		}	
		if (c.getId()==null) {
			
			if (!"".equals(c.getName())) {//属性已经约束
				
				//根据当前参数category条件  sql查询 在数据库中判断是否主键  或 条件唯一
				//了解category属性字段  example特性  全面了解  
				
				//id是自增长的  不会异常  则通过条件体     也可以不自增长
				CategoryExample example = new CategoryExample();
				example.createCriteria().andNameEqualTo(c.getName());
				
				example.or().andCodeEqualTo(c.getCode());//或者用and拼接
				
				List<Category> category = CategoryMapper.selectByExample(example);
				
				
				if (category.size()>0) 
				{
					//if看是code还是name
					throw new CustomerException(StatusCodeUtil.ERROR_CODE, "当前栏目已经存在");
				}else {
					CategoryMapper.insert(c);
				
				}
				
			}
			
			
		}else {
			/*用一个id(或随便一个标识)来标识修改和增加      不通过id标识的话  
			 * 就是主键如果存在 我的世界栏目    
			 * 如果  修改数据		(或者给修改一个标识  或else)
			 *     如果  添加则异常  
			 */
			CategoryMapper.updateByPrimaryKey(c);
		}
	}



	@Override
	public List<Category> findCategorysByName(String name) throws CustomerException {
		name =   name==null?"":name.trim();
		CategoryExample example = new CategoryExample();
		if ("".equals(name)) {
			//空条件体
			return CategoryMapper.selectByExample(example);
		}else {
			//条件体添加模板对象后
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+name+"%");
			return CategoryMapper.selectByExample(example);
		}
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		return CategoryMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		//哪些文章要删 就看对应的栏目的文章都删
		articleMapper.deleteByExample(example);//根据条件删除 什么条件  外键的条件
		
		CategoryMapper.deleteByPrimaryKey(id);
	}

	
	
	
	//作业  然后整理回顾 然后数据结构 linxu装系统玩玩  cpu
	
	
	/*栏目存在的话，提示信息功能，不执行增加操作。
	 * 增加操作
	 *   1.通过查看栏目是否存在 
	 * 		存在则抛出  "栏目已存在异常"
	 * 		不存在则继续增加
	 */
	/*public void CategoryExistException(Category c) throws CustomerException {
		//插入逻辑处理
//		c.ge
//		CategoryExample example = new CategoryExample();
//		example.createCriteria().   条件没有查询当前  整个c的  //数据多 麻烦
		
		//查询当前要插入category的id对应的category
		Category category = CategoryMapper.selectByPrimaryKey(c.getId());
		
		if ("".equals(category.getName())) 
		{
			CategoryMapper.insert(c);
		}else {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "当前栏目已经存在");
		}
		
 	}*/
	
	

	@Override
	public List<CategoryEx> CategoryAndArticle() throws CustomerException {
		List<CategoryEx> selectCEList = CategoryMapper.selectCE();
		return selectCEList;
	}
	
	
	/*
	 * 查询所有栏目及以下的文章
	 */
	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException{
		return CategoryExMapper.findAllCategoryEx();
	}

	
	
	/*
	 * 查询指定栏目以下的文章
	 */
	@Override
	public CategoryEx findCategoryExById(int id) throws CustomerException {
		return CategoryExMapper.findCategoryExById(id);
	}
	
	
	
}

package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.util.CustomerException;

public interface ICategoryService {
	/*
	 * 查询所有栏目
	 */
	public List<Category> findAllCategory( ) throws CustomerException;
	
	/*
	 * 添加或修改栏目信息
	 */
	public void saveOrUpdateCategory(Category category) throws CustomerException;
	
	/*
	 * 根据id删除栏目信息
	 */
	
	public void deleteCategoryById(int id) throws CustomerException;
	
	/*
	 * 根据栏目名查找栏目
	 */
	public List<Category> findCategorysByName(String  name) throws CustomerException;
	
	/*
	 * 不通过栏目检索
	 * 想要操作某一个栏目信息
	 * 
	 * 根据id查找指定栏目信息
	 */
	
	public Category findCategoryById(int id) throws CustomerException;

	
	/*
	 * 栏目 级联文章信息封装到exBean中  	然后返回至前台
	 *		
	 */
	public List<CategoryEx> CategoryAndArticle( ) throws CustomerException;
	
	
	
	/*
	 * 栏目 级联文章信息封装到exBean中  	然后返回至前台
	 *		
	 */
	List<CategoryEx> findAllCategoryEx() throws CustomerException;
	
	
	/*
	 * 查询栏目及其包含的文章所有数据
	 */
	CategoryEx findCategoryExById(int id) throws CustomerException;
	
	
	
	/*
	 * 点击一次栏目  点击数加1
	 */
	
	
}

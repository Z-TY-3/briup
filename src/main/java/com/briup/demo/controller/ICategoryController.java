package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.ArticleExample.Criteria;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.Message;
import com.briup.demo.util.MessageUtil;
import com.briup.demo.util.StatusCodeUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 与栏目相关的和前端交互的web层  swagger接口
 */
@RestController
@Api(description="栏目相关接口")
public class ICategoryController {
	@Autowired
	private ICategoryService iCategoryService;
	@Autowired
	private ArticleMapper ArticleMapper;
	
	@PostMapping("/addCategory")
	@ApiOperation("新增栏目")
	public Message<String> saveCategory(Category category){
			try {
				iCategoryService.saveOrUpdateCategory(category);
				return MessageUtil.success();
			} catch (CustomerException e) {
				return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "增加栏目失败:"+e.getMessage());
			}
	}
	
	//修改有异常
	@PostMapping("/updateCategory")
	@ApiOperation("修改栏目")
	public Message<String> updateCategory(Category category){
		try {
			iCategoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "增加栏目失败 ");
		}
}
	
	
	@GetMapping("/selectCategory")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> findAllCategory(){
		 return MessageUtil.success(iCategoryService.findAllCategory());
	}
	
	
	

	@GetMapping("/findCategorysByName")
	@ApiOperation("模糊查询栏目")
	public Message<List<Category>> findCategorysByName(String name){
		return MessageUtil.success(
				iCategoryService.findCategorysByName(name));
	}
 	
	//修改删除传的参数多的就是post请求  传参不多就get
	@GetMapping("/findCategoryById")
	@ApiOperation("查找指定id栏目")
	public Message<Category> findCategoryById(int id){
		return MessageUtil.success(iCategoryService.findCategoryById(id));
	}
	
	
	@GetMapping("/deleteCategory")
	@ApiOperation("删除指定id栏目")//先删文章 然后删栏目
	public Message<String> deleteCategoryById(int id){
		iCategoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	
//	@GetMapping("/categoryAndArticle")
//	@ApiOperation("栏目")
//	public Message<CategoryEx> categoryAndArticle() 
//			throws CustomerException {
//		return MessageUtil.success(iCategoryService.CategoryAndArticle());
//	}
		
	
	
	@GetMapping("findCategoryExById")
	@ApiOperation("/id指定栏目下文章")
	public Message<CategoryEx> findCategoryExById(int id){
			return MessageUtil.success(
					iCategoryService.findCategoryExById(id));
	}
	
	
	
}

package com.briup.demo.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexData;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;
import com.briup.demo.util.CustomerException;


@Service
public class IIndexResultServiceImpl implements IIndexResultService {

	
	@Autowired 
	private ILinkService ILinkService;
	
	@Autowired
	private ICategoryService ICategoryService;
	
//	@Autowired
//	private CategoryExMapper categoryExMapper;//到不进？？空指针  后面就不是这种写法
	
	
//	@Override
//	public List<CategoryEx> categoryAndArticle() throws CustomerException {
//		//
//		List<CategoryEx> list = categoryExMapper.findAllCategoryExs();
//		
//		return list;
//	}
	
	@Override
	public IndexData findIndexData() throws CustomerException {
		IndexData index = new IndexData();
		//设置所有的超链接信息
		List<Link> links = ILinkService.findAllLinks();
		index.setLinks(links);
		//设置所有栏目及其包含的文章
		List<CategoryEx> categoryExs = 	ICategoryService.findAllCategoryEx();
		//设置所有栏目及其包含的所有文章信息
		index.setCategoryExs(categoryExs);
		return index;
	}
	
	
	
	
	
	
	
	
	
	
	

}

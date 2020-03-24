package com.briup.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexData;
import com.briup.demo.util.CustomerException;

/*
 * 首页数据管理的Service
 */


public interface IIndexResultService {
	
	
	/*
	 * 栏目 级联文章信息封装到exBean中  	然后返回至前台
	 *		
	 */
	public IndexData findIndexData() throws CustomerException; 
	
	
	
	

	
	
}

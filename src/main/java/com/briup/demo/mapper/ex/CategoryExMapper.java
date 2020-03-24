package com.briup.demo.mapper.ex;

import java.util.List;


import com.briup.demo.bean.ex.CategoryEx;

/*
 *
 * 处理查询栏目及其包含的文章信息
 *
 */

public interface CategoryExMapper {
	/*
	 * 实现查询所有栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx> findAllCategoryEx();


		/*
		 * 通过id查询对应栏目及其包含的栏目信息
		 */
	CategoryEx findCategoryExById(int id);


}

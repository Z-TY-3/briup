package com.briup.demo.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.demo.bean.Link;

/**
 * 
 * 保存首页的所有数据  
 * @author hero
 *
 */
public class IndexData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CategoryEx> categoryExs;
	private List<Link> links;
	
	
	public List<CategoryEx> getCategoryExs() {
		return categoryExs;
	}
	public void setCategoryExs(List<CategoryEx> categoryExs) {
		this.categoryExs = categoryExs;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public IndexData(List<CategoryEx> categoryExs, List<Link> links) {
		super();
		this.categoryExs = categoryExs;
		this.links = links;
	}
	public IndexData() {
		
	}
	//dao层数据返回  service 返回  controller  
	
	
}

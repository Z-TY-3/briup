package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Link;

import com.briup.demo.util.CustomerException;

/*
 * 关于链接的相关操作
 */
public interface ILinkService {
	/*
	 * 保存链接信息
	 */
	void saveOrUpdateLink(Link link) throws CustomerException;
	/*
	 * 查找链接信息
	 */
	Link selectLink(Integer LinkIdkey) throws CustomerException;
	/*
	 * 查询所有链接
	 */
	List<Link> findAllLinks()  throws CustomerException;
	/*
	 *根据Id删除链接信息
	 */
	void deleteLinkById(int id) throws CustomerException;
	/*
	 * 根据链接名 查询链接   		搜索框
	 */
	List<Link> findLinksByName(String name) throws CustomerException;		
}

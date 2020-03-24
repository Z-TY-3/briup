package com.briup.demo.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.StatusCodeUtil;


/*
 * 操作链接的service功能类
 */
@Service//逻辑处理    执行sql数据库  DML插入删除修改
//事务管理交给service    异常在web层处理 
public class LinkServiceImpl implements ILinkService {
	@Autowired
	private LinkMapper LinkMapper;
	
	
	//添加链接  实际需求
	@Override
	public void saveOrUpdateLink(Link link) throws CustomerException {
		//判空处理  引用类型null 绝对不为空     后台代码停项目
		if (link==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE,"没有传链接 主键可能会自增！");
		}//主键自动填写  那么要求其他两个属性也必须填写 不就不为空了
		if (link.getId()==null) {  //插入与主键不同的        这个是增加其他主键自增长
			LinkMapper.insert(link);// 
		}else {
			LinkMapper.updateByPrimaryKey(link);//映射文件底层 直接传link  自动匹配Id更新
		}//这个就是更改
	}


	/*
	 * 主键查找(primary key)
	 */
	@Override
	public Link selectLink(Integer LinkIdkey) throws CustomerException {
		if (LinkIdkey==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "ID为空");
		}else {
			if (LinkMapper.selectByPrimaryKey(LinkIdkey)==null) {
				//Id没有对应链接
				throw new CustomerException(StatusCodeUtil.NOFOUND_CODE, "ID对应链接不存在");
			}
			return LinkMapper.selectByPrimaryKey(LinkIdkey);
		}
	}
	
	/*
	 * 删除链接数据
	 */
	@Override
	public void deleteLinkById(int id) throws CustomerException {
//		if (Id==null) {//没有数据  删了空白  id不存在 也可以去删    执行删除操作   
//			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "Id为空:");
//		}else {
//			if (LinkMapper.deleteByPrimaryKey(Id)==0) {
//				//Id没有对应链接
//				throw new CustomerException(StatusCodeUtil.NOFOUND_CODE, "Id对应链接不存在");
//			}
//			LinkMapper.deleteByPrimaryKey(Id);
//		}
		LinkMapper.deleteByPrimaryKey(id);
	}


	@Override
	public List<Link> findAllLinks() throws CustomerException {
		//条件体；
		return LinkMapper.selectByExample(new LinkExample());
	}



	//name  如果没传我自己指定为空自字符串  我自己包阔
	@Override 
	public List<Link> findLinksByName(String name) throws CustomerException {
		name=   name==null?"":name.trim();//或者if  其他算法
		LinkExample example = new LinkExample();
		if ("".equals(name)) {//name放后   null 会异常
			//什么不返回    或返回以前的所有数据  
			//如果没有搜索条件 返回所有数据
			return	LinkMapper.selectByExample(example);    //还是给了返回响应
		}else {
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+name+"%");//百分号没写
			//添加了条件的模板对象
			return LinkMapper.selectByExample(example);
		}
	}
	
}

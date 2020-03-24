package com.briup.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.briup.demo.bean.Link;
import com.briup.demo.service.ILinkService;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.Message;
import com.briup.demo.util.MessageUtil;
import com.briup.demo.util.StatusCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/*
 * 与链接相关的和前端交互的web层  	生成swagger
*/

@RestController
@Api(description="链接相关接口")//对接口描述信息百度

//@ApiModel("工作者实体")
public class LinkConstroller {
	@Autowired //自动关联实现类 
	private ILinkService linkService;
	
	
//	@GetMapping("/test")查询和删除  
	//插入数据不知道返回什么结果  就string object
	@PostMapping("/addLink")//传值 插入 修改
	@ApiOperation("新增链接")
	public Message<String> addLink(Link link){
		try {
			//可能会空      service 抛上来
			linkService.saveOrUpdateLink(link);
			return MessageUtil.success();//或注解 插入主键多少的成功
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误:  "+e.getMessage());
		}//就是把底层的message返回过去   这些都是util工具封装思想
	}
	
	@PostMapping("/updateLink")//传值 插入 修改
	@ApiOperation("修改链接")
	public Message<String> UpdateLink(Link link){
		try {
			//可能会空      service 抛上来
			linkService.saveOrUpdateLink(link);
			return MessageUtil.success();//或注解 插入主键多少的成功
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误:  "+e.getMessage());
		}//就是把底层的message返回过去   这些都是util工具封装思想
	}
	
	//查询 Select
	@GetMapping("/selectLink")
	@ApiOperation("通过ID查询链接")
	public Message<Link> SelectLink(Integer LinkIdkey){
		try {
			Link link = linkService.selectLink(LinkIdkey);
			return MessageUtil.success(link);
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.NOFOUND_CODE, "链接查询错误:"+e.getMessage());
		}
	}
	
	/*
	 * 查询所有的link
	 */
	@GetMapping("/selectLinks")
	@ApiOperation("查询所有链接")
	public Message<List<Link>> selectLinks(){
	List<Link> list = linkService.findAllLinks();
		return MessageUtil.success(list);
	}
	
	/*
	 * 删除
	 */
	@GetMapping("/deleteLink")
	@ApiOperation("通过Id删除链接")
	public Message<Link> deleteLinkById(int id){
		linkService.deleteLinkById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/selectLinkByName")
	@ApiOperation("/模糊查询")
	public Message<List<Link>> selectLinkByName(String name){
		return MessageUtil.success(linkService.findLinksByName(name));
		//需不需处理异常  看后面又没有抛出  
	}
	
	
	
	
}

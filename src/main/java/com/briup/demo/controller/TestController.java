package com.briup.demo.controller;
//前后端分离  就是不在返回视图名  返回json

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="测试接口")//对constroller描述信息 
public class TestController {
	
//	@RequestMapping(value="/test")       //找每一个映射   一般只是getpost 两种方式
	@GetMapping("/test")
	@ApiOperation("画的理解")
	public String test() {
		return "hello";
	}
	//dao层一个支持   mybatis一个支持
}


//搞参数
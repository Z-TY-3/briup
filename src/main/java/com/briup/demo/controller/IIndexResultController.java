package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.ex.IndexData;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.util.Message;
import com.briup.demo.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="首页相关接口")
public class IIndexResultController {
		
	@Autowired
	private IIndexResultService IIndexResultService;
	
	
	@GetMapping("/index")
	@ApiOperation("首页")
	public Message<IndexData> findIndex(){
		return MessageUtil.success(IIndexResultService.findIndexData());
	}
}

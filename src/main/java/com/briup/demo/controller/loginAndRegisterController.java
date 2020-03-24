package com.briup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.service.loginAndRegisterService;
import com.briup.demo.util.CustomerException;
import com.briup.demo.util.Message;
import com.briup.demo.util.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="注册登录")
public class loginAndRegisterController {
	@Autowired
	private loginAndRegisterService loginAndRegisterService;
	
	@PostMapping("/Register")
	@ApiOperation("注册信息")
	public Message<String> customerRegister(Customer customer) {
			try {
				loginAndRegisterService.customerRegister(customer);
				return MessageUtil.success();
			} catch (CustomerException e) {
				return MessageUtil.error(500, "注册失败:"+e.getMessage());
			}
	}
	
	
	@PostMapping("/Login")
	@ApiOperation("登录")
	public Message<String> customerLogin(Customer customer) {
			try {
				loginAndRegisterService.customerLogin(customer);
				return MessageUtil.success();
			} catch (CustomerException e) {
				return MessageUtil.error(500, "登录失败:"+e.getMessage());
			}
	}
	
	@PostMapping("/selectCustomerById")
	@ApiOperation("通过id查询")
	public Message<Customer> selectCustomerByExample(int id) {
			return MessageUtil.success(
					loginAndRegisterService.selectCustomerByExample(id));
	}
	
}

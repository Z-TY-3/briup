package com.briup.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;
import com.briup.demo.util.CustomerException;

/*
 * 登陆和注册操作功能模块
 */
public interface loginAndRegisterService {
		/*
		 * 注册    
		 * 	按钮将数据保存到数据库   
		 * 		保存之前有注册规则
		 * 	
		 */
	void customerRegister(Customer customer) throws CustomerException;
	
	/*
	 * 登陆
	 * 	返回成功
	 */
	Customer customerLogin(Customer customer) throws CustomerException;
	
	
	/*
	  *  通过条件  对用户增删改查
	 * 
	 */
	
	Customer selectCustomerByExample(int id) throws CustomerException;
	
	
}	

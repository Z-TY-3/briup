package com.briup.demo.service.Impl;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.dao.customerDao;
import com.briup.demo.service.loginAndRegisterService;
import com.briup.demo.util.CustomerException;

@Service
public class loginAndRegisterImpl implements loginAndRegisterService{
	
	@Autowired
	private customerDao customerDao2;//service层    
	
	
	
	@Override
	public void customerRegister(Customer customer) throws CustomerException {
		//传空值  插入空值 删除空值可以不做处理
		Customer findByusername = customerDao2.findByusername(customer.getUsername());
		if ("".equals(findByusername.getUsername())) {
			customerDao2.save(customer);
		}else {
			throw new CustomerException(500, "用户名已存在");
		}
		
	}


	@Override
	public Customer customerLogin(Customer customer) throws CustomerException {
			Customer findByusername = 
					customerDao2.findByusername(customer.getUsername());
			//这个用户名不存在    
			if (findByusername.getId()!=null) {
				//用户名存在
					if (customer.getPassword()==findByusername.getPassword()) {
						return customer; //向前台返回数据
					}
					else {
						throw new CustomerException(500, "密码错误");
					}
			}else {
					throw new CustomerException(500, "用户名不存在");
			}
			
			
	}


	@Override
	public Customer selectCustomerByExample(int id) throws CustomerException {
			//或者用户名 写在同一个方法？
		Optional<Customer> findById = customerDao2.findById(id);
			
		return findById.get();
	}



	
}

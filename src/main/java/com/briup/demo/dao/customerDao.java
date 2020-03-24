package com.briup.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briup.demo.bean.Customer;

//@Repository
public interface customerDao extends JpaRepository<Customer, Integer>{
	//通过username查询用户
		Customer findByusername(String username);
}

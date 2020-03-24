package com.briup.demo.util;

//自定义相应类型  string list double 等   传什么我就用泛型指定什么

//
public class Message<T> {
	private Integer status;
	private String message;
	private T data;
	private Long time; 
	//自动拆箱封箱     没传值 会歧义   类类型就一个null 统一 不歧义  java与数据库交互
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Message(Integer status, String message, T data, Long time) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.time = time;
	}
	
}

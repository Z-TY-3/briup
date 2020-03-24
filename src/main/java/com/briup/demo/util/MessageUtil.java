package com.briup.demo.util;

import java.util.Date;

//基础代码      封装了下  可能每个状态以每个类来写
public class MessageUtil {
/*
 * 成功 并且返回数据  
 *  //E标识标识后面E一个泛型  E是什么含义 不知道 就是一个泛型类
 *   告诉后面泛型  不成文规定
 */
	public static <E> Message<E> success(E obj){
		return new Message<E>(200,"success",obj,new Date().getTime());
	}
	
	/*
	 * 成功，但无返回数据
	 */
	public static <E> Message<E> success(){
		return new Message<E>(200,"success",null,new Date().getTime());
	}
	//405异常 错误请求方式  状态码
	
	/*
	 * 失败 将自定义异常信息返回   
	 */
	public static <E>Message<E> error(Integer code,String msg){
		return new Message<E>(code,msg,null,new Date().getTime());
		
	}
	
}

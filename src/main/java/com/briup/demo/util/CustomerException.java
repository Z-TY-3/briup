package com.briup.demo.util;

public class CustomerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Integer code;

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public CustomerException(Integer code,String message) {
			super(message);//自定义异常往上抛
			this.code=code;   
			//返回200状态码  我
			//有时list  string 有时json不同   后台需要传什么格式
		}
		
		

}

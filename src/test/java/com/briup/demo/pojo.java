package com.briup.demo;

public class pojo {
	private Integer id;
	private String name;
	private Integer age;
	public pojo(Integer id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public pojo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "pojo [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}

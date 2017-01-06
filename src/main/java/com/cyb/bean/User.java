package com.cyb.bean;

import org.h2.mvstore.DataUtils;

public class User {
 public String name;
 public String getName() {
	 DataUtils XL;
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
public Boolean getSex() {
	return sex;
}
public void setSex(Boolean sex) {
	this.sex = sex;
}
public Integer age;
 public Boolean sex;
}

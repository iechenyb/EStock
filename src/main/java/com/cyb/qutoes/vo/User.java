package com.cyb.qutoes.vo;

import java.util.List;
import java.util.Map;


//@Component("user")
public class User {
	public String id = "";
	public String username = "";
	public String password = "";
	public long roleId = 1;
	public long loginstatus = 1;
	public long registerTime = 0;
	public long status = 1;
	public long phone = 0;
	public String email = "";
	public String page = "";
	public String yzm;
	public List<Map<String,String>> list;
	public String getId() {
//		@SuppressWarnings("deprecation")
//		MappingJacksonHttpMessageConverter x;
//		MappingJacksonHttpMessageConverter y;
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(long loginstatus) {
		this.loginstatus = loginstatus;
	}
	public long getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public String toString(){
		return this.id+","+this.username;
	}
}

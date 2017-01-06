package com.cyb.qutoes.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity(name="usr")
@Table(name="usr")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class AnUser {
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
	@Id
	@Column(name="id_")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="roleid")
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	@Column(name="loginstatus")
	public long getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(long loginstatus) {
		this.loginstatus = loginstatus;
	}
	@Column(name="registertime")
	public long getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
	@Column(name="status")
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	@Column(name="phone")
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="page")
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Column(name="yzm")
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
}

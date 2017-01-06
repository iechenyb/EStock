package com.cyb.xtqx.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author DHUser
 * many to many 
 */
//@Entity
//@Table(name="usr_role",catalog="Hibernate_Many2Many")
public class UserRole {
	@Column(name = "id_")
	@Id
	// 表示主键
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	// 自增长
	private String id;
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String userid;
	private int roleid;
	
	
	private Set<RoleQX> roles = new HashSet<RoleQX>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Set<RoleQX> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleQX> roles) {
		this.roles = roles;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
}

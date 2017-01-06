package com.cyb.xtqx.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleQX {
	private int id;
	private String name;
	private Set<UserQX> users;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsers(Set<UserQX> Users) {
		this.users = Users;
	}
	
	@ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	public Set<UserQX> getUsers() {
		return users;
	}
}

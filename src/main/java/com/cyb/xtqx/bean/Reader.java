package com.cyb.xtqx.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 一对多  一端
 * @author DHUser
 *
 */
@Entity
@Table(name = "reader")
public class Reader {
	 /*@GeneratedValue(generator="system-uuid")  
	 @GenericGenerator(name="system-uuid", strategy = "uuid") */ 
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "MENO")
	private String meno;
	@Column(name = "NAME")
	private String name;
	
	@OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Book> books = new HashSet<Book>();

	public Reader() {
	}

	public Set getBooks() {
		return books;
	}

	public void setBooks(Set books) {
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
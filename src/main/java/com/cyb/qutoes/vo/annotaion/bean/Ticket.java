package com.cyb.qutoes.vo.annotaion.bean;



import javassist.util.proxy.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity  // 表示为实体类
@Table(name="ticket") 
public class Ticket {
	private String id;
	private Long num;
	@Id              // 表示主键
	@GenericGenerator(name = "generator", strategy = "increment")   
	@GeneratedValue(generator = "generator")   // 自增长
    @Column(name = "id")   
	public String getId() {
		Proxy x;
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name="num")            
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	public String toString(){
		return this.id+","+this.num;
	}
}

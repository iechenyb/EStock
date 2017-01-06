package com.cyb.qutoes.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Stock implements RowMapper<Stock>, Serializable{

	public String id;
	public String name;
	public String code;
	public String exchange;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public Stock mapRow(ResultSet rs, int arg1) throws SQLException {
		    Stock userInfo = new Stock();
	        userInfo.setId(rs.getString("ID_"));
	        userInfo.setName(rs.getString("NAME_"));
	        userInfo.setCode(rs.getString("CODE_"));
	        userInfo.setExchange(rs.getString("EXCHANGE_"));
	        return userInfo;
	}
	public String toString(){
		return this.code+","+this.name+".";
	}
}

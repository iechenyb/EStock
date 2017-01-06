package com.cyb.database;

import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.qutoes.vo.Stock;

public class JdbcUtils {
	public static JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");
	public Object insert(Object item) {   
	   String sql ="INSERT INTO items(user_id,name,phone,email) VALUES(?,?,?,?)";   
	   Object[] params =new Object[]{item.hashCode(),item.hashCode(),item.hashCode(),item.hashCode()};   
	   int[] types =new int[]{Types.INTEGER,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.TIME};   
	   jdbcTemplate.update(sql,params,types);   
	   return item;   
	}   
	public Object update(Object item) {   
		 String sql ="UPDATE items SET name = ?, phone = ?, email = ? WHERE id = ?";   
		 Object[] params =new Object[] {item.hashCode(),item.hashCode(),item.hashCode(),item.hashCode()};   
		 int[] types =new int[] {Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};   
		 jdbcTemplate.update(sql,params,types);   
		 return item;   
	}   
	public void delete(Object item) {   
		   String sql ="DELETE FROM items WHERE id = ?";   
		   Object[] params =new Object[] {1};   
		   int[] types =new int[]{Types.INTEGER};   
		   jdbcTemplate.update(sql,params,types);   
	}   
	public Object findById(int id) {   
		   String sql ="SELECT * FROM items WHERE id = ?";   
		   Object[] params =new Object[] {id};   
		   int[] types =new int[] {Types.INTEGER};   
		   List items = jdbcTemplate.query(sql,params,types,new Stock());   
		   if(items.isEmpty()){   
		    return null;   
		   }   
		  return(Object)items.get(0);   
	}  
	public List<Stock> findAll() {   
	    String sql ="SELECT * FROM items";   
	    return jdbcTemplate.query(sql,new Stock());   
	}   
	public List<Stock> findAllByUser(int user_id) {   
	   String sql ="SELECT * FROM items WHERE user_id = ?";   
	   Object[] params =new Object[]{user_id};   
	   int[] types =new int[]{Types.INTEGER};   
	   List items = jdbcTemplate.query(sql,params,types,new Stock());   
	   return items;   
	}   
}

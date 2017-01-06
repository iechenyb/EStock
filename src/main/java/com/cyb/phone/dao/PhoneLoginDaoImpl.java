package com.cyb.phone.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cyb.phone.db.DBEntity;
import com.cyb.phone.db.JdbcUtils;
import com.cyb.phone.vo.User;
@Repository("phoneLoginDao")
public class PhoneLoginDaoImpl {
	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate; 
	
	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;
	
	 public boolean checkLogin(User user){
		 String sql = "SELECT * FROM USR where username= '"+user.getUsername()+"' and password = '"+user.getPassword()+"'";
	     JdbcUtils dbUtils = new JdbcUtils(DBEntity.getConnection());
	     List<User> data = dbUtils.queryForList(User.class, sql);
	     if(data!=null&&data.size()>=1){
	    	 return true;
	     }else{
	    	 return false;
	     }
	 }
}

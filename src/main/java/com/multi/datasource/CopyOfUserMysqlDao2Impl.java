package com.multi.datasource;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class CopyOfUserMysqlDao2Impl{
	public SessionFactory sessionFactory;
	@Resource(name="jdbcTemplateMysql")
	public JdbcTemplate jdbcTemplate;
	public String  query(String name) {
		String a="";
		String sql = "select * from usr where username='"+name+"'";
		List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql);
		if(ls!=null&&ls.size()>0){
			System.out.println(ls.get(0));
			a = ls.get(0).get("age").toString();
		}
		return a;
	}
	public void updateAdd10(String name) {
		String sql = " update usr set age = age+10 where username='"+name+"'";
		jdbcTemplate.update(sql);
	}
	
	public void update(Object entity) {
		Session session = sessionFactory.openSession();
		session.update(entity);
		session.flush();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

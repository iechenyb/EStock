package com.multi.datasource;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserMysqlDao1Impl{
	public SessionFactory sessionFactory;
	@Resource(name="jdbcTemplateMysql1")
	public JdbcTemplate jdbcTemplate;
	public String  query(int id) {
		String a="";
		String sql = "select * from ticket where id='"+id+"'";
		List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql);
		if(ls!=null&&ls.size()>0){
			System.out.println(ls.get(0));
			a = ls.get(0).get("num").toString();
		}
		return a;
	}
	public void updateReset(){
		String sql = " update ticket set num = 10 ";
		jdbcTemplate.update(sql);
	}
	public void updateSet0(int id) {
		String sql = " update usr set num = 0 where id='"+id+"'";
		jdbcTemplate.update(sql);
	}
	public void updateAdd10(int id) {
		String sql = " update ticket set num = num+10 where id='"+id+"'";
		jdbcTemplate.update(sql);
	}
	public void updateSub10(int id) {
		String sql = " update ticket set num = num-10 where id='"+id+"'";
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

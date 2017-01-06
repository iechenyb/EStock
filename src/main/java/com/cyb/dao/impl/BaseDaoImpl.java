package com.cyb.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class BaseDaoImpl extends HibernateDaoSupport{
	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate; 
	@Resource
	public void setSF(SessionFactory sessionFactory){ 
		super.setSessionFactory(sessionFactory); 
	} 
	/*public Session getSession(){
		return currentSession();
	}*/
	public Session getSession(){
		Session session = null ;
		try {
			session = this.getSessionFactory().getCurrentSession();
			if(session==null){
				session =  this.getSessionFactory().openSession();
			}
		} catch (Exception e) {
			return  this.getSessionFactory().openSession();
		}
		return session;
	}
	public void close(Session session){
		if(session!=null){
			session.flush();
			session.close();
		}
	}
}

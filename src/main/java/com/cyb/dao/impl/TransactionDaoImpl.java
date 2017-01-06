package com.cyb.dao.impl;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
@Component("transactionDao")
//@Repository("transactionDao")
//@Transactional
public class TransactionDaoImpl extends BaseDaoImpl{
	Log log = LogFactory.getLog(TransactionDaoImpl.class);
	public void updateMerginJdbcTemplate(boolean throwEx){
		this.jdbcTemplate.update("update ticket set num=num+100 where id=1");
		log.info("更新后数"+this.jdbcTemplate.queryForInt("select nvl(num,0) from ticket where id=1"));
		if(throwEx){
			int i=1/0;
		}
		this.jdbcTemplate.update("update ticket set num=0 where id=1");
	}
	public void updateMerginHibernate(boolean throwEx){
		Session session = getSession();
		Query query  = session.createSQLQuery("update ticket set num=num+100 where id=1");
	 	query.executeUpdate();	
	 	log.info("更新后数"+this.jdbcTemplate.queryForInt("select nvl(num,0) from ticket where id=1"));
	 	if(throwEx){
			int i=1/0;
		}
	 	Query query1  = session.createSQLQuery("update ticket set num=0 where id=1");
	 	query1.executeUpdate();	
	 	/*session.flush();
	 	session.close();*/
	}
	public void updateMerginJdbcTemplate1(boolean throwEx) throws SQLException{
		this.jdbcTemplate.update("update ticket set num=num+100 where id=1");
		log.info("更新后数"+this.jdbcTemplate.queryForInt("select nvl(num,0) from ticket where id=1"));
		if(throwEx){
			try{
				int i=1/0;
			}catch(Exception e){
				log.info("事务回滚over！");
				throw e;
			}
			
		}
		this.jdbcTemplate.update("update ticket set num=0 where id=1");
	}
	public void updateMerginHibernate1(boolean throwEx) throws Exception{
		Session session = getSession();
		//session.beginTransaction();
		Query query  = session.createSQLQuery("update ticket set num=num+100 where id=1");
	 	query.executeUpdate();	
	 	log.info("更新后数"+this.jdbcTemplate.queryForInt("select nvl(num,0) from ticket where id=1"));
	 	if(throwEx){
			try {
				int i=1/0;
			} catch (Exception e) {
				session.getTransaction().rollback();
				log.info("事务回滚over！");
				throw e;
			}
			
		}
	 	Query query1  = session.createSQLQuery("update ticket set num=0 where id=1");
	 	query1.executeUpdate();	
	 /*	session.flush();
	 	session.close();*/
	}
	public void updateMix(boolean throwEx) throws Exception{
		Session session = getSession();
		//session.beginTransaction();
		this.jdbcTemplate.update("update ticket set num=num+100 where id=1");
		log.info("更新后数"+this.jdbcTemplate.queryForInt("select nvl(num,0) from ticket where id=1"));
	 	if(throwEx){
			try {
				int i=1/0;
			} catch (Exception e) {
				session.getTransaction().rollback();
				log.info("事务回滚over！");
				throw e;
			}
		}
	 	Query query1  = session.createSQLQuery("update ticket set num=0 where id=1");
	 	query1.executeUpdate();	
	 	//session.flush();
	 	//session.close();
	}
	public void add(boolean throwEx) throws Exception{
		this.jdbcTemplate.update("update ticket set num=num+100 where id=1");
	}
	public void addSub(boolean throwEx) throws Exception{
		Session session = getSession();
		Query query1  = session.createSQLQuery("update ticket set num=0 where id=1");
	 	query1.executeUpdate();	
	}
	public void display(){
		log.info("更新后数"+this.jdbcTemplate.queryForInt("select nvl(num,0) from ticket where id=1"));
	}
}

package com.cyb.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.dao.impl.TransactionDaoImpl;

@Service("transactionService")
public class TransactionServiceImpl {
	@Resource(name="transactionDao")
	public TransactionDaoImpl dao;
	public void updateMerginJdbcTemplate(boolean throwEx){
		this.dao.updateMerginJdbcTemplate(throwEx);
	}
	public void updateMerginHibernate(boolean throwEx){
		this.dao.updateMerginHibernate(throwEx);
	}
	public void updateMerginJdbcTemplate1(boolean throwEx) throws SQLException{
		this.dao.updateMerginJdbcTemplate1(throwEx);
	}
	public void updateMerginHibernate1(boolean throwEx) throws Exception{
		this.dao.updateMerginHibernate1(throwEx);
	}
	public void updateMix(boolean throwEx) throws Exception{
		this.dao.updateMix(throwEx);
	}
	@SuppressWarnings("unused")
	@Transactional
	public void updateCrossMethod(boolean throwEx) throws Exception{
		this.dao.add(throwEx);
		this.dao.display();
		if(throwEx){
			try {
				int i=1/0;
			} catch (Exception e) {
				throw e;
			}
		}
		this.dao.addSub(throwEx);
	}
}

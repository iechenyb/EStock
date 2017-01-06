package com.cyb.test.user;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.dao.impl.TransactionDaoImpl;

public class TranscationDaoTest extends JunitBase{
	TransactionDaoImpl service;
	String id = "";
	@Before
	public void init(){
		service = (TransactionDaoImpl) ac.getBean("transactionDao");
	}
	@Test
	public void testMerginJdbcTemplate(){
		service.updateMerginJdbcTemplate(true);
	}
	@Test
	public void updateMerginHibernate(){
		service.updateMerginHibernate(true);
	}
	@Test
	public void testMerginJdbcTemplaterollback() throws Exception{
			service.updateMerginJdbcTemplate1(true);
	}
	@Test
	public void updateMerginHibernaterollback() throws Exception{
			service.updateMerginHibernate1(true);
	}
	@Test
	public void updateMix() throws Exception{
		service.updateMix(true);
	}
}

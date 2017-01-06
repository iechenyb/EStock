package com.cyb.test.user;

import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.service.impl.TransactionServiceImpl;

public class TranscationServiceTest extends JunitBase{
	TransactionServiceImpl service;
	String id = "";
	@Before
	public void init(){
		service = (TransactionServiceImpl) ac.getBean("transactionService");
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
	@Test
	public void crossMethod() throws Exception{
		service.updateCrossMethod(true);
	}
}

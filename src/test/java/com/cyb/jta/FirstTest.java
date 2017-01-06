package com.cyb.jta;

import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.multi.datasource.UserMysqlDao1Impl;
import com.multi.datasource.UserMysqlDao2Impl;
import com.multi.datasource.UserMysqlDao3Impl;
import com.multi.datasource.UserServiceImpl;

public class FirstTest extends JunitBase{
	UserMysqlDao1Impl mysql1 ;
	UserMysqlDao2Impl mysql2;
	UserMysqlDao3Impl mysql3;
	UserServiceImpl userService;
    @Before
    public void init() {
    	 mysql1 = (UserMysqlDao1Impl) ac.getBean("mysqlDao1");
    	 mysql2 = (UserMysqlDao2Impl) ac.getBean("mysqlDao2");
    	 mysql3 = (UserMysqlDao3Impl) ac.getBean("mysqlDao3");
    	 userService = (UserServiceImpl) ac.getBean("userServiceImpl");
    	 //userService.doResetMoneyOfCards();//金额重新设置（不能释放所，不能提交事务再次）
    }

    @Test
    public void contectionTest() {
    	mysql1.query(1);
    	System.out.println("------------------------------");
        mysql2.query(1);
        System.out.println("------------------------------");
        mysql3.query(1);
        System.out.println("------------------------------");
    }
    @Test//没有回滚事务
    public void updateTest() {
    	mysql2.updateSet0(1);
    	System.out.println(1/0);
    	System.out.println("------------------------------");
        mysql1.updateAdd10(1);
    }
    @Test
    public void transaction2a2Test(){
    	userService.doTranscation2a2(true);
    }
    @Test
    public void transaction2a3Test(){
    	userService.doTranscation2a3(false);
    }
}

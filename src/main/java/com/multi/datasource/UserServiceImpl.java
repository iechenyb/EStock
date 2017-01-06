package com.multi.datasource;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *  create table ticket(id int ,num int);
	select * from ticket where id='1'
	insert into ticket values (1,10)
 * @author DHUser
 *
 */
//implements UserService
public class UserServiceImpl {  
	Log log = LogFactory.getLog(UserServiceImpl.class) ; 
    private GenericDao genericDao; 
    @Resource(name="mysqlDao1")
    UserMysqlDao1Impl mysql1 ;
    @Resource(name="mysqlDao2")
	UserMysqlDao2Impl mysql2;  
    @Resource(name="mysqlDao3")
	UserMysqlDao3Impl mysql3;  
	String ZGGSA ="中国工商银行卡A";
	String ZGGSB ="中国工商银行卡B";
	String ZGYHA ="中国银行卡A";
    public void setGenericDao(GenericDao genericDao) {  
        this.genericDao = genericDao;  
    }  
  
    public void saveUser() throws Exception {  
        String userName = "user_" + Math.round(Math.random()*10000);  
        System.out.println(userName);  
        StringBuilder sql = new StringBuilder();  
        sql.append(" insert into t_user(username, gender) values(?,?); ");  
        Object[] objs = new Object[]{userName,"1"};  
        genericDao.save("A", sql.toString(), objs);  
        sql.delete(0, sql.length());  
        sql.append(" insert into t_user(name, sex) values(?,?); ");  
        objs = new Object[]{userName,"男的"};//值超出范围  
        genericDao.save("B", sql.toString(), objs);  
    }  
    //相同连接，相同表
    public void doTranscation1a2(int id){
    	mysql1.updateAdd10(id);
    	System.out.println(mysql1.query(id));
    	System.out.println(1/0);
    	System.out.println("------------------------------");
        mysql2.updateSet0(id);
    }
    //(同一个mysql数据库) 相同表（同行转账）
    public void doTranscation2a2(boolean isException){
    	//this.doResetMoneyOfCards();
    	System.out.println(ZGGSA+"账户余额："+mysql2.query(1));
    	System.out.println(ZGGSB+"账户余额："+mysql2.query(2));
    	mysql2.updateSub10(1);
    	System.out.println(ZGYHA+"转出10块，剩余"+mysql1.query(1));
    	if(isException){
    		System.out.println(1/0);
    	}
    	System.out.println("------------------------------");
        mysql2.updateAdd10(2);
        System.out.println(ZGGSB+"转入10块，剩余"+mysql1.query(2));
    }
    //不同连接(同一个mysql数据库) 不同表(跨行转账)
    public void doTranscation2a3(boolean isException){
    	//this.doResetMoneyOfCards();
    	System.out.println(ZGGSB+"账户余额："+mysql2.query(1));
    	System.out.println(ZGYHA+"账户余额："+mysql3.query(1));
    	mysql2.updateSub10(1);
    	System.out.println(ZGGSB+"转出10块，剩余"+mysql1.query(1));
    	if(isException){
    		System.out.println(1/0);
    	}
    	System.out.println("------------------------------");
        mysql3.updateAdd10(1);
        System.out.println(ZGYHA+"转入10块，剩余"+mysql1.query(1));
    }
    public void doResetMoneyOfCards(){
    	mysql1.updateReset();mysql2.updateReset();mysql3.updateReset();
    }
}  

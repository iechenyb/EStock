package com.cyb.test.user;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.dao.impl.HibernateDaoImplExtend;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.qutoes.vo.User;


public class HibernateExTest extends JunitBase{
	Log log = LogFactory.getLog(HibernateExTest.class);
	HibernateDaoImplExtend service;
	String id = "4028905357078489015707849be30001";
	@Before
	public void init(){
		service = (HibernateDaoImplExtend) ac.getBean("hibernateDaoEx");
	}
    @Test
    public void testSaveUserNoSetId() {
    	//测试自动生成id，不用new对象时setid 
    	service.testSaveNoSetId();
    	log.info(QutoesContants.WEBPATH);//没有被初始化
    }
    @Test
    public void testSaveUser() {
    	//测试自动生成id，不用new对象时setid 
    	User user = new User();
    	user.setUsername("chenyb");
    	user.setPassword("123456");
    	service.testSaveNoSetId(user);
    	log.info(QutoesContants.WEBPATH);//没有被初始化
    }
    @Test
    public void testQueryOne(){
    	//load制定对象，并对部分属性更新
    	User user = service.testLoadOne(id);
    	System.out.println("$$$$$$$$$$");
    	System.out.println(user);
    }
    @Test
    public void testUpdate1(){
    	//load制定对象，并对部分属性更新
    	service.testUpdateLoadObject(id);
    }
    @Test
    public void testUpdate2(){
    	//new对象,手动设置id，并对部分属性更新
    	service.testUpdateNewObject(id);
    	//测试结果，会将改id的记录的其他未设置的属性值弄丢失,
    	//不推荐使用
    }
    @Test
    public void testDelete1(){
    	//load制定对象，并对部分属性更新
    	service.testDeleteLoadObject(id);
    }
    @Test
    public void testdelete2(){
    	//new对象,手动设置id，并对部分属性更新
    	service.testDeleteNewObject(id);
    	//测试结果，与testDelete1结果一致
    }
    @Test//延迟加载，打印对象信息的时候才开始执行查询sql
    public void testQuerySomeIterator(){
    	Iterator<User> it = service.getSomeUserIterator("chenyb");
    	if(it!=null){
    		while(it.hasNext()){
    			User user = it.next();
    			System.out.println(user.getId()+","+user.getUsername()+","+user.getPassword());
    		}
    	}
    }
    @Test//延迟加载，打印对象信息的时候才开始执行查询sql
    public void testQuerySomeList(){
    	List<User> it = service.getSomeUserList("chenyb");
    	if(it!=null){
    		for(int i=0;i<it.size();i++){
    			User user = it.get(i);
    			System.out.println(user.getId()+","+user.getUsername()+","+user.getPassword());
    		}
    	}
    }
    @Test//延迟加载，打印对象信息的时候才开始执行查询sql
    public void testQueryTwices(){
    	testQuerySomeList();
    	testQuerySomeList();
    }
    @Test
    public void testSQLQuery1(){
    	List<User> it = service.getUserList("name");
    	if(it!=null){
    		for(User user :it)
    		System.out.println(user.getId()+","+user.getUsername());
    	}
    }
    @Test
    public void testSQLQuery2(){
    	Map it = service.getUserMap("13938469072");
    	if(it!=null){
    		System.out.println(it);
    	}
    }
}
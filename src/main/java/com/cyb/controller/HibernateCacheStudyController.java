package com.cyb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.qutoes.vo.User;
//http://www.cnblogs.com/wean/archive/2012/05/16/2502724.html
@Controller
@RequestMapping("hibernate")
public class HibernateCacheStudyController {
	private String id = "402890535261b527015261b59cbc0001";
	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate; 
	
	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;
	public Session getSession(){
		Session session = null ;
		try {
			session = sessionFactory.getCurrentSession();
			if(session==null){
				session = sessionFactory.openSession();
			}
		} catch (Exception e) {
			return sessionFactory.openSession();
		}
		return session;
	}
	//两次sql查询
	@ResponseBody   //返回string的时候，必须加  @responseBody注解
	@RequestMapping(value = "/find2", method = RequestMethod.GET)
	public String find2() {
		Session session1 = getSession();
		User model = (User)session1.get(User.class, id);  
        System.out.println(model.getId());  
        Session session2 = getSession();
        User model2 = (User)session2.get(User.class, id);  
        System.out.println(model2.getId());  
        session1.close();  
        session2.close();
		return "haha";
	}
	//仅执行一次sql查询
	@ResponseBody   //返回string的时候，必须加  @responseBody注解
	@RequestMapping(value = "/find1", method = RequestMethod.GET)
	public String find1() {
		Session session = getSession();
		User model = (User)session.get(User.class, id);  
        System.out.println(model.getId());  
        User model2 = (User)session.get(User.class, id);  
        System.out.println(model2.getId());  
        session.close();  
		return "haha";
	}
	//测试步骤 先find1，在执行find3，看find1的session是否能够共用（结果，缓存不能够夸方法访问）
	@ResponseBody   //返回string的时候，必须加  @responseBody注解
	@RequestMapping(value = "/find3", method = RequestMethod.GET)
	public String find3() {
		Session session = getSession();
		User model = (User)session.get(User.class, id);  
        System.out.println(model.getId());  
        User model2 = (User)session.get(User.class, id);  
        System.out.println(model2.getId());  
        session.close();  
		return "haha";
	}
	
//	查询测试, 看看命中几次
	@ResponseBody   //返回string的时候，必须加  @responseBody注解
	@RequestMapping(value = "/find4", method = RequestMethod.GET)
	public String find4() {
		query();
		final SessionFactory sf = this.getSessionFactory();
		final Statistics s = sf.getStatistics();  
        System.out.println(s); //打印所有信息 监测SessionFactory  
        System.out.println("---------------");  
        System.out.println("放入:" + s.getSecondLevelCachePutCount()); //打印缓存的信息  
        System.out.println("命中:" + s.getSecondLevelCacheHitCount());  
        System.out.println("丢失:" + s.getSecondLevelCacheMissCount());  
		return "haha";
	}
	
	public  void query()  
	    {  
	        Session session = null;
	  
	        for (int i = 0; i < 10; i++)  
	        {  
	            session = sessionFactory.openSession();   
	            final User p1 = (User) session.get(User.class, id);  
//	            session.close();  
	            System.out.println(p1.getUsername());  
	            session.close();
	        }  
	       
	    }
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}  
}

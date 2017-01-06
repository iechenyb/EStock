package com.cyb.database;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cyb.qutoes.constant.SpringUtil;
import com.cyb.qutoes.vo.Idea;
import com.cyb.qutoes.vo.Stock;
import com.cyb.qutoes.vo.User;
import com.cyb.utils.DateUtil;
import com.cyb.utils.UUIDUtils;
@Repository
public class HibernateStudy {
	@Resource(name="sessionFactory")
	public SessionFactory sessionFactory ;
	public  static void studyQuery(){
		SessionFactory factory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = factory.openSession();
		 String sql=" from Stock ";
		   Query query = session.createQuery(sql);
		   List<Stock> list=query.list();
		   Stock stock=null;
		    for(int i=0;i<list.size();i++)
		   {
		    	stock = (Stock)list.get(i);
		        System.out.println("code:"+stock.getCode());
		   }
	}
	public  static void studyQueryaddEntity(){
		SessionFactory factory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = factory.openSession();
		String sql="select * from Stock where code_ = ?";
		Query query = session.createSQLQuery(sql).addEntity(Stock.class).setString(0, "sh600868");
		 query.setFirstResult(0);//从第一条记录开始
	     query.setMaxResults(4);//取出四条记录
		List<Stock> list = (List<Stock>)query.list();
		System.out.println("studyQueryaddEntity:"+list);
	 }
	public  static void studySQLQuery(){
		/**
		 * 错误的写法
		 */
		   String sql="select * from Stock limit :num1,:size";
		   SessionFactory factory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		   Session session = factory.openSession();
		   Query query = session.createSQLQuery(sql);
		   query.setParameter("num1",1);
		   query.setParameter("size", 5);
		   List list=query.list();
		   Object[] question=null;
		    for(int i=0;i<list.size();i++)
		   {
		    question = (Object[]) list.get(i);//[0e4045197fac43d38ee3adff6e59d35c, 166105, sh, 信达增利, 2015-12-24 12:15:24.579]
		    System.out.print("ID"+question[0]);
		   }
	}
	public static void studyQuerySqlFromXml(){
		SessionFactory factory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = factory.openSession();
		Query query = session.getNamedQuery("getStocks");
		query.setParameter("code_param", "600868");
		List<Stock> lst = query.list();
		for(Stock stock:lst){
			System.out.println("stock.name="+stock.getName());
		}
	}
	public  static void studySaveObject(){
		SessionFactory factory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setId(UUIDUtils.getUUID());
		user.setEmail("123@sina.com");
		user.setRegisterTime(DateUtil.date2long14(new Date()));
		session.save(user);
		User user_ = new User();
		user_.setId("40289053525d812001525d813e9e0001");
		session.delete(user_);
		Idea idea = new Idea();
		idea.setId(UUIDUtils.getUUID());
		idea.setEmail("haha@sina.com");
		idea.setMessage("网站做的还不错！");
		session.save(idea);
		tx.commit();
		System.out.println("用户注册成功！");
	}
}
	

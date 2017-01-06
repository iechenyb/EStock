package com.cyb.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.qutoes.vo.User;

@Repository("hibernateDaoEx")
@Transactional
public class HibernateDaoImplExtend  extends BaseDaoImpl{
	@Resource(name="sessionFactory") 
	public void setSF(SessionFactory sessionFactory){ 
		super.setSessionFactory(sessionFactory); 
	} 
	Log log = LogFactory.getLog(HibernateDaoImplExtend.class);
	public void testSaveNoSetId(){
		Session session = this.getSession();
		User user = new User();
		user.setPassword("sdfsdf");
		user.setUsername("username");
		session.save(user);
		log.info(user.getId()+","+user.getUsername());
		User usertmp = (User) session.load(User.class, user.getId());
		log.info(usertmp);
		/*session.getTransaction().commit();
		session.flush();
		session.close();*/
		
	}
	
	public void testSaveNoSetId(User user){
		Session session = getSession();
		//session.beginTransaction();
		session.save(user);
		log.info(user.getId()+","+user.getUsername());
		User usertmp = (User) session.load(User.class, user.getId());
		log.info(usertmp);  
		//session.flush();
		/*session.getTransaction().commit();
		session.close();*/
	}
	public void testUpdateLoadObject(String id){
		Session session = getSession();
		User user = (User) session.load(User.class, id);
		user.setPassword("chenyb1111");
		user.setUsername("username1111");
		session.update(user);
	}
	public void testUpdateNewObject(String id){
		Session session = getSession();
		User user = new User();
		user.setId(id);
		user.setEmail("hibernateseted@qq.com");
		session.update(user);
	}
	public void testDeleteLoadObject(String id){
		Session session = getSession();
		User user = (User) session.load(User.class, id);
		session.delete(user);
	}
	public void testDeleteNewObject(String id){
		Session session = getSession();
		User user = new User();
		user.setId(id);
		session.delete(user);
	}
	public User testLoadOne(String id){
		Session session = getSession();
		User user =(User) session.load(User.class, id);
		return user;
	}
	
	public Iterator<User> getSomeUserIterator(String name)throws HibernateException
	{
		Session session = getSession();
		String condition = "";
		if(name!=null){
			condition = " where c.username like :name";
		}
		String queryString = " select c from User as c "+condition; 
		Query query = session.createQuery(queryString);	
		query.setCacheable(true);  
		if(name!=null){
			query.setString("name", "%"+name+"%");
		}
		Iterator<User> it= query.iterate();
		return it;
	} 
	public List<User> getSomeUserList(String name)throws HibernateException
	{
		Session session = getSession();
		String condition = "";
		if(name!=null){
			condition = " where c.username like :name";
		}
		String queryString = " select c from User as c "+condition; 
		Query query = session.createQuery(queryString);	
		query.setCacheable(true);  
		if(name!=null){
			query.setString("name", "%"+name+"%");
		}
		List<User> it= query.list();
		return it;
	} 
	
	public List<User> getUserList(String name){
		String condition = "";
		if(name!=null){
			condition = "where username like'%"+name+"%' ";
		}
		Session session = getSession();
		Query query = session.createSQLQuery("select * from usr t "+condition ) .addEntity(User.class);//返回对象 
		return query.list();
	}
	public Map getUserMap(String name){
		Session session = getSession();
		Query query = session.createSQLQuery("select * from usr t where username ='"+name+"' ")
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//返回一个map,KEY:为DB中名称一致（大小写一致）  
		//遍历list时就可以
		Map map = (Map)query.list().get(0);
		return map;
	}
}

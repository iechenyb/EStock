package com.cyb.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cyb.qutoes.vo.User;

@Repository("hibernateDao")
public class HibernateDaoImpl extends BaseDaoImpl {
	Log log = LogFactory.getLog(HibernateDaoImpl.class);
	public void testSaveNoSetId(){
		Session session = getSession();
		User user = new User();
		user.setPassword("sdfsdf");
		user.setUsername("username");
		session.save(user);
		log.info(user.getId()+","+user.getUsername());
		User usertmp = (User) session.load(User.class, user.getId());
		log.info(usertmp);  
	}
	public void testSaveNoSetId(User user){
		Session session = getSession();
		session.save(user);
		log.info(user.getId()+","+user.getUsername());
		User usertmp = (User) session.load(User.class, user.getId());
		log.info(usertmp);  
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
	public Iterator<User> getSomeUser(String name)throws HibernateException
	{
		Session session = getSession();
		String condition = "";
		if(name!=null){
			condition = " where c.username like :name";
		}
		String queryString = " select c from User as c "+condition; 
		Query query = session.createQuery(queryString);	
		//query.setCacheable(true);  
		if(name!=null){
			query.setString("name", "%"+name+"%");
		}
		Iterator<User> it= query.iterate();
		return it;
	} 
	public List<User> getUserList(String name){
		String condition = "";
		if(name!=null){
			condition = " where username like'%"+name+"%' ";
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
    public void getLoadUser(String id){
    	Session session = getSession();
    	User user = (User) session.load(User.class, id);
    	System.out.println(user.getId());
	}
}

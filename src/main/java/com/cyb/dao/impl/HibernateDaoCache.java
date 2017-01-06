package com.cyb.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cyb.qutoes.vo.User;
import com.cyb.utils.UUIDUtils;

@Repository("hibernateDaoCache")
@Transactional
public class HibernateDaoCache extends BaseDaoImpl{
	@Resource(name="sessionFactory") 
	public void setSF(SessionFactory sessionFactory){ 
		super.setSessionFactory(sessionFactory); 
	} 
	Log log = LogFactory.getLog(HibernateDaoImplExtend.class);
    public void testFirstCache(String id){
    	Session  session = this.getSession();
    	Transaction tx = null;
    	 /*开启一个事务*/
        tx = session.beginTransaction();
        /*从数据库中获取id="402881e534fa5a440134fa5a45340002"的Customer对象*/
        User customer1 = (User)session.get(User.class, id);
        User customer3 = (User)session.get(User.class, id);
        log.info("customer.getUsername is "+customer1.getUsername());
        /*事务提交*/
        tx.commit();
         
        log.info("-------------------------------------");
         
        /*开启一个新事务*/
        tx = session.beginTransaction();
        /*从数据库中获取id="402881e534fa5a440134fa5a45340002"的Customer对象*/
        User customer2 = (User)session.get(User.class, id);
        customer2.setUsername("222222");
        session.update(customer2);
        User customer4 = (User)session.load(User.class, id);
        log.info("customer2.getUsername is "+customer2.getUsername());
        /*事务提交*/
        tx.commit();
         
        log.info("-------------------------------------");
         
        /*比较两个get()方法获取的对象是否是同一个对象*/
        log.info("user1get="+customer1);
        log.info("user2get="+customer2);
        log.info("user3load="+customer3);
        log.info("user4load="+customer4);
        log.info("customer1 == customer2 result is "+(customer1==customer2));
        
    }
    public void testFirstCache1(String id){
    	Session  session = this.getSession();
        /*从数据库中获取id="402881e534fa5a440134fa5a45340002"的Customer对象*/
        User customer1 = (User)session.get(User.class, id);
        User customer3 = (User)session.load(User.class, id);
        log.info("customer.getUsername is "+customer1.getUsername());
        log.info("-------------------------------------");
        /*从数据库中获取id="402881e534fa5a440134fa5a45340002"的Customer对象*/
        User customer2 = (User)session.get(User.class, id);
        customer2.setUsername("33333");
        session.update(customer2);
        log.info("customer2.getUsername is "+customer2.getUsername());   
        User customer4 = (User)session.load(User.class, id);
        log.info("-------------------------------------");
        /*比较两个get()方法获取的对象是否是同一个对象*/
        log.info("user1get="+customer1);
        log.info("user2get="+customer2);
        log.info("user3load="+customer3);
        log.info("user4load="+customer4);
        log.info("customer1 == customer2 result is "+(customer1==customer2));
        //session.flush();
    }
    public void testSecondCacheClear(String id){
    	//evict(Class arg0, Serializable arg1)将某个类的指定ID的持久化对象从二级缓存中清除,释放对象所占用的资源。
    	getSessionFactory().evict(User.class, id);  
    	//evict(Class arg0)  将指定类的所有持久化对象从二级缓存中清除,释放其占用的内存资源。
    	getSessionFactory().evict(User.class);  
    	//evictCollection(String arg0)  将指定类的所有持久化对象的指定集合从二级缓存中清除,释放其占用的内存资源。
    	//getSessionFactory().evictCollection("User.list");  
    	Session  session1 = this.getSession();
    	
    	User customer1 = (User)session1.get(User.class, id);
    	customer1.setEmail(UUIDUtils.getUUID()+"@sina.com");
    	log.info("id="+id+","+customer1.getEmail());
    	session1.update(customer1);//测试二级缓存只读属性
    	//session1.flush();
    	
    	log.info("customer1 = "+customer1);
    	//session1.close();
    	Session  session2 = this.getSession();
    	User customer2 = (User)session2.get(User.class, id);
    	log.info("customer2 = "+customer2); //
    	log.info("两个对象相同，则说明二级缓存设置成功！");
    	
    }
    public void testNadd1Question(String id){
    	
    	Session  session = this.getSession();
    	User customer1 = (User)session.get(User.class, id);
    	log.info("customer1 = "+customer1);
    	/**
         * 此时会发出一条sql，将30个学生全部查询出来
         */
    	log.info("-----------直接查询出所有对象-----------------");
        @SuppressWarnings("unchecked")
		List<User> ls = (List<User>)session.createQuery("from User")
                            .setFirstResult(0).setMaxResults(30).list();
        Iterator<User> stus = ls.iterator();
        for(;stus.hasNext();)
        {
            User stu0 = (User)stus.next();
            log.info(stu0);
        }
        log.info("-----------先查id，然后根据id查询对象(使用了缓存，不用sql查询)-----------------");
        /**
         * 如果使用iterator方法返回列表，对于hibernate而言，它仅仅只是发出取id列表的sql
         * 在查询相应的具体的某个学生信息时，会发出相应的SQL去取学生信息
         * 这就是典型的N+1问题
         * 存在iterator的原因是，有可能会在一个session中查询两次数据，如果使用list每一次都会把所有的对象查询上来
         * 而是要iterator仅仅只会查询id，此时所有的对象已经存储在一级缓存(session的缓存)中，可以直接获取
         */
        @SuppressWarnings("unchecked")
		Iterator<User> stus1 = (Iterator<User>)session.createQuery("from User")
                            .setFirstResult(0).setMaxResults(30).iterate();
        for(;stus1.hasNext();)
        {
            User stu1 = (User)stus1.next();
            log.info(stu1);//延迟加载
        }
        
        @SuppressWarnings("unchecked")
		Iterator<User> stus2 = (Iterator<User>)session.createQuery("from User")
                            .setFirstResult(0).setMaxResults(30).iterate();
        for(;stus2.hasNext();)
        {
            User stu2 = (User)stus2.next();
            log.info(stu2);//延迟加载
        }
        User customer2 = (User)session.get(User.class, id);//从缓存里边找，不发送sql查询
        log.info("customer2 = "+customer2);
        log.info("请注意查看，对象的id，后边查询相同的对象id都一样，而不是重新分配一块内存！");
    }
 public void testNadd1Question1(String id){
    	
    	Session  session = this.getSession();
    	User customer1 = (User)session.get(User.class, id);
    	log.info("customer1 = "+customer1);
    	/**
         * 此时会发出一条sql，将30个学生全部查询出来
         */
    	/*log.info("-----------直接查询出所有对象-----------------");
        @SuppressWarnings("unchecked")
		List<User> ls = (List<User>)session.createQuery("from User")
                            .setFirstResult(0).setMaxResults(30).list();
        Iterator<User> stus = ls.iterator();
        for(;stus.hasNext();)
        {
            User stu0 = (User)stus.next();
            log.info(stu0);
        }*/
        log.info("-----------先查id，然后根据id查询对象，除了id，其他的对象都需要发送sql进行查询-----------------");
        /**
         * 如果使用iterator方法返回列表，对于hibernate而言，它仅仅只是发出取id列表的sql
         * 在查询相应的具体的某个学生信息时，会发出相应的SQL去取学生信息
         * 这就是典型的N+1问题
         * 存在iterator的原因是，有可能会在一个session中查询两次数据，如果使用list每一次都会把所有的对象查询上来
         * 而是要iterator仅仅只会查询id，此时所有的对象已经存储在一级缓存(session的缓存)中，可以直接获取
         */
        @SuppressWarnings("unchecked")
		Iterator<User> stus1 = (Iterator<User>)session.createQuery("from User")
                            .setFirstResult(0).setMaxResults(30).iterate();
        for(;stus1.hasNext();)
        {
        	System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            User stu1 = (User)stus1.next();//发送sql进行查询
            System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
            log.info(stu1);//延迟加载
        }
        
        log.info("-----------根据id从缓存中获取数据，不进行sql查询-----------------");
        @SuppressWarnings("unchecked")
		Iterator<User> stus2 = (Iterator<User>)session.createQuery("from User")
                            .setFirstResult(0).setMaxResults(30).iterate();
        for(;stus2.hasNext();)
        {
            User stu2 = (User)stus2.next();//不再发送sql进行查询
            log.info(stu2);//延迟加载
        }
        User customer2 = (User)session.get(User.class, id);//从缓存里边找，不发送sql查询
        log.info("customer2 = "+customer2);
        log.info("请注意查看，对象的id，后边查询相同的对象id都一样，而不是重新分配一块内存！");
    }
 
   public void testQueryCache(){
	    String queryString="";int pageSize=10;int page=1;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(queryString); 
		query.setFirstResult((page-1)*pageSize); 
		query.setMaxResults(pageSize); 
		List<Object> list= (List<Object>)query.list();
		session.close();
   }
}

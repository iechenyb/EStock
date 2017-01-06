package com.cyb.test.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;

import com.cyb.dao.impl.HibernateDaoCache;
import com.cyb.test.user.HibernateTest;

public class HibernateCacheTest extends JunitBase{
	Log log = LogFactory.getLog(HibernateTest.class);
	HibernateDaoCache service;
	String id = "";
	@Before
	public void init(){
		service = (HibernateDaoCache) ac.getBean("hibernateDaoCache");
	}
	@Test
	public void testHibernateFirstCache(){
		//hibernate的一级缓存是session级别的，所以如果session关闭后，缓存就没了，此时就会再次发sql去查数据库
		//自动更新需要在hbm上配置dynamic-update="true"或者调用flush方法
		//service.testFirstCache("4028905354e67f350154e682c3800001");
		service.testFirstCache1("4028905354e67f350154e682c3800001");
		System.out.println("对象获取相同，则表示存在一级缓存（sql实际上也仅仅执行了一次，同一个session内！）！");
	}
	@Test
	public void testHibernatenFirstCacheNand1(){
		service.testNadd1Question("4028905354e67f350154e682c3800001");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		service.testNadd1Question1("4028905354e67f350154e682c3800001");
		System.out.println("两个service查询的对象id分别不同，使用的是session级别的缓存，session关闭后，数据需要重新查询！");
	}
	@Test
	public void testHibernateSecondCache(){
		//hibernate的二级缓存了，也就是sessionFactory级别的缓存
		/**
		 * 二级缓存的使用策略一般有这几种：read-only、nonstrict-read-write、read-write、transactional。
		 * 注意：我们通常使用二级缓存都是将其配置成 read-only ，即我们应当在那些不需要进行修改的实体类上使用二级缓存，
		 * 否则如果对缓存进行读写的话，性能会变差，这样设置缓存就失去了意义。
		 */
		/**
         * 注意：二级缓存中缓存的仅仅是对象，而下面这里只保存了姓名和性别两个字段，所以 不会被加载到二级缓存里面
         
        List<Object[]> ls = (List<Object[]>) session
                .createQuery("select stu.name, stu.sex from Student stu")
                .setFirstResult(0).setMaxResults(30).list();
       */
		service.testSecondCacheClear("4028905354e67f350154e682c3800001");
		service.testSecondCacheClear("4028905354e67f350154e682c3800001");
	}
}
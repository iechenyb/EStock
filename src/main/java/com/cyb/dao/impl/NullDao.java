package com.cyb.dao.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class NullDao {
	@Transactional(propagation = Propagation.REQUIRED)  
	public void test1() {  
	}  
	// 声明方法不需要事务，如果方法没有关联到一个事务，容器不会为它开启事物。  
	// 如果方法在一个事物中被调用，该事物会被挂起，在方法调用结束后，原先的  
	// 事物便会恢复执行。  
	@Transactional(propagation = Propagation.NOT_SUPPORTED)  
	public void test2() {  
	}  
	// 表明不管是否存在事物，业务方法总会为自己发起一个新事物。  
	// 如果方法已经运行在一个事物中，则原有事物会被挂起，  
	// 新的事物会被创建，直到方法执行结束，新事物才算结束，  
	// 原先的事务才会被恢复执行。  
	@Transactional(propagation = Propagation.REQUIRES_NEW)  
	public void test3() {  
	}  
	// 该属性指定业务方法只能在一个已经存在的事物中执行，  
	// 业务方法不能发起自己的事物，如果业务方法在没有事物的环境  
	// 下调用，容器就会抛出异常。  
	@Transactional(propagation = Propagation.MANDATORY)  
	public void test4() {  
	}  
	// 这个事物属性表明，如果业务方法在某个事务范围内被调用，则方法成为该事物的一部分，  
	// 如果业务方法在事务范围外被调用，则方法在没有事物的环境下执行。  
	@Transactional(propagation = Propagation.SUPPORTS)  
	public void test5() {  
	}  
	// 指定业务方法绝对不能在事物范围内执行。如果业务方法在某个事物中执行，  
	// 容器会抛出异常，只有业务方法没有关联到任何事物，才能正常执行。  
	@Transactional(propagation = Propagation.NEVER)  
	public void test6() {  
	}  
	// 如果一个活动的事物存在，则运行在一个嵌套的事物中，如果没有活动事物，  
	// 则按REQUIRED属性执行，它使用了一个单独的事物，这个事物拥有多个回滚的保存点，  
	// 内部事务的回滚不会对外事物造成影响，它只对DataSourceTransactionManager  
	// 事务管理器起效。  
	@Transactional(propagation = Propagation.NESTED)  
	public void test7() {  
	}  
	@Transactional(isolation = Isolation.DEFAULT)  
	public void test8() {  
	}  
	// 读已提交数据（会出现不可重复读和幻读）  
	@Transactional(isolation = Isolation.READ_COMMITTED)  
	public void test9() {  
	}  
	// 读未提交数据（会出现脏读、不可重复读和幻读）  
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)  
	public void test10() {  
	}  
	// 可重复读（会出现幻读）  
	@Transactional(isolation = Isolation.REPEATABLE_READ)  
	public void test11() {  
	}  
	// 串行化  
	@Transactional(isolation = Isolation.SERIALIZABLE)  
	public void test12() {  
	}  
	// 抛出Exception异常时，记录回滚  
	@Transactional(rollbackFor = Exception.class)  
	public void test13() throws Exception {  
	}  
}

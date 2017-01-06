package com.cyb.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;

import com.cyb.base.JunitBase;
import com.spring.aop.Sleepable;

/**
 * 模拟spring的依赖注入
 * @author DHUser
 *
 */
public class AOPTest extends JunitBase{
	Log log = LogFactory.getLog(AOPTest.class); 
	@Before
	public void init(){
		
	}
    @org.junit.Test
    public void testSpringAOP() {
    	 Sleepable sleeper = (Sleepable) ac.getBean("human");  
         sleeper.sleep();  
         log.info("-------------------");
         sleeper.sleep("chenyb1");  
         log.info("-------------------");
         sleeper.sleep1("chenyb1");  
    }
}

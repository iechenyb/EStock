package com.cyb.test.user;

import org.junit.Before;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.service.impl.HibernateServiceImpl;

public class FirstTest extends JunitBase{
	
    @Before
    public void init() {
        System.out.println("display student: "+"#" );
    }

    @Test
    public void userBeanTest() {
    	HibernateServiceImpl service = (HibernateServiceImpl) ac.getBean("hibernateService");
        System.out.println("application display student: " + service.toString());
        service.testSaveNoSetId();
    }

}

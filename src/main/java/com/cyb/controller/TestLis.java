package com.cyb.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.cyb.service.UserService;

/**
 * Application Lifecycle Listener implementation class TestLis
 *
 */
public class TestLis implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TestLis() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		/*System.out.println("contextInitialized");
//		H2DBUtil.register();
//		System.out.println(H2DBUtil.getConnection());
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		System.out.println("userService = "+wac.getBean("userService"));
		UserService dao = (UserService)wac.getBean("userService");
		dao.getList();*/
		JavaMailSenderImpl x;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}

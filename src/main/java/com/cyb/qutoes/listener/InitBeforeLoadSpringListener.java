package com.cyb.qutoes.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cyb.h2.H2Manager;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.utils.PropertyUtil;

public class InitBeforeLoadSpringListener implements ServletContextListener {
    public InitBeforeLoadSpringListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public void contextInitialized(ServletContextEvent sce) {
    	try {
    		String webPath = sce.getServletContext().getRealPath("/");
    		if(webPath.charAt(webPath.length()-1)!=File.separator.charAt(0)){
    			webPath = webPath + File.separator;
    		}
    		QutoesContants.WEBPATH = webPath;
			PropertyUtil.init("App");
			H2Manager.initServer();
			H2Manager.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
	
}

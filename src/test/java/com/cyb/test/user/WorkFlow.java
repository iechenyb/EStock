package com.cyb.test.user;

import java.io.File;
import java.io.InputStream;
import java.util.Set;

import javax.el.FunctionMapper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jbpm.api.Execution;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.pvm.internal.id.PropertyImpl;
import org.junit.Test;

import com.cyb.base.JunitBase;
import com.cyb.qutoes.constant.QutoesContants;
import com.cyb.utils.FileUtils;
//http://ashaochangfu.blog.163.com/blog/static/104251730201391112236635/
public class WorkFlow extends JunitBase{
	public void deployFile(){
		String path="D:\\apache-tomcat-7.0.52\\EStockSvn\\WEB-INF\\classes\\process.jpdl.xml";
		repositoryService.createDeployment().addResourceFromFile(new File(path)).deploy();
	}
	public void deployFromClasspath(){
		String path="process.jpdl.xml";
		String path1="com/cyb/jbpm/process.jpdl.xml";
		repositoryService.createDeployment().addResourceFromClasspath(path).deploy();
	}
    @Test
    public void startProcessEngine() {
    	deployFromClasspath();
//    	 SessionFactory sessionFactory = new Configuration().configure("classpath:jbpm.hibernate.cfg.xml").buildSessionFactory(); 
//    	 ProcessEngine processEngine = new  org.jbpm.api.Configuration().buildProcessEngine(); 
  	  ProcessInstance processInstance = executionService.startProcessInstanceByKey("AsyncActivity");
  	    String processInstanceId = processInstance.getId();
  	    System.out.println("processInstance.getid:"+processInstanceId);
  	  Execution executionInA = processInstance.findActiveExecutionIn("a");

  	    processInstance = executionService.signalExecutionById(executionInA.getId());
  	    Execution executionInB = processInstance.findActiveExecutionIn("b");
  	    processInstance = executionService.signalExecutionById(executionInB.getId());
  	    Execution executionInC = processInstance.findActiveExecutionIn("c");
//  		 String id=request.getParameter("id");
//  		 ProcessInstance processInstance=executionService.findProcessInstanceById(id);
  		 String processDefinitionId=processInstance.getProcessDefinitionId();
  		 ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
  		 InputStream inputStream=repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), "process.png");
  		 String path = QutoesContants.WEBPATH+"process.png";
  		 FileUtils.copyFileByStream(inputStream, path);
//  		 http://blog.csdn.net/clj198606061111/article/details/8507515
//  		    String id=request.getParameter("id");
//  		    ProcessEngine processEngine=Configuration.getProcessEngine(); 
//  		    RepositoryService repositoryService = processEngine.getRepositoryService();
//  		    ExecutionService executionService=processEngine.getExecutionService();
//  		    ProcessInstance processInstance=executionService.findProcessInstanceById(id);
  		    Set<String> activityName=processInstance.findActiveActivityNames();
  		    ActivityCoordinates ac=repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(), activityName.iterator().next());
  		    System.out.println("x = "+ac.getX());
  		    System.out.println("y = "+ac.getY());
  		    System.out.println("x = "+ac.getWidth());
  		    System.out.println("x = "+ac.getHeight());
    
    }


}

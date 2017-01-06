package com.cyb.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@Component
//@Aspect
public class ServiceAop {
	Logger log = Logger.getLogger(ServiceAop.class);
	public ServiceAop() {
		log.info("AOP LogServcie created!");
	}

	//@Pointcut("@annotation(com.cyb.dao.CommonInterface)")
	@Pointcut("execution(* com.cyb.*.dao.*.*(..))")
	public void methodPointcut() {
		log.info("methodPointcut()");
	}

	@Around("methodPointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object object = null;
		if(RequestContextHolder.getRequestAttributes()!=null){
			try {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
						.getRequestAttributes()).getRequest();
				Object[] method_param = null;
				method_param = point.getArgs(); // 获取方法参数
				for (int i = 0; i < method_param.length; i++) {
					//log.info("method_param [" + i + "] = "+ method_param[i]);
				}
				String methodName = point.getSignature().getName();// 获取方法名
				String packages = point.getThis().getClass().getName();
				if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
					try {
						packages = packages.substring(0, packages.indexOf("$$"));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
	//			log.info(packages + "," + methodName);// 类包。方法名
				String queryStr = request.getQueryString();
	//			log.info("queryStr = " + queryStr);// 请求参数：
																// post请求获取不到querystring
				String queryUri = request.getRequestURI();
				StringBuffer queryUrl = request.getRequestURL();
	//			log.info("reqUri = " + queryUri + ", requrl="		+ queryUrl.toString());// 请求参数： post请求获取不到querystring
				Enumeration<String> names = request.getParameterNames();// 通用的获取请求参数名方法
				while (names.hasMoreElements()) {
					String name = names.nextElement();
	//				log.info("request.paramName = " + name + ", value = "					+ request.getParameter(name));
				}
				//log.debug("---------------执行请求"+packages + "." + methodName+"的方法开始-------------");
				long s = System.currentTimeMillis();
				object = point.proceed(); // 执行方法本身
				long e = System.currentTimeMillis();
				//log.debug("---------------执行请求"+packages + "." + methodName+"的方法结束-------------");
				// throw new Exception();
	//			double x = 1 / 0;
				log.info("执行请求"+packages + "." + methodName+" 执行时间 ："+(e-s)/1000+"s."+(e-s)%1000+"ms");
			} catch (Exception e) {
				// 异常处理记录日志..log.error(e);
				throw e;
			} finally {
				object = point.proceed(); // 执行方法本身
			}
			return object;
		}else{
			return object;
		}
     //		log.info("---------------切面拦截结束-------------");
	}

	// 方法运行出现异常时调用
	@AfterThrowing(pointcut = "execution(* com.cyb.*.dao.*.*(..))", throwing = "ex")
	public void afterThrowing(Exception ex) {
//		log.info("---------------切面拦截出现异常-------------");
		log.info(ex);
		ex.printStackTrace();
//		log.info("---------------切面拦截异常处理结束-------------");
	}
	
	@Before("methodPointcut()")
	public void before() {
//		log.info("---------------before start-------------");
//		log.info("---------------before inner-------------");
//		log.info("---------------before end -------------");
	}
	
	@AfterReturning("methodPointcut()")  
    public void doAfter(){  
//        log.info("---------------doAfter后置通知 start-------------");  
//        log.info("---------------doAfter inner-------------");  
//        log.info("---------------doAfter后置通知 end-------------");  
    }  
      
	@After("methodPointcut()")  
    public void after(){  
//        log.info("---------------after最终通知 start-------------");
//        log.info("---------------after inner-------------");  
//        log.info("---------------after最终通知 end-------------");  
    } 
	/*
	 * 程序运行结果，对所有controller中的方法进行拦截， 
	---------------切面拦截开始-------------
	method_param [0] = org.apache.catalina.connector.RequestFacade@910797
	method_param [1] = org.apache.catalina.connector.ResponseFacade@7a2c44
	com.cyb.controller.StudyParamsController,getReq
	queryStr = name=chenyb&age=25
	reqUri = /springmvc/para/get/req, requrl=http://localhost:8080/springmvc/para/get/req
	request.paramName = name, value = chenyb
	request.paramName = age, value = 25
	---------------执行请求的方法开始-------------
	---------------before start-------------
	---------------before inner-------------
	---------------before end -------------
	org.apache.catalina.connector.RequestFacade@910797,name=chenyb&age=25
	---------------执行请求的方法结束-------------
	---------------切面拦截结束-------------
	---------------after最终通知 start-------------
	---------------after inner-------------
	---------------after最终通知 end-------------
	---------------doAfter后置通知 start-------------
	---------------doAfter inner-------------
	---------------doAfter后置通知 end-------------
 */
}

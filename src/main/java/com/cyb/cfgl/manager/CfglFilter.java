package com.cyb.cfgl.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyb.cfgl.contanst.CfglConstants;

public class CfglFilter implements Filter{
	 static Logger log = LoggerFactory.getLogger(Filter.class);
	 FilterConfig config ;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		 String loginUrl = config.getInitParameter("cfglLoginUrl");
		 HttpServletRequest request = (HttpServletRequest) req;
		 HttpServletResponse response = (HttpServletResponse) res;
		 HttpSession session = request.getSession();
		 String path = ( request).getContextPath();
		 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		 log.info("应用basepath:"+basePath);
		 Object username = session.getAttribute("username");
		 //不对登陆页面进行过滤
		 //String url = request.getRequestURL().toString();//http://localhost:8085/EStock/cfgl/login.jsp
		 //String uri = request.getRequestURI().toString();//  /EStock/cfgl/login.jsp
		 String urlContext = request.getServletPath().toString();// /cfgl/login.jsp
		  if(username==null||"".equals(username)){ //登陆页面死循环 
			  if (request.getHeader("x-requested-with") != null 
				  && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
				  if(CfglConstants.excludeUrl.containsKey(urlContext)){///cfgl/login.zc
					  chain.doFilter(req, res); 
				      return ;
                  }else{
                	  //如果是ajax请求响应头会有，x-requested-with  
	                  log.info("发生ajax请求...");//返回指定字符串
	                  Map<String, String> data = new HashMap<String, String>();
	                  data.put("login", "false");
	                  response.getWriter().write(JSONArray.fromObject(data).toString()); 
	                  return ;
                  }
			  }else{
				  response.setContentType("text/html; charset=UTF-8");
				  response.setCharacterEncoding("UTF-8");
				  PrintWriter out = response.getWriter(); 
				  String dispatcher = "window.top.location.href = '"+basePath+loginUrl+"';"; 
				  //需要登录
			      out.print("<script type='text/javascript'> alert('您还未登陆，请重新登录！');");
				  out.print(dispatcher+"</script>");
				  out.close();
				 return;
			 }
		   }else{
			   chain.doFilter(req, res);
		 }
		  /**
		   *  chain.doFilter(req, res); //正常进入请求
		   *  doFilter(req, res,chain);//出现死循环
		   *  chain.doFilter()，执行该方法之前，即对用户请求进行预处理；执行该方法之后，即对服务器响应进行后处理。
		   *  对所有的请求都执行chain.doFilter (request,reponse)方法，当Filter对请求过滤后，依然将请求发送到目的地址。
		   *  如果权限不够，直接调用重定向即可！
		   */
	}

	@Override
	public void destroy() {
		log.info("应用关闭！");
	}

}

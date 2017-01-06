package com.cyb.phone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyb.phone.contants.EContants;
import com.cyb.phone.service.PhoneLoginServiceImpl;
import com.cyb.phone.vo.User;
import com.cyb.utils.JsonUtils;
import com.cyb.utils.MD5Encoder;

/**
 * Servlet implementation class PhoneWebServiceImpl
 */
public class PhoneWebServiceImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PhoneWebServiceImpl() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String method = request.getParameter("cmd");
	    System.out.println("客户端请求方法："+method);
	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String,Object> ret = new HashMap<String,Object>();
		if(EContants.LOGIN.equals(method)){//登陆
			 //ServerInterFace?cmd=login&username=13938469072&password=1
			 String username = request.getParameter("username");
			 String password = request.getParameter("password");
			 PhoneLoginServiceImpl loginService = new PhoneLoginServiceImpl();
			 User user = new User();
			 user.setUsername(username);
			 user.setPassword(MD5Encoder.encode(password));
			 System.out.println("请求参数："+user);
			 boolean canLogin = loginService.checkLogin(user);
			 ret.put("canLogin", canLogin);
		}
		System.out.println("服务端返回数据："+JsonUtils.map2json(ret));
		out.print(JsonUtils.map2json(ret));
		out.print("halll");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

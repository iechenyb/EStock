package com.cyb.cfgl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.cfgl.service.CfglService;
import com.cyb.qutoes.vo.User;
import com.cyb.service.UserService;

@Controller
@RequestMapping("cfgl")
public class CfglController {
 @Resource(name="cfglService")
 CfglService cfglService ;
 @Resource(name="userService")
 UserService userService ;
 @RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,HttpSession session) throws IOException {
		  ModelAndView view = new ModelAndView();
		  String username = (String) session.getAttribute("cfgl_username");
		  session.removeAttribute("cfgl_user");
		  session.removeAttribute("cfgl_username");
		  view.setViewName("cfjh/index");
		  System.out.println("用户 "+username+" 成功退出！");
		  return view;
	}
    @RequestMapping(value="/login")
	@ResponseBody
	public JSONArray login(String username,String password,String yzm,HttpServletRequest request,HttpSession session) throws IOException {
    	 String yzmSession = (String) request.getSession().getAttribute("nlcheckcode"); 
    	 System.out.println("session yzm:"+yzmSession+",input yzm:"+yzm);
    	 Map<String, String> view = new HashMap<String, String>();
		  view.put("username", username);
		  view.put("password", password);
		  User user = new User();
		  user.setUsername(username);
		  user.setPassword(password);
		  session.setAttribute("user", user);
		  session.setAttribute("username", user.getUsername());
		  if(!yzmSession.equals(yzm.toUpperCase())){
			  view.put("msg","验证码不正确！");
			  view.put("ret","0");
		  }else{ }
			  if(userService.queryUserByUserNamePassword(user)!=null){
				  view.put("msg","成功登陆！");
				  view.put("ret","1");
			  }else{
				  view.put("msg","用户名或者密码不正确！");
				  view.put("ret","0");
			  }
		 
		  return JSONArray.fromObject(view);
	 }
    @RequestMapping(value="/logincfgl")
	@ResponseBody
	public JSONArray logincfgl(String username,String password,HttpServletRequest request,HttpSession session) throws IOException {
		  Map<String, String> view = new HashMap<String, String>();
		  view.put("username", username);
		  view.put("password", password);
		  User user = new User();
		  user.setUsername(username);
		  user.setPassword(password);
		  session.setAttribute("cfgl_user", user);
		  session.setAttribute("cfgl_username", user.getUsername());
		  if(userService.queryUserByUserNamePassword(user)!=null){
			  view.put("msg","成功登陆！");
			  view.put("ret","1");
		  }else{
			  view.put("msg","用户名或者密码不正确！");
			  view.put("ret","0");
		  }
		  return JSONArray.fromObject(view);
	 }
}

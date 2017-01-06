package com.cyb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.qutoes.vo.Idea;
import com.cyb.qutoes.vo.User;
import com.cyb.service.UserService;
import com.cyb.utils.DateUtil;
import com.cyb.utils.FileUtils;
import com.cyb.utils.MD5Encoder;
import com.cyb.utils.PropertyUtil;
import com.cyb.utils.UUIDUtils;

@Controller
@RequestMapping("user")
public class UserController {
	Logger log = Logger.getLogger(UserController.class);
	@Resource(name="userService")
	UserService userService;
	
	@RequestMapping(value="/login")
	public ModelAndView login(@ModelAttribute User user,HttpServletRequest request,HttpSession session) throws IOException {
		  ModelAndView view = new ModelAndView();
		  view.addObject("username", user.getUsername());
		  view.addObject("password", user.getPassword());
		  session.setAttribute("user", user);
		  session.setAttribute("username", user.getUsername());
		  if(userService.queryUserByUserNamePassword(user)!=null){
			  view.setViewName(user.getPage());
			  view.addObject("msg","成功登陆！");
			  view.addObject("ret",1);
		  }else{
			  view.setViewName("login");
			  view.addObject("msg","用户名或者密码不正确！");
			  view.addObject("ret",0);
		  }
		  return view;
	 }
	@RequestMapping(value="/loginphone")
	@ResponseBody
	public JSONObject loginphone(@ModelAttribute User user,HttpServletRequest request,HttpSession session) throws IOException {
			Map<String, Object> ret = new HashMap<String, Object>();
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
		  if(userService.queryUserByUserNamePassword(user)!=null){
			  ret.put("canLogin",true);
		  }else{
			  ret.put("canLogin",false);
		  }
		  log.info("服务器返回值："+JSONArray.fromObject(ret));
		  return JSONArray.fromObject(ret).getJSONObject(0);
	 }
	@RequestMapping(value="/register")
	public ModelAndView register(@ModelAttribute User user,HttpServletRequest request) throws IOException {
		  ModelAndView view = new ModelAndView();
		  view.setViewName("registerSuccess");
		  user.setId(UUIDUtils.getUUID());
		  user.setRegisterTime(DateUtil.date2long14(new Date()));
		  user.setPassword(MD5Encoder.encode(user.getPassword().trim()));
		  if(this.userService.queryUserByUserName(user)==null&&!"".equals(user.getUsername())&&user.getUsername()!=null){
			  this.userService.addUser(user);
			  view.addObject("msg", "congraluation you, "+user.getUsername()+" become one of us!");
		  }else{
			  view.addObject("msg", "sorry, the username "+user.getUsername()+" is used,please change anther one!");
		  }
		  return view;
	 }
	@RequestMapping(value="/idea")
	public ModelAndView ieda(@ModelAttribute Idea idea,HttpServletRequest request, @RequestParam MultipartFile file) throws IOException {
		  ModelAndView view = new ModelAndView();
		  try {
			  view.setViewName("registerSuccess");
			  CommonsMultipartFile cf= (CommonsMultipartFile)file; 
			  DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
			  String savePath = PropertyUtil.getValueByKey("App", "defaultUploadPath");
			  String realFileName = fi.getName();
			  String subfix = realFileName.substring(realFileName.indexOf("."), realFileName.length());
			  String fileId = UUIDUtils.getUUID();
			  log.info("上传临时文件路径："+fi.getStoreLocation());
			  FileUtils.copyFile(fi.getStoreLocation(), savePath+File.separator+fileId+subfix);
			  idea.setFileId(fileId);
			  /**
			   * name=Lighthouse.jpg, 
			   * StoreLocation=D:\chenyb\tools\apache-tomcat-7.0.52\work\Catalina/localhost/EStock/upload_24b6da42_5610_48df_9738_f1fc9279afea_00000003.tmp, 
			   * size=561276 bytes, 
			   * isFormField=false, 
			   * FieldName=file
			   */
			  idea.setId(UUIDUtils.getUUID());
			  idea.setSubmitTime(DateUtil.date2long14(new Date()));
			  this.userService.addIdea(idea);
			  view.addObject("msg","感谢您的宝贵意见，我们会尽快处理你的提议并改进【股往今来】！");
		} catch (Exception e) {
			log.info(e.toString());
			view.addObject("msg","意见存储出现问题！");
		}
		  return view;
	 }
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,HttpSession session,String page) throws IOException {
		  ModelAndView view = new ModelAndView();
		  String username = (String) session.getAttribute("username");
		  session.removeAttribute("user");
		  session.removeAttribute("username");
		  view.setViewName(page);
		  System.out.println("用户 "+username+" 成功退出！");
		  return view;
	}
	@Resource(name="qqMailSender")
	JavaMailSender qqMailSender;
	
	@RequestMapping(value="/resetScret")
	public ModelAndView resetPassword(@ModelAttribute User user,HttpSession session) throws IOException {
		  ModelAndView view = new ModelAndView();
		  int count = userService.updatePassword(user.getUsername());
		  if(count>0){
				SimpleMailMessage email = new SimpleMailMessage();  
				email.setFrom("383065059@qq.com");  
				email.setTo(user.getEmail());  
				email.setSubject("密码找回");   
				email.setText("您的密码已经重置，新密码为111111,请及时修改密码！");  
				qqMailSender.send(email);
				view.setViewName("registerSuccess");
			    view.addObject("msg","邮件发送成功，请到邮箱"+user.getEmail()+"查看新密码并及时修改密码！");
		  }else{
			    view.setViewName("findPassword");
			    view.addObject("msg","密码更新失败，请联系网站管理员！");
		  }
		  return view;
	 }
}

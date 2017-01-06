package com.cyb.phone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.phone.dao.PhoneLoginDaoImpl;
import com.cyb.phone.service.PhoneLoginServiceImpl;
import com.cyb.phone.service.PhoneStockServiceImpl;
import com.cyb.phone.vo.User;
import com.cyb.utils.MD5Encoder;
import com.cyb.utils.PropertyUtil;

@Controller
@RequestMapping("phone")
public class PhoneController {
	@Resource(name="phoneLoginService")
	PhoneLoginServiceImpl phoneLoginService ; 	
	@Resource(name="phoneStockService")
	PhoneStockServiceImpl phoneStockService ; 
	@RequestMapping(value="/test", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String test(){
		return "halou!中文。";
	}
//	produces = "text/html;charset=UTF-8"
	@RequestMapping(value="/login")
	@ResponseBody
	public JSONArray login(String username,String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(MD5Encoder.encode(password));
		boolean canLogin =  this.phoneLoginService.checkLogin(user);
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("canLogin", false);	
		if(canLogin){
			map.put("canLogin", true);
		}
		return JSONArray.fromObject(map);
	}
	
	@RequestMapping(value="/myConcerns")
	@ResponseBody
	public JSONArray login(String username){
		Map<String,Object> map = this.phoneStockService.myConern(username);
		return JSONArray.fromObject(map.get("data"));
	}
	@RequestMapping(value="/zdfx")
	@ResponseBody
	public JSONArray zdfx(String type){
		//type= sz xd tp 1 2
		Map<String,Object> map = this.phoneStockService.zdfx(type);
		return JSONArray.fromObject(map.get("data"));
	}
	@RequestMapping(value="/hyfl")
	@ResponseBody
	public JSONArray hyfl(String type){
		Map<String,Object> map = this.phoneStockService.hyfl(type);
		return JSONArray.fromObject(map.get("data"));
	}
	@RequestMapping(value="/kline")
	@ResponseBody
	public JSONArray kline(String code){
		Map<String,Object> map = this.phoneStockService.kline(code);
		return JSONArray.fromObject(map);
	}
	 @RequestMapping(value="/url")
	  @ResponseBody
	  public String getPushMap(){
		 String url =  PropertyUtil.getValueByKey("App", "pushurlPhone");
		  return url;
	  }
	 @RequestMapping(value="/urlIO")
	  @ResponseBody
	  public String getSoketIOPushUrl(){
		 String url =  PropertyUtil.get("pushurlPhoneIO");
		  return url;
	  }
	 @RequestMapping(value="/linejson")//, produces = "application/json"
	  @ResponseBody
	  public JSONArray lineJson(String code){
		 Map<String,Object> map = this.phoneStockService.lineJson(code);
		  return JSONArray.fromObject(map);
	 }
	 @RequestMapping(value="/zhzs")//, produces = "application/json"
	  @ResponseBody
	  public JSONArray zhzs(){
		 Map<String,Object> map = this.phoneStockService.dpzs(null);
		 return JSONArray.fromObject(map.get("data"));
	 }
	  @RequestMapping(value="/dpfx")//, produces = "application/json"
	  @ResponseBody
	  public JSONArray dpfx(){
		  List<Map<String, Object>> data = this.phoneStockService.staticsCompany();
		  Map dpzs = this.phoneStockService.hsindiator();
		  Map ret = new HashMap();
		  ret.put("dpzs", dpzs);
		  ret.put("lst", data);
		  return JSONArray.fromObject(ret);
	 }
	  @RequestMapping(value="/close")//, produces = "application/json"
	  @ResponseBody
	  public JSONArray closeQutoes(String code){
		 Map<String, Object> data = this.phoneStockService.closeQutoes(code);
		 return JSONArray.fromObject(data);
	 }
}

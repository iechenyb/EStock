package com.cyb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.ModelAndView;
/**
 * 返回值测试，包括返回页面、返回集合信息、json以及普通的类型值。
 * @author DHUser
 *
 */
@Controller
@RequestMapping("return")
public class StudyReturnValueController {
	//返回页面，测试视图配置文件 /return/jsp
	@RequestMapping(value = "/jsp", method = RequestMethod.GET)
	public String viewtest(@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {
		System.out.println("Received request to show inner jsp page of  web root  jsp");
		if (error == true) {
			model.put("error","You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		model.put("ret", "chenyb");
		return "/jsp/jspinner/jspinner";
	}
	
	@RequestMapping(value = "/mav", method = RequestMethod.GET)
	public ModelAndView viemav() {
		ModelAndView mav = new ModelAndView();
		System.out.println("Received request to show inner jsp page of  web root  jsp");
		mav.addObject("ret", "attributeValue");	
		mav.setViewName("/jsp/jspinner/jspinner");
		return mav;
	}
	
	
	//study/jsonest 
	@ResponseBody   //返回json的时候，必须加  @responseBody注解
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public JSONArray jsontest() {
		Map map = new HashMap();
		map.put("k1", "11111");
		map.put("k2", "22222");
		System.out.println("map = "+map);
		return JSONArray.fromObject(map);
	}
	
	@ResponseBody   //返回map的时候，必须加  @responseBody注解
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public Map maptest() {
		Map map = new HashMap();
		map.put("1", "11111");
		map.put("2", "22222");
		System.out.println("map = "+map);
		return map;
	}
	
	@ResponseBody   //返回list的时候，必须加  @responseBody注解
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List listtest() {
		List list = new ArrayList();
		list.add(1);list.add(2);list.add(3);
		System.out.println("list = "+list);
		return list;
	}
	
	@ResponseBody   //返回string的时候，必须加  @responseBody注解
	@RequestMapping(value = "/string", method = RequestMethod.GET)
	public String strtest() {
		List list = new ArrayList();
		list.add(343441);list.add(33332);list.add(44443);
		System.out.println("list = "+list);
		return list.toString();
	}
	
}

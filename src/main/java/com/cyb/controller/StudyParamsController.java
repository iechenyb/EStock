package com.cyb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cyb.bean.User;
/*
 * 页面传递参数学习
 */
@Controller
@RequestMapping("para")
public class StudyParamsController {
	//参数建议不要用普通数据类型，传空值时汇报错 
	//http://localhost:8080/springmvc/paramter/get/obj?name=chenybsdfsf&age=30&sex=true
	@RequestMapping(value = "/get/name", method = RequestMethod.GET)
	public ModelAndView getname(String name,Integer age,Boolean sex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ret", name+"#"+age+"#"+sex);	
		mav.setViewName("/jsp/jspinner/jspinner");
		return mav;
	}
	/**
	 * 请求路径：
		localhost/user/addUser?user.name="test"
		那么在后台接受到的参数中，user对象的name属性是null。如果路径换为localhost/user/addUser?name="test"，则user对象的name属性是test。
		这里必须用name="test"而不是user.name="test"，因为默认情况下springMVC是不支持user.name这种传参方式的。
		请求方式 :http://localhost:8080/springmvc/paramter/get/obj?name=chenybsdfsf&age=30&sex=true
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/get/obj", method = RequestMethod.GET)
	public ModelAndView getobj(@ModelAttribute User user) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ret", user.getName()+"$"+user.getAge()+"$"+user.getSex());	
		mav.setViewName("/jsp/jspinner/jspinner");
		return mav;
	}
	
    @RequestMapping(value = "/get/path/{id}/{str}")
    public ModelAndView getPath(@PathVariable String id,
            @PathVariable String str) {
    	ModelAndView mav = new ModelAndView();
		mav.addObject("ret",id+"$"+str);	
		mav.setViewName("/jsp/jspinner/jspinner");
		return mav;
    }
    //------------------------------------------------
    @RequestMapping(value = "/get/req")
    public ModelAndView getReq(HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView mav = new ModelAndView();
		mav.addObject("ret","req.name = "+request.getParameter("name")+"$ response.getCharacterEncoding="+response.getCharacterEncoding());	
		System.out.println(request+","+request.getQueryString());
		/*queryString 
		  get 有值  post 没有值
		*/
		mav.setViewName("/jsp/jspinner/jspinner");
		return mav;
    }
    
    //用注解@RequestParam绑定请求参数name到变量name
    @RequestMapping(value = "/get/requestParam")
    public ModelAndView setupForm(@RequestParam("name") String name, ModelMap model) {
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("ret",name);	
		mav.setViewName("/jsp/jspinner/jspinner");
		System.out.println("name="+name);
        return mav;
    }
    
    
    //返回页面参数的第一种方式,在形参中放入一个map  
    @RequestMapping(value = "/map/ret")  
    public String hello(int id,Map<String,Object> map){  
        System.out.println("hello1 action:"+id);  
        map.put("ret", "huangjie map");  
        return "/jsp/jspinner/jspinner";  
    }  
    
    //返回页面参数的第二种方式,在形参中放入一个Model  
    @RequestMapping(value = "/model/ret")  
    public String hello2(int id,Model model){  
        System.out.println("hello2 action:"+id);  
        model.addAttribute("ret", "huangjie model");  
        //这个只有值没有键的情况下,使用Object的类型作为key,String-->string  
        model.addAttribute("ok");  
        return "/jsp/jspinner/jspinner";  
    }  
    
    
}

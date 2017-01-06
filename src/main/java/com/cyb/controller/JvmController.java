package com.cyb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.bean.User;

@RequestMapping("jmv")
@Controller
public class JvmController {
	 @RequestMapping("gc")
	 @ResponseBody
	 public String  gc(){
		 System.gc();
		 return "1";
	 }
	 @RequestMapping("alloc")
	 @ResponseBody
	 public String  s(boolean flag){
		 long l = 0;
		 List<User> us = new ArrayList<User>();
		 while(flag){
		   User user = new User();
		   us.add(user);
		   l++;
		   if(l>=1000000000){
			   l=0;
		   }
		 }		 
		 return "1";
	 }
	 public static void main(String[] args) {
		 System.gc();
		 System.out.println("over!");
	}
}

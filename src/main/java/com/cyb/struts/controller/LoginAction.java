package com.cyb.struts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.cyb.struts.Action;
import com.cyb.struts.bean.Book;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction implements Action{
	 Logger x ;
	 private String userName;  
	    private String passWord;  
	      
	    public String getUserName() {  
	        return userName;  
	    }  
	      
	    public void setUserName(String userName) {  
	        this.userName = userName;  
	    }  
	    public String getPassWord() {  
	        return passWord;  
	    }  
	    public void setPassWord(String passWord) {  
	        this.passWord = passWord;  
	    }  
	      
	      
	    //处理用护请求的execute方法  
	    public String execute() throws Exception  
	    {  
	      
	    if (getUserName().equals("chenyb")  
	    && getPassWord().equals("1") )  
	    {  
	        //通过ActionContext访问Web对象的Session对象,此处的ActionContext返回的是一个Map  
	        //虽然struts2的action中没有HttpSession对象,但我们也可以通过通过ActionContext访问Web对象的Session对象  
	        Map<String, Object> sessionMap = ActionContext.getContext().getSession();  
	        sessionMap.put("user" , getUserName());  
	        Book book = new Book();  
	        Object books[]= book.getBook();  
	        List<Object[]> bookList = new ArrayList<Object[]>();  
	        bookList.add(books);  
	        sessionMap.put("book", bookList);  
	        return SUCCESS;  
	    }  
	    else  
	    {  
	        return ERROR;  
	    }  
	    }  
}

package com.cyb.struts;

public interface Action {
	 public static String SUCCESS="suc";  
	 public static String ERROR="error";  
	      
	 public String execute()throws Exception;  
}

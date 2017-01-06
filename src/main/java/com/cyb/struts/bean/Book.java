package com.cyb.struts.bean;

public class Book {
	 private static Object book[]=new Object[4];  
     
	    public Book(){  
	        book[0] = "《java编程思想》";  
	        book[1] = "《Struts2权威指南》";  
	        book[2] = "《精通Oracle》";  
	        book[3] = "《我的Flex我精通》";  
	    }  
	      
	    public Object[] getBook(){  
	        return book;  
	    }  
}

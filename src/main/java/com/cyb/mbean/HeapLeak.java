package com.cyb.mbean;

import java.util.ArrayList;
/**
 * 由Java的官方文档我们可以看出，Java堆中存放：对象、数组。下面以不断创建对象为例：
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * @author DHUser
 *
 */
public class HeapLeak {
	@SuppressWarnings("unchecked")
	public void  heapLeak(){
		 @SuppressWarnings("rawtypes")
			ArrayList<method> list = new ArrayList();

	        while(true){
	            list.add(new HeapLeak.method());
	        }
	}
	public static void main(String[] args){
	       
	    }
	    static class method{

	    }
}

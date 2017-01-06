package com.cyb.mbean;

import java.util.ArrayList;
/**
 * 常量池溢出
 * 常量池在方法区中，首先设置持久代大小，使其不可扩展。
 * @author DHUser
 * -XX:PermSize=10m -XX:MaxPermSize=10m
 *
 */
public class ConstantPoolLeak {
	 public static void main(String[] args) {
        int count = 0;
        ArrayList<String> list = new ArrayList<String>();
        while (true){
            list.add(String.valueOf(count++).intern());
        }
	}
	 public void constantPoolLeak(){
		 int count = 0;
	        ArrayList<String> list = new ArrayList<String>();
	        while (true){
	            list.add(String.valueOf(count++).intern());
	        }
	 }
	 /**
	  * 然后需要做的就不停地往方法区中加字符串。
	  * 其中intern()就是查看方法区中有没有这个字符串，没有的话就加进去，
	  * 如果这里不用intern()，字符串是存在堆里的，会报heapOutOfMemory.
	  */
}

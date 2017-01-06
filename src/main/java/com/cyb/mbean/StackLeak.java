package com.cyb.mbean;
/**
 * 栈溢出
从Java官方API中我们知道，栈中存储：基本数据类型，对象引用，方法等。下面以无限递归创建方法和申请栈空间为例，分别演示栈的stackOverflow和OutOfMemory
 * @author DHUser
 * Exception in thread "main" java.lang.StackOverflowError
 *
 */
public class StackLeak {
	public static void main(String[] args){

        method();

    }

    public static void method(){

        method();

    }
}

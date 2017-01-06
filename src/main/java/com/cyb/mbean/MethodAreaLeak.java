package com.cyb.mbean;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * 从Java官方API中我们知道，方法区存放每个Class的结构，
 * 比如说运行时常量池、域、方法数据、方法体、构造函数、包括类中的专用方法、实例初始化、接口初始化。
Java的反射和动态代理可以动态产生Class，另外第三方的CGLIB可以直接操作字节码，也可以动态产生Class，下面通过CGLIB来演示。
 * @author DHUser
 *
 */
public class MethodAreaLeak {
	public void methodAreaLeak(){
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object arg0, Method arg1,
						Object[] arg2, MethodProxy arg3) throws Throwable {
					return arg3.invoke(arg0, arg2);
				}

			});
			enhancer.create();
		}
	}
	public static void main(String[] args) {

	}

	class OOMObject {

	}

}

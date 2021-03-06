package com.cyb.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
	public static void main(String[] args) throws Exception {
		UserBean bean = new UserBean();
		bean.setId(1000);
		bean.setAddress("武汉");
		bean.setAge(100);
		bean.setName("chenyb");
		printNameValueOfObject(bean);
	}

	public static void printNameValueOfObject(Reflector bean) {
		Class<? extends Reflector> userCla;
		try {
			userCla = bean.getClass();
			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(bean);// 得到此属性的值
				System.out.println(f.getName() + "= " + val);
				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("String")) {
					// System.out.println(f.getType()+"\t是String");
					f.set(bean, "12"); // 给属性设值
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
					// System.out.println(f.getType()+"\t是int");
					f.set(bean, 12); // 给属性设值
				} else {
					// System.out.println(f.getType()+"\t");
				}
			}
			/*
			 * 得到类中的方法
			 */
			Method[] methods = userCla.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().startsWith("get")) {
					// System.out.print("methodName:"+method.getName()+"\t");
					// System.out.println("value:"+method.invoke(bean));//得到get
					// 方法的值
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
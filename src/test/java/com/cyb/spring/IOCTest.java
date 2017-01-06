package com.cyb.spring;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.jaxen.NamespaceContext;
import org.junit.Before;

import com.cyb.bean.User;

/**
 * 模拟spring的依赖注入
 * @author DHUser
 *
 */
public class IOCTest {
	public MyApplicationContext ac = null;
	NamespaceContext x;
	@Before
	public void init(){
		//new ClassPathXmlApplicationContext("");
		ac = new MyApplicationContext(
				"MyApplicationContext.xml"
		);
	}
    @org.junit.Test
    public void testSpringIOC() {
    	Person person = (Person)ac.getBean("chinese");
    	person.save();
    	person.useAxe();
    	Chinese per = (Chinese)ac.getBean("chinese");
    	per.save();
    	per.useAxe();
    	System.out.println("设置普通属性："+per.getName());
    	/*try {
    		User user = new User();
			PropertyDescriptor[] ps = Introspector.getBeanInfo(user.getClass()).getPropertyDescriptors();
			for(int i=0;i<ps.length;i++){
				System.out.println(ps.length+","+ps[i].getWriteMethod()+","+ps[i].getName());
			}
			} catch (IntrospectionException e) {
			e.printStackTrace();
		}*/
    	//Object obj = ConvertUtils.convert(null, new Axe());
    }
}

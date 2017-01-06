package com.webservice.cxf;

import javax.jws.WebService;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


@WebService(endpointInterface = "com.webservice.cxf.HelloWorld",serviceName="HelloGT")  
public class HelloWorldImpl implements HelloWorld{  
    @Override  
    public String sayHello(String name) {  
    	System.out.println("cxf ws invoke :"+name);
        return name;  
    }  
    public static void main(String[] args) {
    	String url = "http://localhost:8089/services/HelloWorldService?wsdl";
    	/*Endpoint endpoint =Endpoint.publish("http://localhost:8089/services/HelloWorldService?wsdl",
		new HelloWorldImpl());//这里是实现类
		System.out.println("WS发布成功！");*/
		
		/*HelloWorldImpl impl = new HelloWorldImpl();
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
		factoryBean.setAddress("http://localhost:8089/services/HelloWorldService?wsdl");
		factoryBean.setServiceClass(HelloWorld.class);//接口类
		factoryBean.setServiceBean(impl);
		factoryBean.create();
		System.out.println("WS发布成功！");*/
    	
     /*  	QName qName = new QName("http://service.jaxb.com/","HelloServiceImplService");
    	HelloWorld helloServiceImplService =new HelloWorldImpl(
    			new URL("http://localhost:8080/WSCXF/helloService?wsdl"),qName);
    	HelloWorld helloService	=(HelloWorld)helloServiceImplService.getPort(HelloWorld.class);*/
    	
    	JaxWsProxyFactoryBean soapFactoryBean = new JaxWsProxyFactoryBean();
    	soapFactoryBean.setAddress(url);
    	soapFactoryBean.setServiceClass(HelloWorld.class);
    	Object o = soapFactoryBean.create();
    	HelloWorld helloService = (HelloWorld)o;
    	helloService.sayHello("chenyb");
    	//Utils x;
    	/*JaxWsDynamicClientFactory dcf =JaxWsDynamicClientFactory.newInstance();
    	org.apache.cxf.endpoint.Client client =	dcf.createClient(url);
    	//sayHello 为接口中定义的方法名称  张三为传递的参数   返回一个Object数组
    	Object[] objects;
		try {
			objects = client.invoke("sayHello","张三");
			//输出调用结果
	    	System.out.println(objects[0].toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
  
}  

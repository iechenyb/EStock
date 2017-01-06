package com.cyb.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ServletFilter1
 */
public class ServletFilter1 implements Filter {

    /**
     * Default constructor. 
     */
    public ServletFilter1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*System.out.println("第一个拦截器的chain.doFilter()之前 \t\t\t\t\t"+" 1111111");
		HttpServletRequest hrequest = (HttpServletRequest)request;
		HttpServletResponse res= (HttpServletResponse)response;
        WrapperResponse wrapperResponse = new WrapperResponse(res);
        System.out.println(hrequest.getRequestURL()+","+hrequest.getRequestURI());*/
		chain.doFilter(request, response);
		/*Enumeration<?> el=hrequest.getAttributeNames();
		while(el.hasMoreElements()){
			System.out.println(el.nextElement().toString());
		}*/
        /*String html = wrapperResponse.getContent();
        System.out.println("response 返回的页面或者数据("+wrapperResponse.getContentType()+","+wrapperResponse.getCharacterEncoding()+")："+html);
		ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
		String responseContent = new String(responseWrapper.getDataStream());
		System.out.println("第一个拦截器的chain.doFilter()之后 \t\t\t\t\t"+" 5555555");*/
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

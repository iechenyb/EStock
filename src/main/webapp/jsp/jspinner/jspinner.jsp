<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="from"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<!-- $在服务端解析 -->
    测试springmvc的视图解析器，进入第二级目录访问jsp页面,返回参数值  ret= <font color='red' >${ret}</font>
   <hr>
  <c:out value="&lt要显示的数据对象（未使用转义字符）&gt" escapeXml="true" default="默认值"></c:out><br/>
  <c:out value="&lt要显示的数据对象（使用转义字符）&gt" escapeXml="false" default="默认值"></c:out><br/>
  <c:out value="${null}" escapeXml="false">使用的表达式结果为null，则输出该默认值</c:out><br/>
  <c:set value="张三" var="name1" scope="session"></c:set>
  <c:set var="name2" scope="session">李四</c:set>
  <%-- <c:set value="赵五" target="${person}" property="name"></c:set> --%>
  <%-- <c:set target="${person}" property="age">19</c:set> --%>
	  从session中得到的值：${sessionScope.name1}
	  从session中得到的值：${sessionScope.name2}
	<%--   从Bean中获取对象person的name值：<c:out value="${person.name}"></c:out>
	  从Bean中获取对象person的age值：<c:out value="${person.age}"></c:out> --%>
  <hr>
  <spring:message></spring:message>
  <spring:theme></spring:theme>
  <from:form></from:form>
</body>
</html>
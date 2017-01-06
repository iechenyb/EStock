<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%
	String basePath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";
	List a = new ArrayList();
	a.add("贝贝");
	a.add("晶晶");
	a.add("欢欢");
	a.add("莹莹");
	a.add("妮妮");
	Map map = new HashMap();
	map.put("name", "chenyb");
	request.setAttribute("list", a);
	request.setAttribute("map", map);
	request.setAttribute("person", map);
	String[] ary=new String[3];
    ary[0]="A";
    ary[1]="B";
    ary[2]="C";
    pageContext.setAttribute("ary",ary);
	pageContext.setAttribute("lists",a);
	/*
	 <include file="../public.jsp" ><!-- 翻译阶段执行 -->
	 */
%>
<%@taglib prefix="ex" uri="/custom"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <jsp:include page="../public.jsp" flush="true" >
	<jsp:param name="fitstParamer" value="firstValue">
	<jsp:param name="lastParamer" value="lastValue">
</jsp:include>  
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>标准标签库</title>
</head>
<body>
	<hr>
	<c:out value="<要显示的数据对象（未使用转义字符）>" escapeXml="true" default="默认值"></c:out>
	<br />
	<c:out value="<要显示的数据对象（使用转义字符）>" escapeXml="false" default="默认值"></c:out>
	<br />
	<c:out value="${null}" escapeXml="false">使用的表达式结果为null，则输出该默认值</c:out>
	<br />
	<hr>
	<c:set value="张三" var="name1" scope="session"></c:set>
	<c:set var="name2" scope="session">李四</c:set>
	<ul>
		<li>从session中得到的值：${sessionScope.name1}</li>
		<li>从session中得到的值：${sessionScope.name2}</li>
	</ul>
	<hr>
	<c:if test="${'1' == '1'}" var="rs1"></c:if>
	<c:out value="rs1的值：${rs1}"></c:out>
	<br />
	<c:if test="${'2' == '1'}" var="rs2"></c:if>
	<c:out value="rs2的值：${rs2}"></c:out>
	<hr>
	<c:set var="score">85</c:set>
	<c:choose>
		<c:when test="${score>=90}">  
    你的成绩${score}为优秀！  
    </c:when>
		<c:when test="${score>=70&&score<90}">  
    您的成绩${score}为良好!  
    </c:when>
		<c:when test="${score>60&&score<70}">  
    您的成绩${score}为及格  
    </c:when>
		<c:otherwise>  
    对不起，您没有通过考试！  
    </c:otherwise>
	</c:choose>
	<hr>
	<B><c:out value="不指定begin和end的迭代：" /></B>
	<br>
	<c:forEach var="fuwa" items="${list}">
		<c:out value="${fuwa}" />
		<br>
	</c:forEach>
	<hr>
	<B><c:out value="指定begin和end的迭代：" /></B>
	<br>
	<c:forEach var="fuwa" items="${list}" begin="1" end="3" step="2">
		<c:out value="${fuwa}" />
		<br>
	</c:forEach>
	<hr>
	<B><c:out value="输出整个迭代的信息：" /></B>
	<br>
	<c:forEach var="fuwa" items="${list}" begin="3" end="4" step="1"
		varStatus="s">
		<c:out value="${fuwa}" />的四种属性：<br>  
       所在位置，即索引：<c:out value="${s.index}" />
		<br>  
       总共已迭代的次数：<c:out value="${s.count}" />
		<br>  
       是否为第一个位置：<c:out value="${s.first}" />
		<br>  
       是否为最后一个位置：<c:out value="${s.last}" />
		<br>
	</c:forEach>
	<hr>
	取map的key值：${map.name}
	<hr>
	<c:set value="赵五" target="${person}" property="name"></c:set>
	<c:set target="${person}" property="age">19</c:set>
	<li>从map中获取对象person的name值：<c:out value="${person.name}"></c:out></li>
	<li>从map中获取对象person的age值：<c:out value="${person.age}"></c:out></li>
	<hr>
	<b>自定义标签</b><br>
	 <h3>输出标签</h3>
	<ex:Hello/><br>
	 <h3>有body内容的标签</h3>
	<ex:HelloBody>This is message of tag body</ex:HelloBody><br>
	 <h3>传递固定参数</h3>
	<ex:HelloPro message="This is custom tag has property" /><br>
	 <h3>传递表达式参数</h3>
	<ex:HelloPro message="${person.name}" /><br>
	 <h3>这里迭代的是集合</h3>
     <table style="border:solid 1px black; width=400xp;">
           <ex:foreach items="lists" var="item"> 
               <tr>
                   <td>${item}-<ex:HelloPro message="${item}" /></td>
               </tr>
           </ex:foreach>
       </table>
           <br />
        <h3>这里迭代的是数组</h3>
        <ex:foreach items="ary" var="item">
            ${item }&nbsp;
        </ex:foreach>
        <h3>这里if标签</h3>
         <ex:if test="${null==null }">
		    未登陆. <br>
		 </ex:if>
		 <ex:if test="${null!=null }">
		   welcome用户已经登录. <br>
    	</ex:if>
</body>
</html>
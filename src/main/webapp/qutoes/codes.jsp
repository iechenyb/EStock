<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
<title>股往金来</title>
</head>
<body>
 <font color='gray' size=4>菜单->搜索结果</font>
 <center>
  <table class="am-table">
     <tbody>
     <tr>
       <c:forEach var="vo" items="${lst}" varStatus="status">
        <td> 
         <a target='_blank'   title='<c:out value='${vo.code}-${status.index}' default='wang'/>'
		       href="<%=basePath%>ws/line.zc?code=<c:out value='${vo.code}' default='wang'/>">
		       &nbsp;<b><c:out value="${vo.name}"   default="wang" /></b>
				</a> 
        </td>
        <c:if test="${(status.index+1)%10==0}"></tr></c:if>
        </c:forEach> 
      </tr>
        </tbody>
    </table>
    <!-- <div class="am-g am-margin-left-xl am-margin-right-xl">
        <c:forEach var="vo" items="${lst}" varStatus="status">
         <div class="am-u-sm-2 am-u-end">
		       <a target='pcframe'   title='<c:out value='${vo.code}-${status.index}' default='wang'/>'
		       href="<%=basePath%>ws/line.zc?code=<c:out value='${vo.code}' default='wang'/>">
		       &nbsp;<c:out value="${vo.name}"   default="wang" />
				</a> 
	       </div>
	    </c:forEach> 
	 </div> 
   -->
	 </center>
</body>
</html>
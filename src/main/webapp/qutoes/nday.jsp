<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
Object object = request.getSession().getAttribute("username");
String username = "";
if (object != null) {
	username = object.toString();
}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
<input type="hidden" id="basePath" value=<%=basePath%>/>
<title>股往金来</title>
</head>
<body ng-controller='controller'>
<div><font color='gray' size=4>连续涨跌分析</font></div>
<hr>
 <center>
 <table class="am-table am-table-bd am-table-bdrs am-table-striped">
    <thead>
        <tr>
            <th>股票代码</th>
             <th>股票名称</th>
              <th></th>
        </tr>
    </thead>
     <tbody>
     <c:forEach var="vo" items="${list}" varStatus="status">
        <tr>
            <td> 
	            <a target='_blank'   title='<c:out value='${vo.code}' default='wang'/>'
			       href="<%=basePath%>ws/line.zc?code=<c:out value='${vo.code}' default='wang'/>">
			       &nbsp;<b><c:out value="${vo.code}"   default="wang" /></b>
				</a> 
			</td>
			 <td> 
	           <b><c:out value="${vo.name}"   default="wang" /></b>
			</td>
			<td><a ng-click="showDialog('${vo.code}')" title='${vo.name}' class="ng-binding"><b>收盘行情</b></a></td>
        </tr>
     </c:forEach>
   </tbody>
</table>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">
					收盘历史走势 <a href="javascript: void(0)"
						class="am-close am-close-spin" data-am-modal-close>&times;</a>
				</div>
				<div id="longShortRateTrend" style="height: 80%"></div>
			</div>
</div>
</center>
 <script src="<%=basePath%>js/jquery.min.js"></script>
 <script src="<%=basePath%>js/jquery.js"></script>
 <script src="<%=basePath%>echarts/echarts-all.js"></script>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
 <script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
 <script src="<%=basePath%>js/my/qutoes.js"></script>
</body>
</html>
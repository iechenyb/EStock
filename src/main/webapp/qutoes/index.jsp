<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<title>股往金来</title>
<style type="text/css">
<!--130px-->
.s_btn { 
width: 100%;
height: 48px;
margin-top:5px;
margin-left:0px;
padding-left:0px;
float:left;
color: white;
font-size: 15px;
letter-spacing: 1px;
background: #3385ff;
border-bottom: 1px solid #2d78f4;
outline: medium;
-webkit-appearance: none;
-webkit-border-radius: 0;
}

.quickdelete-wrap {
position: relative;
}
<!-- s 586px-->
.s_ipt {
width: 100%;
height: 48px;
font: 16px/18px arial;
line-height: 22px\9;
margin: 6px 0 0 7px;
padding: 0;
background: transparent;
border: 0;
outline: 0;
-webkit-appearance: none;
}
input {
border: 0;
padding: 0;
}
body {
border: 0;
padding: 0;
margin:0;
}
.txt{
    color:#ccc;
}
.focus{
    color:#333;
}
</style>
</head>
<input type='hidden' value=<%=basePath%> id='context'/>
<body ng-controller='controller'>
<%--  <center>
 	<img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="180px" height="150px"/>
 </center> --%>
  <div class="am-g ">
         <div class="am-u-sm-7 am-u-end am-text-center">&nbsp;</div>
		 <div class="am-u-sm-1 am-u-end am-text-center"><a target="_blank" title="最新的行情数据" href="<%=basePath%>index.jsp">个人中心</a></div> 
		  <% if("".equals(username)){ %>
		   <div class="am-u-sm-1 am-u-end am-text-center"><a href="<%=basePath%>cfjh/login.jsp?page=qutoes/index">登陆</a></div>
		   <div class="am-u-sm-1 am-u-end am-text-center"><a href="<%=basePath%>register.jsp">注册</a></div>
		  <% }else{ %>
		   <div class="am-u-sm-1 am-u-end am-text-center"><a href="<%=basePath%>index.jsp"><%=username%></a></div>
		   <div class="am-u-sm-1 am-u-end am-text-center"><a href="<%=basePath%>user/logout.zc?page=qutoes/index" target="_self">退出</a></div>
		   <%} %>
		 <div class="am-u-sm-1 am-u-end am-text-center"> <a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>意见箱</a></div>
		 <div class="am-u-sm-1 am-u-end am-text-center"><a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>联系我们</a></div>
	       
 </div> 
 <%-- <table class="am-table1" width="100%"  border=0>
      <tr>
        <td   align='center' >
      		&nbsp;
        </td>
        <td   align='right' >
      		<a target="_blank" title="最新的行情数据" href="<%=basePath%>index.jsp">个人中心</a>
        </td>
        <% if("".equals(username)){ %>
        <td   width="3%" align='center' >
      		<a href="<%=basePath%>cfjh/login.jsp?page=qutoes/index">登陆</a>
        </td>
          <td   width="3%" align='center' >
      		<a href="<%=basePath%>register.jsp">注册</a>
        </td>
        <% }else{ %>
        <td    width="8%" align='center' >
      		<a href="<%=basePath%>index.jsp"><%=username%></a>
        </td>
         <td   width="5%" align='center' >
      		<a href="<%=basePath%>user/logout.zc?page=qutoes/index" target="_self">退出</a>
        </td>
        <%} %>
        <td   width="4%" align='right' >
      		 <a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>意见箱</a>
        </td>
        <td   width="5%" align='right' >
      		 <a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>联系我们</a>
        </td>
        <td   width="2%" align='right' >
      		&nbsp;
        </td>
       </tr>
  </table> --%>
  <div class="am-u-sm-12 am-u-end am-text-center" style="margin-top:100px;margin-bottom:10px;">&nbsp;</div>
  <div class="am-g ">
         <div class="am-u-sm-2 am-u-end am-text-center">&nbsp;</div>
         <div class="am-u-sm-8 am-u-end am-text-center">
               <div style="color: #353535; font-family: 微软雅黑; font-size: 36px; padding:0px; font-weight:bold;height:60px;">
    			<span style="text-shadow: 2px 2px 2px #dd0,2px 2px 2px #ff0, 0px 0px 10px #0ff, 0px 0px 20px #f0f;">
    			股往<img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="50px" height="40px"/>今来
    			</span>
 				</div>
         </div>
         <div class="am-u-sm-2 am-u-end am-text-center">&nbsp;</div>
  </div>
    <div class="am-u-sm-12 am-u-end am-text-center" style="margin-top:10px;margin-bottom:10px;">&nbsp;</div>
  <div class="am-g ">
         <div class="am-u-sm-4 am-u-end am-text-center">&nbsp;</div>
         <div class="am-u-sm-1 am-u-end am-text-left"><a target="_blank" title="sh000001" href="<%=basePath%>ws/line.zc?code=sh000001">&nbsp;上证指数</a></div>
         <div class="am-u-sm-1 am-u-end am-text-left"><a target="_blank" title="sz399001" href="<%=basePath%>ws/line.zc?code=sz399001">&nbsp;深证成指</a></div>
         <div class="am-u-sm-1 am-u-end am-text-left"><a target="_blank" title="sz399106" href="<%=basePath%>ws/line.zc?code=sz399006"> &nbsp;创业板综</a></div>
         <div class="am-u-sm-5 am-u-end am-text-center">&nbsp;</div>
  </div>
  <div class="am-g " style="border:0px solid red">
         <div class="am-u-sm-4 am-u-end am-text-center">&nbsp;</div>
          <div class="am-u-sm-4 am-u-end am-text-right" style="border:0px solid red">
			<input id="kw" ng-model="condition" class="am-form-field am-radius s_ipt"  placeholder="&nbsp;输入股票、基金、国债代码或者名称"  value="" 
		 	onfocus="if(this.value=='&nbsp;输入股票、基金、国债代码或者名称'){this.value='';}"  
		 	onblur="if(this.value=='&nbsp;输入股票、基金、国债代码或者名称'){this.value='';}" 
		 	onmouseout="if(this.value=='&nbsp;输入股票、基金、国债代码或者名称'){this.value='';}" 
		 	maxlength="255" autocomplete="off" style='margin-right:0px;border:1px solid gray;'/>
	       </div>
           <div class="am-u-sm-1 am-u-end am-text-left" style="border:0px solid red"> <button ng-click='search()' class="am-btn am-btn-primary s_btn">搜索一下</button></div>
           <div class="am-u-sm-3 am-u-end am-text-center">&nbsp;</div>
   </div>
      <%-- <table class="am-table1" width="100%"  border=0>
      <tr height="150px">
        <td colspan=3 align='center' >
      		&nbsp;
        </td>
       </tr>
       <tr>
        <td colspan=3 align='center'>
                <div style="color: #353535; font-family: 微软雅黑; font-size: 36px; padding:0px; font-weight:bold;">
    			<span style="text-shadow: 2px 2px 2px #dd0,2px 2px 2px #ff0, 0px 0px 10px #0ff, 0px 0px 20px #f0f;">
    			股往<img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="50px" height="40px"/>今来
    			</span>
 				</div>
        </td>
       </tr>
        <tr>
        <td colspan=3 align='center' height="15px;">
        </td>
        </tr>
        <tr >
       <td width="40%" align='right'><a target="_blank" title="sh000001" href="<%=basePath%>ws/line.zc?code=sh000001">&nbsp;上证指数</a></td>
        <td align='left'>
		  &nbsp;&nbsp;<a target="_blank" title="sz399001" href="<%=basePath%>ws/line.zc?code=sz399001">&nbsp;深证成指</a>
		  <a target="_blank" title="sz399106" href="<%=basePath%>ws/line.zc?code=sz399006"> &nbsp;创业板综</a>
        </td>
        <td align='left'></td>
       </tr>
       <tr align='center'>
        <td align='right' colspan=2 width="65%" height='50px'>
		 	<input id="kw" ng-model="condition" class="am-form-field am-radius s_ipt"  placeholder="&nbsp;输入股票、基金、国债代码或者名称"  value="" 
		 	onfocus="if(this.value=='&nbsp;输入股票、基金、国债代码或者名称'){this.value='';}"  
		 	onblur="if(this.value=='&nbsp;输入股票、基金、国债代码或者名称'){this.value='';}" 
		 	onmouseout="if(this.value=='&nbsp;输入股票、基金、国债代码或者名称'){this.value='';}" 
		 	maxlength="255" autocomplete="off" style='margin-right:0px;border:1px solid gray;'/>
        </td>
        <td>
        <button ng-click='search()' class="am-btn am-btn-primary s_btn am-margin-left-1">搜索一下</button>
        </td>
       </tr>
      <tr align='center'>
      <td height=20px></td>
      </tr>
      </table> --%>
      
	<!--  <div class="am-g">
	 <div class="am-u-sm-3">&nbsp;</div>
	 	<div class="am-u-sm-5 border">
		 	<input id="kw" ng-model="condition" style="margin-right:0px;" class="s_ipt"  placeholder="输入股票、基金、国债代码或者名称"  value="" 
		 	onfocus="if(this.value=='输入股票、基金、国债代码或者名称'){this.value='';}"  
		 	onblur="if(this.value=='输入股票、基金、国债代码或者名称'){this.value='';}" 
		 	onmouseout="if(this.value=='输入股票、基金、国债代码或者名称'){this.value='';}" 
		 	maxlength="255" autocomplete="off" style="border:1px solid gray;">
	 	</div>
		<div class="am-u-sm-1">
			<button ng-click='search()' class="am-btn am-btn-primary s_btn am-margin-left-0">搜索一下</button>
		</div>
		<div class="am-u-sm-3">&nbsp;</div>
	 </div> -->


<!-- http://favicon.htmlkit.com/favicon/ -->
<%-- <span style="display:block;border:0px solid red;height:50px;width:526px;">
 <span style="display:block;width:150px;border:0px solid gray;float:left;">
 <!--<%=basePath%>ws/line?code=sh000001 -->
  <a target="_blank" title="sh000001-0" href="<%=basePath%>ws/codes.zc?code=sh000001">&nbsp;上证指数</a>
 </span> 
 <span style="display:block;width:150px;border:0px solid gray;float:left;">
   <a target="_blank" title="sz399102-1" href="<%=basePath%>ws/codes.zc?code=sz399006"> &nbsp;创业板综</a>
 </span> 
 <span style="display:block;width:150px;border:0px solid gray;float:left;">
  <a target="_blank" title="sz399001-2" href="<%=basePath%>ws/codes.zc?code=sz399001">&nbsp;深证成指</a></span>
 </span>
</center> --%>
<!-- 
<span style="border:0px solid red;">
 <input id="kw" name="wd" class="s_ipt" value="输入股票、基金、国债代码或者名称" 
 onfocus="if(this.value=='输入股票、基金、国债代码或者名称'){this.value='';}"  onblur="if(this.value==''){this.value='输入股票、基金、国债代码或者名称';}" 
 maxlength="255" autocomplete="off" style="border:1px solid gray;">
</span>
<span ><input type="submit" id="su" value="搜索一下"  onclick="alert(this.value);" class="bg s_btn"></span><br>
</center>
 -->
 <%--  <center>
    <div class="am-g am-margin-left-xl am-margin-right-xl">
        <c:forEach var="vo" items="${lst}" varStatus="status">
         <div class="am-u-sm-1 am-u-end">
		       <a target='_blank'   title='<c:out value='${vo.code}-${status.index}' default='wang'/>'
		       href="<%=basePath%>qutoes/main.jsp?code=<c:out value='${vo.code}' default='wang'/>">
		       &nbsp;<c:out value="${vo.name}"   default="wang" />
				</a> 
	       </div>
	    </c:forEach> 
	 </div>
	 </center>  am-margin-left-xl am-margin-right-xl--%> 
    <div class="am-g ">
         <div class="am-u-sm-1 am-u-end am-text-center" ng-repeat="vo in stocks">
		       <a target='_blank'   title='{{$index+1}}-{{vo.name}}-{{vo.code}}'
		       href="<%=basePath%>ws/line.zc?code={{vo.code}}">
		       &nbsp;{{vo.name}}
				</a> 
	       </div>
	       <!-- <div class="am-u-sm-2 am-u-end am-text-center">更多...</div> -->
	  </div>   
	  
	  <!-- <div class="am-g am-margin-left-xl am-margin-right-xl" ng-show="true">
	   <div class="am-u-sm-12 am-text-center" >
	   		<button type="button" ng-click ="addNum(30)" class="am-btn am-btn-default am-btn-block">
	   			<span class="am-icon-caret-down">+30</span>
	   		</button>
	   </div>
	  </div>
	  <div class="am-g am-margin-left-xl am-margin-right-xl" ng-show="true">
	   <div class="am-u-sm-12 am-text-center" >
	   		<button type="button" ng-click ="addNum(-30)" class="am-btn am-btn-default am-btn-block">
	   			<span class="am-icon-caret-down">-30</span>
	   		</button>
	   </div>
	  </div>  -->
	  <center>
	  <span style="position:fixed;bottom:0px;right:30%;left:30%;">
		<a href="http://quote.eastmoney.com" target="window.top">东方财富</a>&nbsp;&nbsp;
		<a href="http://www.10jqka.com.cn" target="window.top">同花顺</a>&nbsp;&nbsp;
		<a href="http://gupiao.baidu.com" target="window.top">百度股市通</a>&nbsp;&nbsp;<br>
		Copyright &copy; 2015.iechenyb All rights reserved.
	</span>
	</center>
	 <!-- ECharts单文件引入 -->
     <script src="<%=basePath%>js/jquery.min.js"></script>
      <script src="<%=basePath%>js/jquery.js"></script>
      <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
      <script src="<%=basePath%>js/my/angular.js"></script>
      <script src="<%=basePath%>echarts/echarts-all.js"></script>
      <script src="<%=basePath%>js/my/index.js"></script>
</body>
</html>
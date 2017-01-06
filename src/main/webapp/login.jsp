<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>股往金来-</title>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
<script src="<%=basePath%>js/tongji.js?version=Math.random()"></script>  
</head>
<body style='margin-left:10%;margin-right:10%'>

<form class="am-form" method="post" target='_self' action="<%=basePath%>user/login.zc">
 <input type="hidden" value="<%=request.getParameter("page")%>" name="page"/>
  <table class="am-table1" width="80%"  border=0>
    <tr>
        <td colspan=2 align='center' >
      		 用户登陆
        </td>
   </tr>
   <tr>
       <td  align='right' >
      		 <label for="doc-ipt-email-1">用户名&nbsp;&nbsp;&nbsp;&nbsp; </label>
        </td>
        <td align='center' >
      		 <input type="text" class="am-form-field am-radius" minlength="3" required
      placeholder="手机号、邮箱、字符和字母组合的不超出16个字符" name="username"/>
        </td>
   </tr>
   <tr>
        <td align='right' height="80px;">
      		 <label for="doc-ipt-pwd-1">密码&nbsp;&nbsp;&nbsp;&nbsp;</label>
        </td>
        <td align='center' >
      		<input type="password" class="" id="doc-ipt-pwd-1" placeholder="密码" name="password" required>
        </td>
   </tr>
    <tr>
        <td align='center' colspan=2 >
      		 <button type="submit" onclick="" class="am-btn am-btn-secondary">登陆</button> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      		<a href="<%=basePath%>findPassword.jsp">忘记密码 </a>
        </td>
   </tr>
 </table>
 
  <%-- <fieldset>
    <legend>用户登陆</legend>
     <div class="am-form-group">
      <label for="doc-ipt-email-1">用户名 </label>
      <input type="text" class="am-form-field am-radius" minlength="3" required
      placeholder="手机号、邮箱、字符和字母组合的不超出16个字符" name="username"/>
    </div>
    <div class="am-form-group">
      <label for="doc-ipt-pwd-1">密码</label>
      <input type="password" class="" id="doc-ipt-pwd-1" placeholder="密码" name="password" required>
    </div> 
    <center>
    <button type="submit" onclick="" class="am-btn am-btn-secondary">登陆</button> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=basePath%>findPassword.jsp">忘记密码 </a>
     --%></center>
     ${msg}
  </fieldset>
</form>
</body>
</html>
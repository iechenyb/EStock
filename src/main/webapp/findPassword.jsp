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
<form class="am-form" method="post" action="<%=basePath%>user/resetScret.zc">
  <fieldset>
    <legend>用户登陆</legend>
     <div class="am-form-group">
      <label for="doc-ipt-email-1">用户名 </label>
      <input type="text" class="am-form-field am-radius" minlength="3" required
      placeholder="手机号、邮箱、字符和字母组合的不超出16个字符" name="username"/>
    </div>
     <div class="am-form-group">
      <label for="doc-ipt-email-1">邮箱</label>
      <input type="email" class="" id="doc-ipt-email-1" placeholder="注册时填写的电子邮箱" name="email" required>
    </div>
    <center>
       <button type="submit" class="am-btn am-btn-secondary">找回</button> 
    </center>
     ${msg}
  </fieldset>
</form>
</body>
</html>
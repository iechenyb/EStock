<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>股往金来-注册</title>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
</head>
<body style='margin-left:10%;margin-right:10%;background:url(cfjh/images/backgrounds/0.jpg) top center no-repeat; background-size:cover;' >
<form class="am-form" method="post" action="<%=basePath%>user/register.zc">
  <fieldset>
    <legend><b>用户注册</b></legend>
     <div class="am-form-group">
      <label for="doc-ipt-email-1">用户名 </label>
      <input type="text" class="am-form-field am-radius" minlength="3" required
      placeholder="手机号、邮箱、字符和字母组合的不超出16个字符" name="username"/>
    </div>
    <div class="am-form-group">
      <label for="doc-ipt-email-1">手机号码</label>
      <input type="text" class="am-form-field am-radius" placeholder="手机号码" name="phone" required/>
    </div>
    <p></p>
    <div class="am-form-group">
      <label for="doc-ipt-email-1">邮箱</label>
      <input type="email" class="" id="doc-ipt-email-1" placeholder="电子邮件" name="email" required>
    </div>

    <div class="am-form-group">
      <label for="doc-ipt-pwd-1">密码</label>
      <input type="password" class="" id="doc-ipt-pwd-1" placeholder="密码" name="password" id='p1' required>
    </div> 
    <div class="am-form-group">
      <label for="doc-ipt-pwd-1">确认密码</label>
      <input type="password" class="" id="doc-ipt-pwd-2" placeholder="确认密码" name="password_" id='p2' required>
    </div> 
    <center><button type="submit" class="am-btn am-btn-secondary">注册</button></center>
  </fieldset>
</form>
</body>
</html>
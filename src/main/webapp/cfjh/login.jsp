<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>cfjh/css/supersized.css">
<link rel="stylesheet" href="<%=basePath%>cfjh/css/login.css">
<link href="<%=basePath%>/cfjh/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>cfjh/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>cfjh/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>cfjh/js/tooltips.js"></script>
<script type="text/javascript" src="<%=basePath%>cfjh/js/login.js"></script>
<input type='hidden' id='basePath' value='<%=basePath%>'></input>
<title>股往金来-登陆</title>
</head>
<body>
<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				  <div style="color: #353535; font-family: 微软雅黑; font-size: 36px; padding:0px; font-weight:bold;">
    			<span style="text-shadow: 2px 2px 2px #dd0,2px 2px 2px #ff0, 0px 0px 10px #0ff, 0px 0px 20px #f0f;">
    			股往今来
    			</span>
 				</div>
			</div>		
			<div class="login_form">
				<form action="<%=basePath%>user/login.zc" id="login_form" method="post">
				<input type="hidden" value="<%=request.getParameter("page")%>" name="page" id="page"/>
					<div class="form-group">
						<label for="j_username" class="t">用户名：</label> 
						<input id="username" value="" name="username" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="password" value="" name="password" type="password" 
						class="password form-control x319 in">
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						 <input id="yzm" name="yzm" type="text" value='' class="form-control x164 in">
            			<img alt="验证码看不清，换一张" src="<%=basePath%>/DrawImage?createTypeFlag=nl" onclick="changeImg(this,'nl')">
					</div>
					<div class="form-group">
						<label class="t"></label>
						<label for="j_remember" class="m">
						<input id="j_remember" type="checkbox" value="true">&nbsp;记住登陆账号!</label>
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      					<a href="<%=basePath%>findPassword.jsp"><b>忘记密码</b> </a>
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp </button>
						<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
		<div class="bottom">Copyright iechenyb 2015-2016 <a href="<%=basePath%>qutoes/index.jsp">系统首页</a></div>
	</div>
</div>
 <script type="text/javascript">
    //刷新验证码
    function changeImg(obj,createTypeFlag){
        document.getElementById(obj.id).src="<%=basePath%>/DrawImage?createTypeFlag="+createTypeFlag+"&"+Math.random();
    }
    </script>
<!-- Javascript -->
<script src="<%=basePath%>cfjh/js/supersized.3.2.7.min.js"></script>
<script src="<%=basePath%>cfjh/js/supersized-init.js"></script>
<script src="<%=basePath%>cfjh/js/scripts.js"></script>
</body>
</html>
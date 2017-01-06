<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>验证码Case</title>
    <script type="text/javascript">
    //刷新验证码
    function changeImg(obj,createTypeFlag){
        document.getElementById(obj.id).src="<%=basePath%>/DrawImage?createTypeFlag="+createTypeFlag+"&"+Math.random();
    }
    </script>
  </head>
  
  <body>
        <form action="<%=basePath%>CheckServlet" method="post">
数字字母混合验证码：<input type="text" name="nlValidateCode"/>
            <img alt="验证码看不清，换一张" src="<%=basePath%>/DrawImage?createTypeFlag=nl" id="validateCodeImg1"  onclick="changeImg(this,'nl')">
            <br/>
            中文验证码：<input type="text" name="chValidateCode"/>
            <img alt="验证码看不清，换一张" src="<%=basePath%>/DrawImage?createTypeFlag=ch" id="validateCodeImg2"  onclick="changeImg(this,'ch')">
            <br/>
            英文验证码：<input type="text" name="lValidateCode"/>
            <img alt="验证码看不清，换一张" src="<%=basePath%>/DrawImage?createTypeFlag=l" id="validateCodeImg3"  onclick="changeImg(this,'l')">
            <br/>
            数字验证码：<input type="text" name="nValidateCode"/>
            <img alt="验证码看不清，换一张" src="<%=basePath%>/DrawImage?createTypeFlag=n" id="validateCodeImg4"  onclick="changeImg(this,'n')">
            <br/>
            <input type="submit" value="提交">
        </form>
  </body>
</html>
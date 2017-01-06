<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>股往金来</title>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="<%=basePath%>css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
<script type="text/javascript">
function setValue(msg){
	document.getElementById("upload").innerHTML="<font color='blue'>"+msg+"</font>";
}
</script>
</head>
<body style='margin-left:10%;margin-right:10%;background:url(../cfjh/images/backgrounds/0.jpg) top center no-repeat; background-size:cover;'><!-- enctype="multipart/form-data" -->
<form class="am-form" method="post" action="<%=basePath%>user/idea.zc" enctype="multipart/form-data"  >
  <fieldset>
    <legend><b>说出你的Idea</b></legend>
  <div class="am-form-group am-form-success am-form-icon am-form-feedback">
      <label class="am-radio-inline">
        <input type="radio"  checked=true value="1" name="userType">会员 
      </label>
      <label class="am-radio-inline">
        <input type="radio"  value="2"  name="userType">匿名
      </label>
    </div>
    <div class="am-form-group am-form-success am-form-icon am-form-feedback">
      <label for="doc-ipt-email-1">联系方式</label>
      <input type="text" class="am-form-field am-radius" placeholder="手机号码或者QQ" name="phone"/>
    </div>
    <p></p>
    <div class="am-form-group am-form-success am-form-icon am-form-feedback">
      <label for="doc-ipt-email-1">邮件</label>
      <input type="text" class="am-form-field am-radius" id="doc-ipt-email-1" placeholder="输入电子邮件" name="email">
    </div>
    <!-- <div class="am-form-group">
      <label for="doc-ipt-file-1">需求或者意见文档</label>
      <input type="file" id="doc-ipt-file-1" name="file">
      <p class="am-form-help"></p>
    </div> alert(document.getElementById('upload').value)-->
    <div class="am-form-group am-form-file">
      <label for="doc-ipt-file-2">需求或者意见文档</label>
      <div>
        <button type="button" class="am-btn am-btn-default am-btn-sm">
          <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button> 
          <i class="am-icon-cloud-upload" id='upload'></i>
      </div>
      <input type="file" id="doc-ipt-file-2" name='file' onchange="setValue(this.value)" value=""/>
    </div>
    <div class="am-form-group am-form-success am-form-icon am-form-feedback">
     <label for="doc-ipt-type-1">意见类型</label><br>
      <label class="am-radio-inline">
        <input type="radio"  checked=true value="1" name="ideaType"> 期待功能 
      </label>
      <label class="am-radio-inline">
        <input type="radio" value="1" name="ideaType"> 改善功能
      </label>
      <label class="am-radio-inline">
        <input type="radio" value="3" name="ideaType"> 上传需求
      </label>
    </div>  
    <div class="am-form-group am-form-success am-form-icon am-form-feedback">
      <label for="doc-ta-1">意见说明：</label>
      <textarea class="am-form-field am-radius" rows="4" id="doc-ta-1" name="message" placeholder="您的宝贵意见"></textarea>
    </div>
    <center><button type="submit" class="am-btn am-btn-primary">提交意见</button></center>
  </fieldset>
</form>
</body>
</html>
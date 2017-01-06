<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String basePath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Spring上传文件测试</title>
</head>
<body>
   <form action="<%=basePath%>file/upload.zc" method="post" enctype="multipart/form-data">
            选择文件<input type="file" name="file">
            <input type="submit" value="上传">
   </form>
   <form action="<%=basePath%>file/upload2.zc" method="post" enctype="multipart/form-data">
            选择文件<input type="file" name="file">
            <input type="submit" value="上传">
   </form>
   <form action="<%=basePath%>file/upload3.zc" method="post" enctype="multipart/form-data">
            选择文件<input type="file" name="file">
            <input type="submit" value="上传">
   </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>split page</title>
<style type="text/css">
#page{margin:5px; padding-bottom:20px; padding-top:20px;} 
#page a{border:1px solid #003399;padding:5px;margin:2px;background-color:#FFFFFF;color:#003300;height:18px; } 
#page a:hover{font-size:14px; color:#FFFFFF; background-color:#003399;padding:5px;margin:2px;height:20px;}
</style>
</head>
<body>
<center>
<div id=page>记录条 共4页 每页20条 
<a href=?id=74&page=1>第一页</a> 
<a href=?id=74&page=2>pre页</a> 
<a href=?id=74&page=1 class='sf'>1</a> 
<a href=?id=74&page=2 class='sf'>2</a> 
<a href=?id=74&page=3 class='sf'>3</a> 
<a href=?id=74&page=4 class='sf'>4</a> 
<a href=?id=74&page=2>下一页</a> 
<a href=?id=74&page=4>最后一页</a> 
</div>
</center>
</body>
</html>
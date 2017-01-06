<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>spilt page</title>
<style>
<style type="text/css">
* { margin:0; padding:0;}
body { font-size:12px; font-family:Verdana;}
a { color:#333; text-decoration:none;}
ul { list-style:none;}
#pagelist {width:600px; margin:30px auto; padding:6px 0px; height:20px;}
#pagelist ul li { float:left; border:1px solid #5d9cdf; height:20px; line-height:20px; margin:0px 2px;}
#pagelist ul li a, .pageinfo { display:block; padding:0px 6px; background:#e6f2fe;}
.pageinfo  { color:#555;}
.current { background:#a9d2ff; display:block; padding:0px 6px; font-weight:bold;}
</style>
</head>
<body>
<center>
<div id="pagelist">
  <ul>
    <li><a href="#">首页</a></li>
    <li><a href="#">上页</a></li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li class="current">3</li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li><a href="#">下页</a></li>
    <li><a href="#">尾页</a></li>
    <li class="pageinfo">第3页</li>
    <li class="pageinfo">共8页</li>
  </ul>
</div>
</center>
</body>
</html>
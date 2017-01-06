<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>横向菜单</title>
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
<script src="<%=basePath%>amazeui/js/handlebars.min.js"></script>
<script src="<%=basePath%>amazeui/js/amazeui.widgets.helper.js"></script>
<script src="<%=basePath%>amazeui/js/handlebars.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
</head>
<body>
  <header data-am-widget="header"
          class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="#left-link" class="">
              <span class="am-header-nav-title">
                                 首页
              </span>
                <i class="am-header-icon am-icon-home"></i>
          </a>
      </div>
      <h1 class="am-header-title">
          Amaze UI
      </h1>
      <div class="am-header-right am-header-nav">
          <a href="#right-link" class="">
              <span class="am-header-nav-title">
                       横排菜单             
              </span>
          </a>
      </div>
  </header>
 <nav data-am-widget="menu" class="am-menu  am-menu-slide1"  
    
    
     data-am-menu-collapse
    
    > 
    <a href="javascript: void(0)" class="am-menu-toggle">
          <i class="am-menu-toggle-icon am-icon-bars"></i>
    </a>


      <ul class="am-menu-nav am-avg-sm-4 am-collapse">
          <li class="am-parent">
            <a href="##" class="" >公司</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-2 ">
                  <li class="">
                    <a href="##" class="" >公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >人物</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >趋势</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >投融资</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业人物</a>
                  </li>
                  <li class="am-menu-nav-channel"><a href="##" class="" title="公司">进入栏目 &raquo;</a></li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="##" class="" >人物</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-3 ">
                  <li class="">
                    <a href="##" class="" >公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >人物</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >趋势</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >投融资</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业人物</a>
                  </li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="#c3" class="" >趋势</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-4 ">
                  <li class="">
                    <a href="##" class="" >公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >人物</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >趋势</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >投融资</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业人物</a>
                  </li>
                  <li class="am-menu-nav-channel"><a href="#c3" class="" title="趋势">泥煤 &raquo;</a></li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="##" class="" >投融资</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-3 ">
                  <li class="">
                    <a href="##" class="" >公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >人物</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >趋势</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >投融资</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业公司</a>
                  </li>
                  <li class="">
                    <a href="##" class="" >创业人物</a>
                  </li>
              </ul>
          </li>
          <li class="">
            <a href="##" class="" >创业公司</a>
          </li>
          <li class="">
            <a href="##" class="" >创业人物</a>
          </li>
      </ul>

  </nav>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=gbk"    pageEncoding="gbk"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html  ng-app="app">
  <head>
      <link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
      <link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
  </head>
  <body ng-controller="controller">
    Your name: <input type="text" ng-model="yourname" placeholder="World" value="chenyb">
    <hr>
   <ul>
   
    <li ng-repeat="phone in phones">
      <font size=2 color=red>{{phone.name}}</font>
    <p><font size=2 color=red>{{phone.snippet}} </font></p>
    </li>
   
  </ul>
  <button ng-click="testOnclick()">µ„Œ“£°</button>
<p>{{ count }}</p>

    Hello <font size=5 color=red>{{yourname || 'World'}}!</font><br>
    the param from angular.js  is  <font size=5 color=red>{{test}}</font><br>
    <input type="text" id="test" name="test" value="haha" placeholder="World"></br>
 <script src="<%=basePath%>js/jquery.min.js"></script>
 <script src="<%=basePath%>js/jquery.js"></script>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
      <script src="<%=basePath%>js/my/angular.js"></script>
  </body>
</html>
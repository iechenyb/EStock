<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%  
String path=application.getRealPath(request.getRequestURI());
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<input type="hidden" id="meta" value=<%=request.getContextPath()%>/>
<title>股往金来-${code}</title>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<%-- <input type="hidden" name='code' id ='code' value='${code}'/> --%>
<input type="hidden" name='code' id ='code' value='<%=request.getParameter("code")%>'/> 
</head>
<body ng-controller="controller" style="background-color:black;">
      <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
      <center>
      <div id="main" style="height:550px;" class="am-u-sm-12"></div>
      <div  style="height:630px;" class="am-u-sm-6 border ">
       <table class="am-table am-table-bd">
            <thead>
              <tr>
	              <td colspan=2 height="150px"></td>
              </tr>
              <tr>
	              <td colspan=2 align=center>
	              	<b><font size=9 color=white>&nbsp;上证:</font><font size=9 color={{dpzs.colorsh}}>{{dpzs.shzsprice}}({{dpzs.shzsA}}%)</font> 
					<br>
	              	<font size=9 color=white>深成:</font><font size=9 color={{dpzs.colorsz}}>{{dpzs.szzsprice}}({{dpzs.szzsA}}%)</font></b>
	              </td>
              </tr>
            <tr>
	            <td align=center><font size=9 color=white><b>涨跌类别</b></font></td>
	            <td align=center><font size=9 color=white><b>个数（家）</b></font></td>
	        </tr>
	       </thead>
	       <tbody>
              <tr ng-repeat="vo in zdfxList">
	              <td align=center><font size=8 color=white><b>{{vo.name}}</b></font></td>
	              <td align=center><font size=8 color=white><b>{{vo.value}}</b></font></td>
              </tr>
            </tbody>
            </table>
      </div>
      </center>
      <script src="<%=basePath%>js/jquery.min.js"></script>
      <script src="<%=basePath%>js/jquery.js"></script>
      <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
      <script src="<%=basePath%>echarts/echarts-all.js"></script>
      <script src="<%=basePath%>phone/js/bing.js"></script>
      <script src="<%=basePath%>js/tongji.js?version=Math.random()"></script>  
</body>
</html>
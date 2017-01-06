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
<title>大盘涨跌分析</title>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<%-- <input type="hidden" name='code' id ='code' value='${code}'/> --%>
<input type="hidden" name='code' id ='code' value='<%=request.getParameter("code")%>'/> 
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<script type="text/javascript" src="<%=basePath%>push/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>push/socket.io.js"></script>
</head>
<body ng-controller="controller">
      <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
      <center>
      <div class="am-g">
       <div class="am-u-sm-2">&nbsp;</div>	
      <div id="main" style="height:400px;" class="am-u-sm-8 border"></div>
       <div class="am-u-sm-2">&nbsp;</div>	
      </div>
      <div class="am-g">
       <div class="am-u-sm-3">&nbsp;</div>	
      <div  style="height:630px;" class="am-u-sm-6 border ">
       <table class="am-table am-table-bd">
            <thead>
               <tr>
	              <td colspan=2 height="80px"><div id='time'></div></td>
              </tr> 
              <tr>
	              <td colspan=2 align=center>
	              <a style="display:block" href="<%=basePath%>ws/line.zc?code=sh000001" target="_blank">
	              	上证指数:<font size=4 color={{dpzs.colorsh}}>
	              	{{dpzs.shzsprice}}({{dpzs.shzsA1}},{{dpzs.shzsA}}%)</font> </a>
	              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              	<a style='display:block' href="<%=basePath%>ws/line.zc?code=sz399001" target="_blank">
	              	深证成指:<font size=4 color={{dpzs.colorsz}}>{{dpzs.szzsprice}}({{dpzs.szzsA1}},{{dpzs.szzsA}}%)</font></a>
	              </td>
              </tr>
            <tr>
	            <td align=center><font size=4><b>涨跌类别</b></font></td>
	            <td align=center><font size=4><b>个数（家）</b></font></td>
	        </tr>
	       </thead>
	       <tbody>
              <tr ng-repeat="vo in zdfxList">
	              <td align=center><font size=3><b>{{vo.name}}</b></font></td>
	              <td align=center><font size=3><b><a style="display:block" href="<%=basePath%>ws/zdfx.zc?flag={{vo.type}}" target="_blank">{{vo.value}}</a></b></font></td>
              </tr>
              <tr >
	              <td align=center colspan=><b>总计</b></td>
	              <td align=center colspan=><font color='red'><b>{{total}}</b></font></td>
              </tr>
            </tbody>
            </table>
      </div>
       <div class="am-u-sm-3">&nbsp;</div>	
      </div>
      <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1"
		id="my-modal-loading">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">正在加载....</div>
			<div class="am-modal-bd">
				<!--  <span class="am-icon-spinner am-icon-spin">
				
				</span> -->
				<img src="<%=basePath%>img/load.gif"></img> 
			</div>
		</div>
	</div>
      <a id='page' href="#" target='_blank'></a>
      </center>
      <script src="<%=basePath%>js/jquery.min.js"></script>
      <script src="<%=basePath%>js/jquery.js"></script>
      <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
       <script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
      <script src="<%=basePath%>echarts/echarts-all.js"></script>
      <script src="<%=basePath%>phone/js/bing.js"></script>
      <script src="<%=basePath%>js/tongji.js?version=Math.random()"></script>  
</body>
</html>
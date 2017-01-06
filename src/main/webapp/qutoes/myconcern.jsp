<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
Object object = request.getSession().getAttribute("username");
String username = "";
if (object != null) {
	username = object.toString();
}
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<link rel="icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
<input type="hidden" id="basePath" value=<%=basePath%>/>
<input type="hidden" value="<%=username%>" id='username' name='username'/>
<script type="text/javascript" src="<%=basePath%>push/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>push/socket.io.js"></script>
<title>股往金来</title>
</head>
<body ng-controller='controller'>
<div id='time'></div>
 <center>
 <hr>
 <table class="am-table am-table-bd am-table-bdrs am-table-striped">
    <thead>
        <tr>
            <th>股票代码</th>
            <th>股票名称</th>
            <th>最新价</th>
            <th>涨跌幅</th>
            <th>涨跌</th>
            <th>昨收</th>
            <th>开盘</th>
            <th>最高</th>
            <th>最低</th>
            <th>成交量</th>
            <th>成交额</th>
            <th>&nbsp;</th>
        </tr>
    </thead>
     <tbody>
        <tr ng-repeat="vo in concers">
            <td> 
	            <a target='_blank'   title='{{vo.NAME_}}'
			       href="<%=basePath%>ws/line.zc?code={{vo.CODE_}}">
			       &nbsp;<b>{{vo.CODEONLY}}</b>
				</a> 
			</td>
            <td> <b>{{vo.NAME_}}</b></td>
            <td> <font color='{{vo.color}}'><b>{{vo.PRICE_}}</b></font></td>
            <td><font color='{{vo.color}}'><b>{{vo.A}}</b></font> </td>
            <td> <font color='{{vo.color}}'><b>{{vo.A1}}</b></font> </td>
            <td><font color='{{vo.color}}'><b>{{vo.PRECLOSE_}}</b></font> </td>
            <td><font color='{{vo.color}}'><b>{{vo.OPEN_}}</b></font> </td>
            <td> <font color='{{vo.color}}'><b>{{vo.HIGH_}}</b></font> </td>
            <td><font color='{{vo.color}}'><b>{{vo.LOW_}}</b></font> </td>
            <td><font color='{{vo.color}}'><b>{{vo.VOLUME}}</b></font> </td>
            <td><font color='{{vo.color}}'><b>{{vo.TURNVOLUME}}</b></font> </td>
            <td>
            <!--  <a data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 225}"   title='<c:out value='{{vo.NAME_}}' default='wang'/>'
			       href="<%=basePath%>phone/close_.jsp?code=<c:out value='{{vo.CODE_}}' default='wang'/>">
			       &nbsp;<b>收盘行情</b>
			</a> -->
			<a ng-click="showDialog(vo)" title='{{vo.NAME_}}' class="ng-binding"><b>收盘行情</b></a>
            <a target='_self'   title='{{vo.NAME_}}'
			       href="<%=basePath%>ws/deleteMyConcern.zc?code={{vo.CODE_}}">
			       &nbsp;<b>取消自选</b>
			</a> 
			</td>
        </tr>
   </tbody>
</table>


<div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">
					收盘历史走势 <a href="javascript: void(0)"
						class="am-close am-close-spin" data-am-modal-close>&times;</a>
				</div>
				<div id="longShortRateTrend" style="height: 80%"></div>
			</div>
</div>
</center>
 <script src="<%=basePath%>js/jquery.min.js"></script>
 <script src="<%=basePath%>js/jquery.js"></script>
 <script src="<%=basePath%>echarts/echarts-all.js"></script>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
 <script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
 <script src="<%=basePath%>js/my/myconcern.js"></script>
</body>
</html>
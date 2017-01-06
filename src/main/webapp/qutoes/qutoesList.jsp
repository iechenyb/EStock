<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<title>股往金来</title>
</head>
<body ng-controller='controller'>
<div><font color='gray' size=4>菜单->${title}</font></div>
<hr>
 <center>
 <div class="am-g" style="height:24px;border:0px red solid;">
		<div class="am-u-sm-4"><a href="javascript:void(0)">记录${page.recordCount}条 共${page.pageCount}页 每页${page.pageSize}条  第${page.currentPage}页 </a></div>
		<div class="am-u-sm-2"><a href="<%=basePath%>ws/zdfx.zc?page=1&cmd=first&flag=${flag}" onclick="$('#my-modal-loading').modal();">第一页</a> </div>
		<div class="am-u-sm-2"><a href="<%=basePath%>ws/zdfx.zc?page=${page.currentPage}&cmd=pre&flag=${flag}" onclick="$('#my-modal-loading').modal();">上一页 </a> </div>		
		<div class="am-u-sm-2"><a href="<%=basePath%>ws/zdfx.zc?page=${page.currentPage}&cmd=next&flag=${flag}" onclick="$('#my-modal-loading').modal();">下一页</a> </div>
		<div class="am-u-sm-2 am-u-end "><a href="<%=basePath%>ws/zdfx.zc?page=1&cmd=last&flag=${flag}" onclick="$('#my-modal-loading').modal();">最后一页</a> </div>
</div>
 <table class="am-table am-table-bd am-table-bdrs am-table-striped">
    <thead>
        <tr>
            <th> &nbsp;</th>
            <th>序号</th>
            <th>股票代码</th>
            <th>股票名称</th>
            <th>最股价</th>
            <th>涨跌幅</th>
            <th>涨跌</th>
            <th>昨收</th>
            <th>开盘</th>
            <th>最高</th>
            <th>最低</th>
            <th>成交量</th>
            <th>成交额</th>
            <th>收盘行情</th>
        </tr>
    </thead>
     <tbody>
     <c:forEach var="vo" items="${data}" varStatus="status">
        <tr>
            <td> &nbsp;</td>
            <td> <b>${vo.XH}</b></td>
            <td> 
	            <a target='_blank'   title='<c:out value='${vo.CODE_}' default='wang'/>'
			       href="<%=basePath%>ws/line.zc?code=<c:out value='${vo.CODE_}' default='wang'/>">
			       &nbsp;<b><c:out value="${vo.CODEONLY}"   default="wang" /></b>
				</a> 
			</td>
            <td> <b>${vo.NAME_}</b></td>
            <td> <font color='${vo.color}'><b>${vo.PRICE_}</b></font></td>
            <td><font color='${vo.color}'><b>${vo.A}</b></font> </td>
            <td> <font color='${vo.color}'><b>${vo.A1}</b></font> </td>
            <td><font color='${vo.color}'><b>${vo.PRECLOSE_}</b></font> </td>
            <td><font color='${vo.color}'><b>${vo.OPEN_}</b></font> </td>
            <td> <font color='${vo.color}'><b>${vo.HIGH_}</b></font> </td>
            <td><font color='${vo.color}'><b>${vo.LOW_}</b></font> </td>
            <td><font color='${vo.color}'><b>${vo.VOLUME}</b></font> </td>
            <td><font color='${vo.color}'><b>${vo.TURNVOLUME}</b></font> </td>
            <td><a ng-click="showDialog('${vo.CODE_}')" title='${vo.NAME_}' class="ng-binding"><b>收盘行情</b></a>
            <a ng-click="add('${vo.CODE_}')" title='${vo.NAME_}' class="ng-binding" ><b>添加到自选股</b></a></div>
            </td>
        </tr>
     </c:forEach>
   </tbody>
</table>
<div class="am-g" style="height:24px;">
		<div class="am-u-sm-4"><a href="javascript:void(0)">记录${page.recordCount}条 共${page.pageCount}页 每页${page.pageSize}条  第${page.currentPage}页 </a></div>
		<div class="am-u-sm-2"><a href="<%=basePath%>ws/zdfx.zc?page=1&cmd=first&flag=${flag}" onclick="$('#my-modal-loading').modal();">第一页</a> </div>
		<div class="am-u-sm-2"><a href="<%=basePath%>ws/zdfx.zc?page=${page.currentPage}&cmd=pre&flag=${flag}" onclick="$('#my-modal-loading').modal();">上一页 </a> </div>		
		<div class="am-u-sm-2"><a href="<%=basePath%>ws/zdfx.zc?page=${page.currentPage}&cmd=next&flag=${flag}" onclick="$('#my-modal-loading').modal();">下一页</a> </div>
		<div class="am-u-sm-2 am-u-end "><a href="<%=basePath%>ws/zdfx.zc?page=1&cmd=last&flag=${flag}" onclick="$('#my-modal-loading').modal();">最后一页</a> </div>
	
</div>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">
					收盘历史走势 <a href="javascript: void(0)"
						class="am-close am-close-spin" data-am-modal-close>&times;</a>
				</div>
				<div id="longShortRateTrend" style="height: 80%"></div>
			</div>
</div>

<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1"
		id="my-modal-loading">
		<div class="am-modal-dialog">
			<div class="am-modal-hd">拼命加载中...</div>
			<div class="am-modal-bd">
				<!--  <span class="am-icon-spinner am-icon-spin">
				
				</span> -->
				<img src="<%=basePath%>img/load1.gif"></img> 
			</div>
		</div>
	</div>
</center>
 <script src="<%=basePath%>js/jquery.min.js"></script>
 <script src="<%=basePath%>js/jquery.js"></script>
 <script src="<%=basePath%>echarts/echarts-all.js"></script>
 <script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
 <script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
 <script src="<%=basePath%>js/my/qutoes.js"></script>
</body>
</html>
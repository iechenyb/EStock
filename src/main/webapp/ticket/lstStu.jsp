<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
	Object object = request.getSession().getAttribute("username");
	String username = "";
	if (object != null) {
		username = object.toString();
	}
	int total = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>售票大厅</title>
<script type="text/javascript">
function showWait(){
	document.getElementById("time").innerHTML="系统已执行时间："+1+"s";
	startTimer();
	document.getElementById('wait').style.visibility="visible";
}
var myVar;
function startTimer(){ 
    /*setInterval() 间隔指定的毫秒数不停地执行指定的代码*/
    myVar=setInterval(function(){myTimer()},1000);
}
var times = 1;
function myTimer()/* 定义一个得到本地时间的函数*/
{
	var d=new Date();
	var t=d.toLocaleTimeString();
	times ++;
	document.getElementById("time").innerHTML="系统已执行时间："+times+"s";
	//console.log(t);
}
function stopTimer()
{/* clearInterval() 方法用于停止 setInterval() 方法执行的函数代码*/
	clearInterval(myVar);
}
</script>
</head>
<body onload=''>
 <p>测试简介：参数信号量(Semaphore)、并发客户端数、默认2个售票窗口,另外，每个客户端可随机购买1-100张火车票。</p>
 <p>Semaphore分为单值和多值两种，前者只能被一个线程获得，后者可以被若干个线程获得。</p>
 <p>以一个停车场是运作为例。为了简单起见，假设停车场只有三个车位，一开始三个车位都是空的。这时如果同时来了五辆车，看门人允许其中三辆不受阻碍的进入，然后放下车拦，剩下的车则必须在入口等待，此后来的车也都不得不在入口处等待。这时，有一辆车离开停车场，看门人得知后，打开车拦，放入一辆，如果又离开两辆，则又可以放入两辆，如此往复。</p>
 <p>在这个停车场系统中，车位是公共资源，每辆车好比一个线程，看门人起的就是信号量的作用。</p>
<center>
<div style="border:2px solid orange">
 <%-- <a href="<%=basePath%>ticket/sale.zc?reqs=100&limit=1&windows=2" target='_self'>开始购票</a> --%>
 <a href="<%=basePath%>ticket/list.zc?page=${page.currentPage}">刷新</a>
 <a href="<%=basePath%>ticket/add100.zc?num=100" target='_self'>放票100</a>&nbsp;&nbsp;&nbsp;&nbsp;当前票数&nbsp;&nbsp;<font size=5 color=red>${total}</font>
 <a href="<%=basePath%>ticket/clear.zc" onclick="showWait()">清空日志</a>
 </div>
  <div style="border:2px solid green">
  <form action="<%=basePath%>ticket/add100.zc" method='get'>
   	放票数量：<input name="num" value="100"/>
   <input type="submit" value="放   票" class="btn2"  />
  </form>
  </div>
   <div style="border:2px solid orange">
 <form action="<%=basePath%>ticket/sale.zc" method='get'>
	 窗口数量：<input name="windows" value="${param.windows}"/>
	 一个窗口处理的线程数：<input name="reqs" value="${param.reqs}"/>
	 每个线程最多可购买票数：<input name="limit" value="${param.limit}"/>
	 信号量：<input name="sema" value="${param.sema}"/>
    <input type="submit" value="购   票" onclick="showWait()" class="btn2"  />
  </form>
  </div>
</center>
<div style="border:2px solid green">
      <font size=5 color=red>成功交易总票数：${ttotal}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前页购买总量=${xx}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购票系统执行结果：${infor}</font>
      </div>
<table border=0  width="100%">
   <tr align="center">
   <td colspan=6><b><a href="javascript:void(0)">记录${page.recordCount}条 共${page.pageCount}页 每页${page.pageSize}条  第${page.currentPage}页 </a></b></td>  
   <td><b><a href="<%=basePath%>ticket/list.zc?page=1&cmd=first" onclick="showWait();">第一页</a></b></td>
   <td><b><a href="<%=basePath%>ticket/list.zc?page=${page.currentPage}&cmd=pre" onclick="showWait();">上一页 </a></b></td>
   <td><b><a href="<%=basePath%>ticket/list.zc?page=${page.currentPage}&cmd=next" onclick="showWait();">下一页</a> </b></td>
   <td><b><a href="<%=basePath%>ticket/list.zc?page=1&cmd=last" onclick="showWait();">最后一页</a></b></td>
   </tr>
</table>
   <table border=0  width="100%">
    <tr><td colspan=11> <center><font size="8" color='green'><b>火车票购买信息</b></font></center></td></tr>
   <tr align="center">
   <td><b>编号</b></td>  
   <td><b>[窗口编号]线程#请求数量</b></td>
   <td><b>购买前数量</b></td>
   <td><b>购买后数量</b></td>
   <td><b>实际购买数量</b></td>
   <td><b>系统扣减数量</b></td>
   <td><b>成功标志</b></td>
   <td><b>校验结果</b></td>
   <td><b>处理结果</b></td>
   <td><b>请求时间</b></td>
   <td><b>操作</b></td>
   <tr>
   <c:set var="sum" value="0"></c:set>
	 <c:forEach var="ticket" items="${list}" varStatus="status"> 	 
	 <c:if test="${ticket.tradenum == 0}"><tr align="center" style="background:gray"></c:if>
     <c:if test="${ticket.tradenum != 0}"><tr align="center" style="background:green"> </c:if>
          <td width="25px">&nbsp;&nbsp;&nbsp;${ ticket.xh}&nbsp;&nbsp;&nbsp;</td>  
          <td width=200px> <b><c:out value="${ticket.reqstr}" default="wang"/> </b>
          </td>          
          <td width=100px>
           <c:out value="${ticket.before_}" default="wang"/> 
          </td> 
          <td width=100px>
           <c:out value="${ticket.after_}" default="wang"/> 
          </td> 
           <td width=100px>
           <c:out value="${ticket.tradenum}" default="wang"/> 
           <c:set var="xx" value="${xx + ticket.tradenum }"/>
          </td> 
           <td width=100px>
           <c:out value="${ticket.before_-ticket.after_}" default="wang"/> 
          </td> 
           <td width=100px>
           <c:out value="${ticket.after_-ticket.before_+ticket.tradenum}" default="wang"/> 
          </td> 
          <td width=100px>
           <c:out value="${ticket.tradenum == 0?'失败':'成功'}" default="wang"/> 
          </td> 
          <td width=100px> <c:out value="${ticket.handlerresult}" default="wang"/> 
          </td> 
          <td width=240px> 
           <c:out value="${ticket.time_}" default="wang"/> 
          </td> 
          <td width=100px> 
           <a href="#">...</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">...</a>
          </td> 
          </tr> 
       </c:forEach> 
   </table>
    <div id="wait" style="filter:alpha(opacity=80); position:absolute; top:35%; left:35%; z-index:10; visibility:hidden; width: 617px; height: 210px;">
		<table WIDTH=80% BORDER=0 CELLSPACING=0 CELLPADDING=0>
			<tr>
			<td width=20%></td>
			<td bgcolor=#104A7B>
				<table WIDTH=100% height=200 BORDER=0 CELLSPACING=1 CELLPADDING=1>
				<tr><td bgcolor=#ffffff align=center>请稍候...</td></tr>
				<tr><td bgcolor=#ffffff align=center><img src="<%=basePath%>img/load.gif"></img></td></tr>
				<tr><td bgcolor=#ffffff align=center><div id='time'></div></td></tr>
				</table>
			</td>
			<td width=20%>
			</td>
			</tr>
		</table>
	</div> 
</body>
</html>
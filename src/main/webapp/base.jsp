<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%
	String basePath = "http://" + request.getServerName() + ":"
			+ request.getServerPort() + request.getContextPath() + "/";
	Object object = request.getSession().getAttribute("username");
	String username = "";
	if (object != null) {
		username = object.toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基础学习</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
<link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
<link rel="stylesheet" href="<%=basePath%>css/estock.css">
</head>
<body style="margin-top:10px;">
<hr>
	<div class="am-g">
	    <div class="am-u-sm-2">
	         <div style="color: #353535; font-family: 微软雅黑; font-size: 36px; padding:0px; font-weight:bold;height:60px;">
    			<span style="text-shadow: 2px 2px 2px #dd0,2px 2px 2px #ff0, 0px 0px 10px #0ff, 0px 0px 20px #f0f;">
    			<a style="color: #353535; font-family: 微软雅黑; font-size: 36px; padding:0px; font-weight:bold;height:60px;" href="<%=basePath%>" target="_blank">股往<img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="50px" height="40px"/>今来
    			</a>
    			</span>
 			  </div>
 		</div>		
		<div class="am-u-sm-2"><a href="12306.jsp" target="_blank">铁路干线分布</a></div>		
		<div class="am-u-sm-2"><a href="move.jsp" target="_blank">国内数据分布</a></div>
		<div class="am-u-sm-2"><a href="phone/analysQutoes_.jsp" target="_blank">今日涨跌统计</a></div>	
		<div class="am-u-sm-2"><a href="analysis/ndaydown.zc?days=4&minPrice=0&maxPrice=10" target="_blank"><b>连续跌涨分析</b></a></div>		
		<div class="am-u-sm-2"><a href="phone/close_.jsp?code=sh600868" target="_blank">收盘行情</a></div>
		<div class="am-u-sm-2"><a href="phone/mintuline.jsp?code=sh000001" target="_blank">上证指数</a></div>
		<div class="am-u-sm-2 am-u-end "><a href="phone/mintuline.jsp?code=sh600868" target="_blank">分钟行情线-1</a></div>
		<div class="am-u-sm-2 am-u-end "><a href="phone/qutoes.jsp?code=sh600868" target="_blank">分钟行情线-2</a></div>
		<div class="am-u-sm-2 am-u-end "><a href="qutoes/KEcharts.jsp?code=sh600868" target="_blank">K图</a></div>	
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="phone/header.jsp" target="_blank">手机信息版面</a></div>	
		<div class="am-u-sm-2"><a href="phone/index1.jsp" target="_blank">横向菜单</a></div>		
		<div class="am-u-sm-2"><a href="phone/index2.jsp" target="_blank">右侧滑出菜单</a></div>
		<div class="am-u-sm-2"><a href="phone/index3.jsp" target="_blank">横向菜单</a></div>		
		<div class="am-u-sm-2"><a href="phone/index4.jsp" target="_blank">左侧滑出菜单</a></div>
		<div class="am-u-sm-2"><a href="phone/index5.jsp" target="_blank">垂直纵向菜单</a></div>	
		<div class="am-u-sm-2 am-u-end "><a href="phone/accord.jsp" target="_blank">图片展示</a></div>	
	</div>
	<hr>
	<div class="am-g">
		<div class="am-u-sm-2"><a href="wf/" target="_blank">工作流学习</a></div>		
		<div class="am-u-sm-2"><a href="ticket/list.zc" target="_blank" title="多线程测试"><b>火车站售票大厅</b></a></div>
		<div class="am-u-sm-2"><a href="push/cpu.jsp" target="_blank"><b>系统cpu监听</b></a></div>
		<div class="am-u-sm-2"><a href="push/index.jsp" target="_blank">sokeio推送测试</a></div>		
		<div class="am-u-sm-2 am-u-end "><a href="wait.jsp" target="_blank">页面加载动画</a></div>
	</div>
	<hr>
	<div class="am-g">
		<div class="am-u-sm-2"><a href="angular.jsp" target="_blank">Anjular学习</a></div>		
		<div class="am-u-sm-2"><a href="amazeui.jsp" target="_blank">Amazeui学习</a></div>
		<div class="am-u-sm-2"><a href="jstl/index.jsp" target="_blank">标准标签库</a></div>
		<div class="am-u-sm-2"><a href="jstl/index.jsp" target="_blank">自定义标签库</a></div>
		<div class="am-u-sm-2"><a href="jstl/index.jsp" target="_blank">自定义注解</a></div>
		<div class="am-u-sm-2 am-u-end"><a href="yzm/yzm.jsp" target="_blank"><b>验证码测试</b></a></div>		
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="druid/wiki/" target="_blank">Druid首页</a></div>		
		<div class="am-u-sm-2"><a href="druid/reset-all.json" target="_blank">Druid清除日志</a></div>		
		<div class="am-u-sm-6 am-u-end"><a href="druid/log-and-reset.json" target="_blank">Druid清除并重置日志</a></div>	
	</div>
	<hr>
	<div class="am-g">
		<div class="am-u-sm-2"><a href="services/ITest?wsdl" target="_blank" title="xfire到1.2.6就不在跟spring同步更新了，只支持3.2.*">WebService-Xfire</a></div>		
		<div class="am-u-sm-2"><a href="webservice" target="_blank">WebService-Axis</a></div>		
		<div class="am-u-sm-2 am-u-end"><a href="/services/" target="_blank">WebService-CXF</a></div>	
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="http://127.0.0.1:8161/">ActiveMQ-首页</a></div>
		<div class="am-u-sm-2"><a href="http://127.0.0.1:8161/admin/" title='(Messages Enqueued  = Number Of Pending Messages + Messages Dequeued ;)' target="_blank">ActiveMQ-Admin</a></div>		
		<div class="am-u-sm-2"><a href="jx/index.jsp" target="_blank">跨域测试(前端Jx)</a></div>		
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank">QWrap(前端)</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank">SpringAOP</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank">SpringIOC</a></div>	
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="#">Memecache</a></div>
		<div class="am-u-sm-2"><a href="#" title='' target="_blank">Redis</a></div>		
		<div class="am-u-sm-2"><a href="#" target="_blank">H2DB</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank">mogodb</a></div>		
	</div>
	<hr>
	<div class="am-g">	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank" title="分布式事务管理">Jtom</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank" title="分布式事务管理">Automikos</a></div>	
	</div>
	<hr>
	<div class="am-g">
        <div class="am-u-sm-2"><a href="<%=basePath%>upload/upload.jsp" target="_blank">单文件上传（标准）</a></div>
		<div class="am-u-sm-2"><a href="<%=basePath%>upload/MultiFileUpload.jsp" target="_blank">多文件上传（标准）</a></div>		
		<div class="am-u-sm-2"><a href="<%=basePath%>upload/uploadProgess.jsp" target="_blank" title="进度条监控">多文件上传（标准）</a></div>
		<div class="am-u-sm-2"><a href="<%=basePath%>upload/spring/upload.jsp" target="_blank">文件上传（Spring）</a></div>	
		<div class=""><a href="<%=basePath%>file/downList.zc" target="_blank">文件下载（通用）</a></div>
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="#">Nigix负载均衡</a></div>
		<div class="am-u-sm-2"><a href="#" title='' target="_blank">Nigix反向代理</a></div>		
		<div class="am-u-sm-2"><a href="#" target="_blank">Apache Hadoop1.x</a></div>
		<div class="am-u-sm-2"><a href="#" target="_blank">Apache Hadoop2.x</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank" title="开发接口定义">SosoApi</a></div>		
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="#">数据库锁级别</a></div>
		<div class="am-u-sm-2"><a href="#" title='' target="_blank">Cglib动态代理</a></div>		
		<div class="am-u-sm-2"><a href="#" target="_blank">sun动态代理</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="#" target="_blank" title="">设计模式</a></div>		
	</div>
	<hr>
	<div class="am-g">
	    <div class="am-u-sm-2"><a href="#">Shell编写</a></div>
		<div class="am-u-sm-2"><a href="#" title='' target="_blank">Linux命令</a></div>		
		<div class="am-u-sm-2"><a href="#" target="_blank">常用网站</a></div>	
		<div class="am-u-sm-2 am-u-end"><a href="pics.jsp" target="_blank" title="">图库</a></div>		
	</div>
</body>
</html>
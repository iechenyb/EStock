<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>股往今来</title>
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
<script src="<%=basePath%>js/jquery.js"></script>
<script src="<%=basePath%>angular/angular-1.0.1.min.js"></script>
<script src="<%=basePath%>js/my/nav.js"></script>
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
<input type="hidden" value="<%=username%>" id='username' name='username'/>
<input type="hidden" value="<%=basePath%>" id='basePath' name='basePath'/>
</head>
<body ng-controller="controller">
<div data-am-sticky>
  <header class="am-topbar am-topbar-inverse">
  <h1 class="am-topbar-brand">
    <a href="<%=basePath%>/nav.jsp">
    <%-- <img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="80px" height="50px"/> --%>
    </a>
  </h1>
  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}">
   <span class="am-sr-only">导航切换</span>
   <span class="am-icon-bars"></span>
   </button>
  <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
    <!--  -->
    <ul class="am-nav am-nav-pills am-topbar-nav"> 
       <!---->
       <li class="am-dropdown" data-am-dropdown id='dpzs'>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	沪深指数 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
         <!--  <li class="am-dropdown-header">&nbsp;</li> -->
          <li onclick="$('#dpzs').click();//hidden menu"> 
          	<a  style="display:block" href="<%=basePath%>ws/line.zc?code=sh000001" target='_blank'>上证指数</a>
          </li>
          <li class="" onclick="$('#dpzs').click();//hidden menu">
               <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz399001" target='_blank'>深证成指</a>
           </li>
           <li class="" onclick="$('#dpzs').click();//hidden menu">
               <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz399006" target='_blank'>创业板综</a>
           </li>
            <li class="" onclick="$('#dpzs').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>phone/analysQutoes_.jsp" target='_blank'>涨跌统计</a>
          </li> 
           <li class="" onclick="$('#dpzs').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/morezs.zc" target='pcframe'>更多...</a>
          </li>   
        </ul>
      </li>
      <!--industrys-->
      <li class="am-dropdown" data-am-dropdown id='hyfl'>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	行业分类 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
         <!--  <li class="am-dropdown-header">&nbsp;</li> -->
          <li onclick="$('#hyfl').click();//hidden menu" ng-repeat="industry in industrys"> 
          	<a  style="display:block" href="<%=basePath%>ws/hyfl.zc?flag={{industry.type}}" target='pcframe' title='{{industry.type}}'>{{industry.name}}</a>
          </li>
           <li class="" onclick="$('#hyfl').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/hyfl.zc?flag=all" target='pcframe'>更多...</a>
          </li>    
        </ul>
      </li>
      <!--industrys-->
      <li class="am-dropdown" data-am-dropdown id='hyfl'>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	涨跌分析<span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
         <li class="" onclick="$('#hyfl').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/zdfx.zc?flag=sz" target='pcframe'>上涨榜</a>
          </li>  
          <li class="" onclick="$('#hyfl').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/zdfx.zc?flag=xd" target='pcframe'>下跌榜</a>
          </li>
           <li class="" onclick="$('#hyfl').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/zdfx.zc?flag=1" target='pcframe'>涨停榜</a>
          </li>  
          <li class="" onclick="$('#hyfl').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/zdfx.zc?flag=2" target='pcframe'>跌停榜</a>
          </li>
          <li class="" onclick="$('#hyfl').click();//hidden menu">
             <a  style="display:block" href="<%=basePath%>ws/zdfx.zc?flag=tp" target='pcframe'>停牌榜</a>
          </li>       
        </ul>
      </li>
      <!-- onmouseout ="$('#zxg').click();" onmouseout ="$('#zxg').click();" onmouseover ="$('#zxg').click();"-->
       <li class="am-dropdown" id='zxg' data-am-dropdown >
        <a class="am-dropdown-toggle"  data-am-dropdown-toggle href="javascript:void(0);">
          	自选股 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li class="" onclick="$('#zxg').click();//hidden menu" ng-repeat="concern in concerns">
             <a  style="display:block" href="<%=basePath%>ws/line.zc?code={{concern.CODEURL}}" target='_blank'>{{concern.NAME_}}({{concern.CODE_}})</a>
          </li>   
          <li class="" onclick="$('#zxg').click();//hidden menu javascript:void(0);">
             <a  style="display:block" href="<%=basePath%>ws/myConcerns.zc" target='pcframe'>更多...</a>
          </li>                
        </ul>
      </li>
    </ul>  
    <!--  -->
    <form action="<%=basePath%>ws/codes.zc" class="am-topbar-form am-topbar-left am-form-inline" role="search" target='pcframe' method='post'>
      <div class="am-form-group">
        <input type="text" name="condition" id='condition' class="am-form-field am-input-sm" placeholder="股票代码或者股票名称">
      </div>
       <div class="am-form-group">
       	<button type="button"  onclick="check(this);" class="am-btn am-btn-primary am-topbar-btn am-btn-sm" >搜索</button>
       	  <!-- <div class="am-topbar-right"><button onclick="check(this);" class="am-btn am-btn-primary am-topbar-btn am-btn-sm">搜索1</button></div> -->
       	<!-- <button type="button"  onclick="check(this);" class="am-btn am-btn-primary am-topbar-btn am-btn-sm" >高级搜索</button> -->
       	<button type="submit" id='tj' style="display:none;">&nbsp;</button>
       </div>
    </form>
<script>
function check(obj){
	if($('#condition').val()==''||$('#condition').val()==null){
		alert("股票代码或者股票名称不能为空！");
		return false;
	}else{
		$('#tj').click();
	}
}
</script>
    <div class="am-topbar-right">
      <div class="am-dropdown" data-am-dropdown="{boundary: '.am-topbar'}">
        <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle>更多 <img class="am-icon-caret-down" width="15px" height="15px" src="<%=basePath%>img/down.jpg"></img></button>
        <ul class="am-dropdown-content">
          <%
				if (username != null && !"".equals(username)) {
		 %>
          <li><a href="<%=basePath%>user/logout.zc?page=index" target='window.top'>退出</a></li>
         <%
			}
		 %>
      </div> 
    </div>
    <div class="am-topbar-right">
       <%
				if (username == null || "".equals(username)) {
		%>
       <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"><a  style="display:block" href="<%=basePath%>cfjh/login.jsp?page=index" target='window.top'>登录</a></button>
       <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"><a  style="display:block" href="<%=basePath%>register.jsp" target='window.top'>注册</a></button>
        <%
				} else{
		%>
		 <ul class="am-nav am-nav-pills am-topbar-nav">     
           <li class="am-dropdown" data-am-dropdown>
            <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	 您好,<%=username%>&nbsp;<span class="am-icon-caret-down"></span>
            </a>
		</li>
		</ul>
		<%
		  }
		%>
		 <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"> <a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>意见箱</a></button>
    </div>
  </div>
</header>

<div class="am-g">
  <div class="am-u-sm-12 am-u-end" >
      	<iframe src="<%=basePath%>phone/analysQutoes_.jsp" marginheight="0" marginwidth="0" 
      	frameborder="0" scrolling="yes" onload='iFrameHeight()' width="100%" height=900 id="pcframe" name="pcframe">
      </iframe>
<script type="text/javascript" language="javascript">
   function iFrameHeight() {
        var iframe= document.getElementById("pcframe");
        try{  
       	    /*alert(document.body.scrollHeight);
            var bHeight = iframe.contentWindow.document.body.scrollHeight;  
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
            var height = Math.max(bHeight, dHeight);  */
            //iframe.height = document.body.scrollHeight-5;  
        }catch (ex){alert('err reinit frame');}  
 }
</script> 
	</div>
     </div>
     </div>
</body>
</html>
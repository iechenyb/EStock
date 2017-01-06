<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
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
<link rel="stylesheet" href="<%=basePath%>/css/estock.css">
</head>
<body>
  <header class="am-topbar am-topbar-inverse">
  <h1 class="am-topbar-brand">
    <a href="<%=basePath%>cfjh/index.jsp"><%-- <img alt="首页-搜索" src="<%=basePath%>img/logo.jpg" width="80px" height="50px"/> --%>财富管家</a>
  </h1>
  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}">
   <span class="am-sr-only">导航切换</span>
   <span class="am-icon-bars"></span>
   </button>
  <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
    <!--  -->
    <ul class="am-nav am-nav-pills am-topbar-nav">     
       <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	收入 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
         <!--  <li class="am-dropdown-header">&nbsp;</li> -->
          <li> 
          	<a  style="display:block" href="<%=basePath%>ws/line.zc.html?code=sh000001" target='pcframe'>薪资</a>
          </li>
          <li class="">
               <a  style="display:block" href="<%=basePath%>ws/line.zc.html?code=sh000001" target='pcframe'>理财</a>
           </li>
           <li class="">
               <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz399001" target='pcframe'>贷借款</a>
           </li>
           <li class="">
               <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz399001" target='pcframe'>福利</a>
           </li>
           <li class="">
               <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz399001" target='pcframe'>奖金</a>
           </li>
        </ul>
      </li>
      <!--  -->
       <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	支出 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li class="">
                   <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sh600868" target='pcframe'>医疗</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz000760" target='pcframe'>贷借款</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sh600614" target='pcframe'>还信用卡</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz000040" target='pcframe'>饮食</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz000977" target='pcframe'>交通费</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sh600756" target='pcframe'>购物</a>
                  </li>
        </ul>
      </li>
      <!--  -->
       <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          	收支管理 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li class="">
                   <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sh600868" target='pcframe'>新增收入</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line.zc?code=sz000760" target='pcframe'>新增支出</a>
                  </li>
        </ul>
      </li>
      <!--  -->
    </ul>
    <!--  -->
    <form action="<%=basePath%>ws/codes.zc" class="am-topbar-form am-topbar-left am-form-inline" role="search" target='pcframe' method='post'>
      <div class="am-form-group">
        <input type="text" name="condition" id='condition' class="am-form-field am-input-sm" placeholder="">
      </div>
       <div class="am-form-group">
       	<button type="button" onclick="check(this);" class="am-btn am-btn-primary am-topbar-btn am-btn-sm" >搜索</button>
       	<button type="submit" id='tj' style="display:none;">&nbsp;</button>
       </div>
    </form>
<script>
function check(obj){
	if($('#condition').val()==''||$('#condition').val()==null){
		//alert("股票、基金、国债代码或者名称不能为空！");
		return false;
	}else{
		//$('#tj').click();
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
          <li><a href="<%=basePath%>cfgl/logout.zc" target="window.top">退出</a></li>
         <%
			}
		 %>
      </div> 
    </div>
    <div class="am-topbar-right">
       <%
				if (username == null || "".equals(username)) {
		%>
       <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"><a  style="display:block" href="<%=basePath%>cfjh/login.jsp" target='window.top'>登录</a></button>
       <%-- <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"><a  style="display:block" href="<%=basePath%>register.jsp" target='window.top'>注册</a></button> --%>
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
		 <%-- <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"> <a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>意见箱</a></button> --%>
    </div>
  </div>
</header>

<div class="am-g">
  <div class="am-u-sm-12 am-u-end" >
      	<iframe src="<%=basePath%>qutoes/center.jsp" marginheight="0" marginwidth="0" frameborder="0" scrolling="yes"  width="100%" height=100% id="pcframe" name="pcframe" onLoad="iFrameHeight()" >
      </iframe>
<script type="text/javascript" language="javascript">

    function iFrameHeight() {
         var iframe= document.getElementById("pcframe");
         try{  
        	 /*alert(document.body.scrollHeight);
             var bHeight = iframe.contentWindow.document.body.scrollHeight;  
             var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
             var height = Math.max(bHeight, dHeight);  */
             iframe.height = document.body.scrollHeight-47;   
         }catch (ex){alert('err reinit frame');}  
    }

</script> 
	</div>
     </div>
</body>
</html>
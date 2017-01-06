<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片展示</title>
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
<script type="text/javascript" language="javascript">     
  
function reinitIframe(){  
var iframe = document.getElementById("iframepage");  
try{  
    var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    var height = Math.max(bHeight, dHeight);  
    iframe.height = height;  
}catch (ex){}  
}  
  
//var timer1 = window.setInterval("reinitIframe()", 500); //定时开始  
  
function reinitIframeEND(){  
var iframe = document.getElementById("iframepage");  
try{  
	alert(document.body.scrollHeight);
   /*  var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    var height = Math.max(bHeight, dHeight);  
    iframe.height = height;   */
}catch (ex){alert('err reinit frame');}  
// 停止定时  
//window.clearInterval(timer1);  
}  
  
</script>  
</head>
<body style="margin:0px;">
<div class="am-g">
  <div class="am-u-sm-12 am-u-end" >
  <header data-am-widget="header"
          class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="#left-link" class="">
              <span class="am-header-nav-title">
              <a href="<%=basePath%>qutoes/index.jsp"  style="display:block" target="iframepage">首页</a>
              </span>
                <i class="am-header-icon am-icon-home"> </i>
          </a>
      </div>
      <h1 class="am-header-title">
         股往今来
      </h1>
      <div class="am-header-right am-header-nav">
          <a href="#right-link" class="">
              <span class="am-header-nav-title">
                       菜单             
              </span>
          </a>
      </div>
  </header>
 <nav data-am-widget="menu" class="am-menu  am-menu-offcanvas1" data-am-menu-offcanvas > 
    <a href="javascript: void(0)" class="am-menu-toggle">
          <i class="am-menu-toggle-icon am-icon-bars"></i>
    </a>
    <div class="am-offcanvas" >
      <div class="am-offcanvas-bar">
      <ul class="am-menu-nav am-avg-sm-1">
          <li class="am-parent">
            <a href="##" class="" >大盘行情</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-2 ">
                  <li class="">
                    <a  style="display:block" href="<%=basePath%>ws/line?code=sh000001" target='iframepage'>上证指数</a>
                  </li>
                  <li class="">
                      <a  style="display:block" href="<%=basePath%>ws/line?code=sh000001" target='iframepage'>深证成指</a>
                  </li>
                  <li class="">
                      <a  style="display:block" href="<%=basePath%>ws/line?code=sz399001" target='iframepage'>创业板综</a>
                  </li>
                  <li class="am-menu-nav-channel"><a href="##" class="" title="公司">更多 &raquo;</a></li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="##" class="" >自选股</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-3 ">
                  <li class="">
                   <a  style="display:block" href="<%=basePath%>ws/line?code=sh600868" target='iframepage'>梅雁吉祥</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line?code=sz000760" target='iframepage'>斯太尔</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line?code=sh600614" target='iframepage'>鼎立股份</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line?code=sz000040" target='iframepage'>宝安地产</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line?code=sz000977" target='iframepage'>浪潮信息</a>
                  </li>
                  <li class="">
                     <a  style="display:block" href="<%=basePath%>ws/line?code=sh600756" target='iframepage'>浪潮软件</a>
                  </li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="#c3" class="" >股票知识</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-4 ">
                  <li class="">
                    <a target='iframepage' style="display:block;" href='<%=basePath%>qutoes/center.jsp'>股票常识</a> 
                  </li>
                  <li class="am-menu-nav-channel"><a href="#c3" class="" title="更多">更多 &raquo;</a></li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="##" class="" >联系我们</a>
              <ul class="am-menu-sub am-collapse  am-avg-sm-3 ">
                  <li class="">
                   <a  target='iframepage'style="display:block;" href='<%=basePath%>qutoes/idear.jsp'>意见箱</a> 
                  </li>
              </ul>
          </li>
          <li class="am-parent">
            <a href="##" class="" >登陆</a>
             <ul class="am-menu-sub am-collapse  am-avg-sm-3 ">
                  <li class="">
                   <a  target='iframepage'style="display:block;" href='<%=basePath%>login.jsp'>登陆</a> 
                  </li>
                  <li class="">
                   <a  target='iframepage'style="display:block;" href='<%=basePath%>register.jsp'>注册</a> 
                  </li>
              </ul>
          </li>
          <li class="">
            <a href="##" class="" ></a>
          </li>
      </ul>
      </div>
    </div>
  </nav>
  </div>
</div>
<div class="am-g">
  <div class="am-u-sm-12 am-u-end" >
      	<iframe src="<%=basePath%>qutoes/index.jsp" marginheight="0" marginwidth="0" frameborder="0" scrolling="yes"  width="100%" height=100% id="iframepage" name="iframepage" onLoad="iFrameHeight()" >
      </iframe>
<script type="text/javascript" language="javascript">

    function iFrameHeight() {
         var iframe= document.getElementById("iframepage");
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
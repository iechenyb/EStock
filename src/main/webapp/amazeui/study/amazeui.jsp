<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String path=application.getRealPath(request.getRequestURI());  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>Hello Amaze UI</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="<%=basePath%>amazeui/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>amazeui/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="<%=basePath%>amazeui/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="<%=basePath%>amazeui/css/amazeui.min.css">
  <link rel="stylesheet" href="<%=basePath%>amazeui/css/app.css">
  <style type="text/css">
  .border{
   border:1px solid gray
  }
  .margin{
   margin-top:10px;
   margin-left:15px;
  }
  </style>
</head>
<body>
<div data-am-sticky>
  <button class="am-btn am-btn-primary am-btn-block">Stick to top</button>
</div>
<button type="button" class="am-btn am-btn-default">默认样式</button>
<button type="button" class="am-btn am-btn-primary">主色按钮</button>
<button type="button" class="am-btn am-btn-secondary">次色按钮</button>
<button type="button" class="am-btn am-btn-success">绿色按钮</button>
<button type="button" class="am-btn am-btn-warning">橙色按钮</button>
<button type="button" class="am-btn am-btn-danger">红色按钮</button>
<a class="am-btn am-btn-link">链接</a>
<a class="am-btn am-btn-danger" href="http://www.bing.com" target="_blank">应用按钮样式的链接</a></br>
<button type="button" class="am-btn am-btn-primary am-radius">主色按钮</button>
<button type="button" class="am-btn am-btn-primary am-round">默认样式</button>
<button type="button" class="am-btn am-btn-primary am-active">激活状态</button>
<button type="button" class="am-btn am-btn-default am-active">激活状态</button>
<br >
<br >
<a href="#" class="am-btn am-btn-primary am-active" role="button">链接按钮激活状态</a>
<a href="#" class="am-btn am-btn-default am-active" role="button">链接按钮激活状态</a>
<div class="am-g">
  <div class="am-u-sm-2 border" >2</div>
  <div class="am-u-sm-2 border am-u-end" >如果行的网格数不足 12</div>
</div>
<hr>
<div class="am-g doc-am-g border">
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-4 lg-3</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-8 lg-9</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-4 lg-3</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-8 lg-9</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-4 lg-3</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-8 lg-9</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border">sm-6 md-4 lg-3</div>
  <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 border am-u-end">如果行的网格数不足 12</div>
</div>
<h2>没有限制宽度的网格</h2>
<div class="am-g border">
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
</div>

<h2>限制宽度的网格</h2>
<div class="am-g am-g-fixed border">
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
</div>
<hr>
<div class="am-g am-g-fixed border">
  <div class="am-u-sm-12 border">全宽限制最大宽度的行</div>
</div>
<div class="am-g border">
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 border">3</div>
  <div class="am-u-sm-3 am-u-end border">实际使用中，如果行的网格数不足 12，需要在最后一列上添加 .am-u-end</div>
</div>
<hr>
<div class="am-g">
  <div class="am-u-sm-2 am-u-lg-4">
    <span class="am-show-md-down">sm-2</span>
    <span class="am-show-lg-only">lg-4</span>
  </div>
  <div class="am-u-sm-4 am-u-lg-4">sm4 lg4</div>
  <div class="am-u-sm-6 am-u-lg-4">
    <span class="am-show-md-down">sm-6</span>
    <span class="am-show-lg-only">lg-4</span>
  </div>
</div>

<div class="am-g border">
  <div class="am-u-lg-3">
    <span class="am-show-md-down">sm-full</span>
    <span class="am-show-lg-only">lg-3</span>
  </div>
  <div class="am-u-lg-6">
    <span class="am-show-md-down">sm-full</span>
    <span class="am-show-lg-only">lg-6</span>
  </div>
  <div class="am-u-lg-3">
    <span class="am-show-md-down">sm-full</span>
    <span class="am-show-lg-only">lg-3</span>
  </div>
</div>

<div class="am-g border ">
  <div class="am-u-sm-6 am-u-lg-2">
    <span class="am-show-md-down">6</span>
    <span class="am-hide-md-down">2</span>
  </div>
  <div class="am-u-sm-6 am-u-lg-8">
    <span class="am-show-md-down">6</span>
    <span class="am-hide-md-down">8</span>
  </div>
  <div class="am-u-sm-12 am-u-lg-2">
    <span class="am-show-md-down">full</span>
    <span class="am-hide-md-down">2</span>
  </div>
</div>
<div class="am-g border">
  <div class="am-u-sm-3">3</div>
  <div class="am-u-sm-9">9</div>
</div>
<div class="am-g border">
  <div class="am-u-lg-4">
    <span class="am-show-md-down">full</span>
    <span class="am-hide-md-down">4</span>
  </div>
  <div class="am-u-lg-8">
    <span class="am-show-md-down">full</span>
    <span class="am-hide-md-down">8</span>
  </div>
</div>
<div class="am-g border">
  <div class="am-u-sm-6 am-u-lg-5">
    <span class="am-show-md-down">6</span>
    <span class="am-hide-md-down">5</span>
  </div>
  <div class="am-u-sm-6 am-u-lg-7">
    <span class="am-show-md-down">6</span>
    <span class="am-hide-md-down">7</span>
  </div>
</div>
<div class="am-g border">
  <div class="am-u-lg-6">
    <span class="am-show-md-down">full</span>
    <span class="am-hide-md-down">6</span>
  </div>
  <div class="am-u-lg-6">
    <span class="am-show-md-down">full</span>
    <span class="am-hide-md-down">6</span>
  </div>
</div>
<div class="am-cf">
  <button class="am-btn am-btn-success am-fl">向左浮动</button>
  <button class="am-btn am-btn-success am-fr">向右浮动</button>
  <a href="" class="am-link-muted">...</a>

<h3 class="am-link-muted"><a href="">haha</a></h3>

<ul class="am-link-muted">
  <li><a href="">haha</a></li><li><a href="">haha</a></li>
</ul>
</div>
<div class="am-alert">
  没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。
</div>
<div class="am-alert" data-am-alert>
  <button type="button" class="am-close">&times;</button>
  <p>没什么可给你，但求凭这阙歌。谢谢你风雨里，都不退愿陪着我。</p>
</div>
<hr>
<button
  type="button"
  class="am-btn am-btn-primary"
  data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 400, height: 225}">
  Modal
</button>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">Modal 标题
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd">
      Modal 内容。本 Modal 无法通过遮罩层关闭。
    </div>
  </div>
</div>

<nav class="scrollspy-nav" data-am-scrollspy-nav="{offsetTop: 45}" data-am-sticky>
  <ul>
    <li><a href="#about">关于棉花糖</a></li>
    <li><a href="#team">成员介绍</a></li>
    <li><a href="#ep">首张 EP</a></li>
    <li><a href="#second">再见王子</a></li>
    <li><a href="#third">第三张</a></li>
  </ul>
</nav>

<div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="doc-tab-demo-1">
  <ul class="am-tabs-nav am-nav am-nav-tabs">
    <li class="am-active"><a href="javascript: void(0)">流浪</a></li>
    <li><a href="javascript: void(0)">流浪</a></li>
    <li><a href="javascript: void(0)">再流浪</a></li>
  </ul>

  <div class="am-tabs-bd">
    <div class="am-tab-panel am-active">
      1231123
    </div>
    <div class="am-tab-panel">
      23434545
    </div>
    <div class="am-tab-panel">
      55678
    </div>
  </div>
</div>
<br />
<button type="button" class="am-btn am-btn-primary js-append-tab">插入 Tab</button>



<form action="" class="am-form" data-am-validator>
<div class="am-g">
	  <div class="am-u-sm-4">
	  	<input type="text" class="am-form-field" id="test1" name="test1" placeholder="日历组件" data-am-datepicker readonly required />
	  </div>
	  <div class="am-u-sm-4">
		  <div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'dd-mm-yyyy'}">
			  <input type="text" id="test" name="test" class="am-form-field" placeholder="日历组件" readonly>
			  <span class="am-input-group-btn am-datepicker-add-on">
			    <button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>
			  </span>
		  </div>
	 </div>
	 <div class="am-u-sm-4"><button class="am-btn am-btn-primary">提交</button></div>
 </div>
</form>
<!--在这里编写你的代码-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=basePath%>amazeui/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="<%=basePath%>amazeui/js/amazeui.min.js"></script>
<div class="am-alert am-alert-danger" id="my-alert" style="display: none">
  <p>开始日期应小于结束日期！</p>
</div>
<hr>
<div class="am-g">
  <div class="am-u-sm-2">
    <button type="button" class="am-btn am-btn-default am-margin-right" id="my-start">开始日期</button><span id="my-startDate">2014-12-20</span>
  </div>
  <div class="am-u-sm-2">
    <button type="button" class="am-btn am-btn-default am-margin-right" id="my-end">结束日期</button><span id="my-endDate">2014-12-25</span>
  </div>
  <div class="am-u-sm-8 am-u-end">
  &nbsp;
  </div>
</div>
<script>
  $(function() {
    var tabCounter = 0;
    var $tab = $('#doc-tab-demo-1');
    var $nav = $tab.find('.am-tabs-nav');
    var $bd = $tab.find('.am-tabs-bd');

    function addTab() {
      var nav = '<li><span class="am-icon-close"></span>' +
        '<a href="javascript: void(0)">标签 ' + tabCounter + '</a></li>';
      var content = '<div class="am-tab-panel">动态插入的标签内容' + tabCounter + '</div>';

      $nav.append(nav);
      $bd.append(content);
      tabCounter++;
      $tab.tabs('refresh');
    }

    // 动态添加标签页
    $('.js-append-tab').on('click', function() {
      addTab();
    });

    // 移除标签页
    $nav.on('click', '.am-icon-close', function() {
      var $item = $(this).closest('li');
      var index = $nav.children('li').index($item);

      $item.remove();
      $bd.find('.am-tab-panel').eq(index).remove();

      $tab.tabs('open', index > 0 ? index - 1 : index + 1);
      $tab.tabs('refresh');
    });
  });
</script>
<script>
  $(function() {
    var startDate = new Date(2014, 11, 20);
    var endDate = new Date(2014, 11, 25);
    var $alert = $('#my-alert');
    $('#my-start').datepicker().
      on('changeDate.datepicker.amui', function(event) {
        if (event.date.valueOf() > endDate.valueOf()) {
          $alert.find('p').text('开始日期应小于结束日期！').end().show();
        } else {
          $alert.hide();
          startDate = new Date(event.date);
          $('#my-startDate').text($('#my-start').data('date'));
        }
        $(this).datepicker('close');
      });

    $('#my-end').datepicker().
      on('changeDate.datepicker.amui', function(event) {
        if (event.date.valueOf() < startDate.valueOf()) {
          $alert.find('p').text('结束日期应大于开始日期！').end().show();
        } else {
          $alert.hide();
          endDate = new Date(event.date);
          $('#my-endDate').text($('#my-end').data('date'));
        }
        $(this).datepicker('close');
      });
  });
</script>
</body>
</html>
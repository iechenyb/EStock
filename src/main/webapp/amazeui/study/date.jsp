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
        content="width=device-width, initial-scale=1">
  <title>Hello Amaze UI</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="amazeui/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="amazeui/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="amazeui/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="amazeui/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="../css/amazeui.min.css">
  <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<p>
  Hello Amaze UI.
</p>

<!--在这里编写你的代码-->
<div class="am-alert am-alert-danger" id="my-alert" style="display: none">
  <p>开始日期应小于结束日期！</p>
</div>
<div class="am-g">
  <div class="am-u-sm-6">
    <button type="button" class="am-btn am-btn-default am-margin-right" id="my-start">开始日期</button>
    <span id="my-startDate">2014-12-20</span>
  </div>
  <div class="am-u-sm-6">
    <button type="button" class="am-btn am-btn-default am-margin-right" id="my-end">结束日期</button>
    <span id="my-endDate">2014-12-25</span>
  </div>
</div>

<!--[if (gte IE 9)|!(IE)]><!-->
<!-- <script src="js/jquery.min.js"></script> -->
<script src="http://analyse.kiiik.com:80/assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="amazeui/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<!-- <script src="amazeui/js/amazeui.min.js"></script> -->
<script src="http://analyse.kiiik.com:80/assets/js/amazeui.min.js"></script>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>VCard1</title>
  <style type="text/css">
    .card{ width:240px; height:160px; border:1px solid #999; background:#fff; overflow:hidden; }
    .avatar{ width:50px; height:50px; margin:18px; float:left; overflow:hidden; }
    .avatar img{ max-width:50px; max-height:50px; }
    .card p{ font:14px/25px "Microsoft Yahei", Arial, Simsun; margin:12px 18px; color:#000; }
    .detail{ color:#ccc; word-break:break-all; }
  </style>
</head>
<body>
  <!-- 
  http://www.kuqin.com/shuoit/20131011/335574.html
  http://alloyteam.github.io/JX/doc/core/symbols/http.html -->
  <div id="wrap">Loading...</div>
  <script type="text/javascript" src="http://pub.idqqimg.com/lib/jx/1.0.1/jx-uiless.js" charset="UTF-8"></script>
  <!-- <script type="text/javascript" src="http://alloyteam.github.io/JX/doc/core/symbols/src/src_jx.event.js" charset="UTF-8"></script> -->
  <script type="text/javascript">
  //var J = new Jx();
  //console.log(J.version); 
  Jx().$package(function(J){
	  console.log(J.version);    //输出当前Jx的版本
	  /* var onMsg = new J.Publish();
	  var funcA = function(option){
	      alert(option);
	  };
	  // 注册一个事件的观察者
     onMsg.subscribe(funcA);
     var option = "demo";
     onMsg.deliver(option);
     onMsg.unsubscribe(funcA);
     onMsg.deliver(option); */
  });
  </script>
  <script type="text/javascript" src="vcard.js"></script>
  <script id="vcardTmpl" type="text/template">
  <div class="card">
    <div class="avatar"><img src=""/></div>
    <p>昵称:${nick}</p>
    <p>性别：${x}</p> 
    <p class="detail">简介：请点击F12，查看后台日志。</p>
  </div>
  </script>
</body>
</html>
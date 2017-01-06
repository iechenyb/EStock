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
    <link rel="stylesheet" href="../css/amazeui.page.css">
  <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<p>
  Hello Amaze UI.
</p>
<div id='content'> </div>
<!-- 动态：<div id='page1'  data-am-page="{pages:2}"></div> -->
<!-- ,jump:'http://localhost:8888/amazeui/study/data.json'-->
静态：<div data-am-page="{pages:10,jump:'http://localhost:8888/amazeui/study/data.json'}" id=page></div>
<div data-am-page="{pages:10}"></div>
<div data-am-page="{pages:10,theme:'secondary'}"></div>
<div data-am-page="{pages:10,theme:'success'}"></div>
<div data-am-page="{pages:10,theme:'warning'}"></div>
<div data-am-page="{pages:10,theme:'danger'}"></div>        
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
<script src="../js/amazeui.page.min.js"></script>
<script type="text/javascript">
//测试数据
var data=["北京","上海","广州","深圳","杭州","长沙","合肥","宁夏","成都","西安","南昌","上饶","沈阳","济南","厦门","福州","九江","宜春","赣州","宁波","绍兴","无锡","苏州","徐州","东莞","佛山","中山","成都","武汉","青岛","天津","重庆","南京","九江","香港","澳门","台北"];
//var data=["北京","上海","广州"];
var nums = 5; //每页出现的数量
var pages = Math.ceil(data.length / nums); //得到总页数
if(pages==1) pages=pages;
var thisDate = function(curr) {
    //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
    var str = '',
        last = curr * nums - 1;
    last = last >= data.length ? (data.length - 1) : last;
    for (var i = (curr * nums - nums); i <= last; i++) {
        str += '<li>' + data[i] + '</li>';
    }
    return str;
};
$(function() {
    //返回的是一个page示例，拥有实例方法
    var $page = $("#page").page({
        pages: pages, //页数
        curr: 1, //当前页 
        type: 'default', //主题
        groups: 5, //连续显示分页数
        prev: '<', //若不显示，设置false即可
        next: '>', //若不显示，设置false即可        
        first: "首页",
        last: "尾页", //false则不显示
        before: function(context, next) { //加载前触发，如果没有执行next()则中断加载
            console.log('开始加载...');
            context.time = (new Date()).getTime(); //只是演示，并没有什么卵用，可以保存一些数据到上下文中
            next();
        },
        render: function(context, $el, index) { //渲染[context：对this的引用，$el：当前元素，index：当前索引]
            //逻辑处理
            if (index == 'last') { //虽然上面设置了last的文字为尾页，但是经过render处理，结果变为最后一页
                $el.find('a').html('最后一页');
                return $el; //如果有返回值则使用返回值渲染
            }
            return false; //没有返回值则按默认处理
        },
        after: function(context, next) { //加载完成后触发
            var time = (new Date()).getTime(); //没有什么卵用的演示
            console.log('分页组件加载完毕，耗时：' + (time - context.time) + 'ms');
            next();
        },
        /*
         * 触发分页后的回调，如果首次加载时后端已处理好分页数据则需要在after中判断终止或在jump中判断first是否为假
         */
        jump: function(context, first) {
            console.log('当前第：' + context.option.curr + "页,first="+first);
            $("#content").html(thisDate(context.option.curr));
            if(context.option.curr==3){
            	data.push("111");
            	data.push("222");
            	data.push(["haha",'heihei']);
            }
        }
    });

    $("#remove").click(function() {
        $page.remove(function() {
            console.log('移除分页组件成功');
        })
    })

    $("#set").click(function() {
        var page = $("#curr").val();
        $page.setCurr(page, function() {
            console.log('跳转到第' + page + "页");
        });
    })
});
</script>
</body>
</html>
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

  <link rel="icon" type="image/png" href="assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="../css/amazeui.min.css">
  <link rel="stylesheet" href="../css/amazeui.tree.css">
  <link rel="stylesheet" href="../css/app.css">
</head>
<body>
<ul class="am-tree" id="firstTree">
  <li class="am-tree-branch am-hide" data-template="treebranch">
    <div class="am-tree-branch-header">
      <button class="am-tree-branch-name">
        <span class="am-tree-icon am-tree-icon-folder"></span>
        <span class="am-tree-label"></span>
      </button>
    </div>
    <ul class="am-tree-branch-children"></ul>
    <div class="am-tree-loader"><span class="am-icon-spin am-icon-spinner"></span></div>
  </li>
  <li class="am-tree-item am-hide" data-template="treeitem">
    <button class="am-tree-item-name">
      <span class="am-tree-icon am-tree-icon-item"></span>
      <span class="am-tree-label"></span>
    </button>
  </li>
</ul>
<!--在这里编写你的代码-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="../js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="../js/amazeui.min.js"></script>
<script src="../js/amazeui.tree.min.js"></script>
<script type="text/javascript">
var data = [
            {
              title: '苹果公司',
              type: 'folder',
              id:'app',
              products: [
                {
                  title: 'iPhone',
                  type: 'folder',
                  products: [
                         {
                           id:'1',
                           title: 'iPhone4',
                           type: 'item'
                         },
                         {
                           id:'2',
                           title: 'iPhone5',
                           type: 'item'
                         },
                         {
                           id:'3',
                           title: 'iPhone6',
                           type: 'item'
                         }
                 ]
                },
                {
                  title: 'iMac',
                  type: 'item'
                },
                {
                  title: 'MacBook Pro',
                  type: 'item'
                }
              ]
            },
            {
              title: '微软公司',
              type: 'folder',
              products: [
                         {
                           title: 'iPhone',
                           type: 'item'
                         },
                         {
                           title: 'iMac',
                           type: 'item'
                         },
                         {
                           title: 'MacBook Pro',
                           type: 'item'
                         }
              ]
            },
            {
              title: 'GitHub',
              type: 'folder',
              products: [
                         {
                           title: 'iPhone',
                           type: 'item'
                         },
                         {
                           title: 'iMac',
                           type: 'item'
                         },
                         {
                           title: 'MacBook Pro',
                           type: 'item'
                         }
               ]
              /*attr: {
                icon: 'am-icon-github'
              }*/
            }
          ];

  var tree = $('#firstTree').tree({
    dataSource: function(options, callback) {
      // 模拟异步加载
      setTimeout(function() {
        callback({data: options.products || data});
      }, 400);
    },
    multiSelect: true,
    cacheItems: true,
    folderSelect: false
  });
 function  getSelected(){
	 var ites = tree.tree('selectedItems');
	 var  str = "";
	 for(i=0;i<ites.length;i++){
		 str = str +ites[i].title+",";
	 }
	 $('#content').html(str);
  }
 function close(){
	 tree.tree("closeAll");
 }
 function open(){
	 tree.tree("discloseAll");
 }
</script>
<button type="button" class="am-btn am-btn-default" onclick="getSelected()">获取选择的节点</button>
<button type="button" class="am-btn am-btn-primary" onclick="open()">打开节点</button>
<button type="button" class="am-btn am-btn-secondary" onclick="close()">关闭节点</button>
<button type="button" class="am-btn am-btn-success">绿色按钮</button>
<button type="button" class="am-btn am-btn-warning">橙色按钮</button>
<button type="button" class="am-btn am-btn-danger">红色按钮</button>
<div id='content'></div>
</body>
</html>
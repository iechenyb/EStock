<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Phone Welcome</title>
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
   <div data-am-widget="intro"
       class="am-intro am-cf am-intro-default"
       >
      <div class="am-intro-hd">
        <h2 class="am-intro-title">你好小娜</h2>
            <a class="am-intro-more am-intro-more-top " href="#more">更多细节</a>
      </div>

    <div class="am-g am-intro-bd">
        <div
            class="am-intro-left am-u-sm-5"><img src="http://s.amazeui.org/assets/2.x/i/cpts/intro/WP_Cortana_China.png" alt="小娜" /></div>
        <div
            class="am-intro-right am-u-sm-7"><p>Cortana 恐怕是用户急于更新到 WP8.1 Update 开发者预览版的原因之一</p><p>Cortana 中国版特有的新图标（面团，带眼睛，可在 Cortana 设置中切换回圆圈），使用的是中文语言，也同样支持 Cortana 笔记本。</p></div>
    </div>
  </div>
  <hr>
  <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
  <!--列表标题-->
    <div class="am-list-news-hd am-cf">
       <!--带更多链接-->
        <a href="###" class="">
          <h2>缩略图在标题下左 + 摘要</h2>
            <span class="am-list-news-more am-fr">更多 &raquo;</span>
        </a>
          </div>

  <div class="am-list-news-bd">
  <ul class="am-list">
    
     <!--缩略图在标题下方居左-->
      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-bottom-left">
          <h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11614662/" class="">我很囧，你保重....晒晒旅行中的那些囧！</a></h3>
        <div class="am-u-sm-4 am-list-thumb">
          <a href="http://www.douban.com/online/11614662/" class="">
            <img src="<%=basePath%>/images/1.png" width="40px" height="80px"alt="我很囧，你保重....晒晒旅行中的那些囧！"/>
          </a>
        </div>

        <div class=" am-u-sm-8  am-list-main">

            <div class="am-list-item-text">囧人囧事囧照，人在囧途，越囧越萌。标记《带你出发，陪我回家》http://book.douban.com/subject/25711202/为“想读”“在读”或“读过”，有机会获得此书本活动进行3个月，每月送出三本书。会有不定期bonus！</div>

        </div>
      </li>
      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-bottom-left">
          <h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11624755/" class="">我最喜欢的一张画</a></h3>
        <div class="am-u-sm-4 am-list-thumb">
          <a href="http://www.douban.com/online/11624755/" class="">
            <img src="http://img3.douban.com/lpic/o637240.jpg" width="40px" height="80px" alt="我最喜欢的一张画"/>
          </a>
        </div>

        <div class=" am-u-sm-8  am-list-main">

            <div class="am-list-item-text">
                     你最喜欢的艺术作品，告诉大家它们的------名图画，色彩，交织，撞色，线条雕塑装置当代古代现代作品的照片美我最喜欢的画群296795413进群发画，少说多发图，
            </div>

        </div>
      </li>
      <li class="am-g am-list-item-desced">
          <h3 class="am-list-item-hd"><a href="http://www.douban.com/online/11645411/" class="">“你的旅行，是什么颜色？” 晒照片，换北欧梦幻极光之旅！</a></h3>

        <div class=" am-list-main">

            <div class="am-list-item-text">
                              还在苦恼圣诞礼物再也玩儿不出新意？快来抢2013最炫彩的跨国圣诞礼物！【参与方式】1.关注“UniqueWay无二之旅”豆瓣品牌小站http://brand.douban.com/uniqueway/2.上传一张**本人**在旅行中色彩最浓郁、最丰富的照片（色彩包含取景地、周边事物、服装饰品、女生彩妆等等，发挥你们无穷的创意想象力哦！^^）一定要有本人出现喔！3. 在照片下方，附上一句旅行宣言作为照片说明。 成功参与活动！* 听他们刚才说，上传照片的次
            </div>

        </div>
      </li>
    </ul>
  </div>

    </div>
    
    <hr>
     <div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default "
      id="">
      <ul class="am-navbar-nav am-cf am-avg-sm-4">
          <li >
            <a href="tel:123456789" class="">
                  <span class="am-icon-phone"></span>
                <span class="am-navbar-label">呼叫</span>
            </a>
          </li>
          <li data-am-navbar-share>
            <a href="###" class="">
                  <span class="am-icon-share-square-o"></span>
                <span class="am-navbar-label">分享</span>
            </a>
          </li>
          <li data-am-navbar-qrcode>
            <a href="###" class="">
                  <span class="am-icon-qrcode"></span>
                <span class="am-navbar-label">二维码</span>
            </a>
          </li>
          <li >
            <a href="https://github.com/allmobilize/amazeui" class="">
                  <span class="am-icon-github"></span>
                <span class="am-navbar-label">GitHub</span>
            </a>
          </li>
          <li >
            <a href="http://amazeui.org/getting-started" class="">
                  <span class="am-icon-download"></span>
                <span class="am-navbar-label">下载使用</span>
            </a>
          </li>
          <li >
            <a href="https://github.com/allmobilize/amazeui/issues" class="">
                  <span class="am-icon-location-arrow"></span>
                <span class="am-navbar-label">Bug 反馈</span>
            </a>
          </li>
      </ul>
  </div>
  <hr>
  <div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default "
      id="">
      <ul class="am-navbar-nav am-cf am-avg-sm-4">
          <li >
            <a href="sms:18601860186?body=约吗？" class="">
                <img src="http://amazeui.b0.upaiyun.com/assets/i/cpts/navbar/Information.png" alt="消息"/>
                <span class="am-navbar-label">消息</span>
            </a>
          </li>
          <li >
            <a href="tel:18601860186" class="">
                <img src="http://amazeui.b0.upaiyun.com/assets/i/cpts/navbar/phone.png" alt="呼叫"/>
                <span class="am-navbar-label">呼叫</span>
            </a>
          </li>
          <li data-am-navbar-share>
            <a href="###" class="">
                <img src="http://amazeui.b0.upaiyun.com/assets/i/cpts/navbar/share.png" alt="分享"/>
                <span class="am-navbar-label">分享</span>
            </a>
          </li>
          <li >
            <a href="http://yunshipei.com" class="">
                <img src="http://amazeui.b0.upaiyun.com/assets/i/cpts/navbar/map.png" alt="地图"/>
                <span class="am-navbar-label">地图</span>
            </a>
          </li>
      </ul>
  </div>
</body>
</html>
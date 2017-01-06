<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
String basePath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Display Picture</title>
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
   <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
      <dl class="am-accordion-item am-active">
        <dt class="am-accordion-title">
          就这样恣意的活着
        </dt>
        <dd class="am-accordion-bd am-collapse am-in">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            置身人群中 <br/> 你只需要被淹没 享受 沉默 <br/> 退到人群后 <br/> 你只需给予双手 微笑 等候
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          让生命去等候，去等候，去等候，去等候
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            走在忠孝东路 <br/> 徘徊在茫然中 <br/> 在我的人生旅途 <br/> 选择了多少错误 <br/> 我在睡梦中惊醒 <br/> 感叹悔言无尽 <br/> 恨我不能说服自己 <br/> 接受一切教训 <br/> 让生命去等候 <br/> 等候下一个漂流 <br/> 让生命去等候 <br/>等候下一个伤口
          </div>
        </dd>
      </dl>
      <dl class="am-accordion-item">
        <dt class="am-accordion-title">
          我就这样告别山下的家
        </dt>
        <dd class="am-accordion-bd am-collapse ">
          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
          <div class="am-accordion-content">
            我就这样告别山下的家，我实在不愿轻易让眼泪留下。我以为我并不差不会害怕，我就这样自己照顾自己长大。我不想因为现实把头低下，我以为我并不差能学会虚假。怎样才能够看穿面具里的谎话？别让我的真心散的像沙。如果有一天我变得更复杂，还能不能唱出歌声里的那幅画？
          </div>
        </dd>
      </dl>
  </section>
   <figure data-am-widget="figure" class="am am-figure am-figure-default "   data-am-figure="{  pureview: 'true' }">
    


      <img src="http://s.amazeui.org/media/i/demos/pure-1.jpg?imageView2/0/w/640" data-rel="http://s.amazeui.org/media/i/demos/pure-1.jpg" alt="春天的花开秋天的风以及冬天的落阳"/>
          <figcaption class="am-figure-capition-btm">
            春天的花开秋天的风以及冬天的落阳
          </figcaption>

    
  </figure>
    <footer data-am-widget="footer"
          class="am-footer am-footer-default"
           data-am-footer="{  }">
    <div class="am-footer-switch">
    <span class="am-footer-ysp" data-rel="mobile"
          data-am-modal="{target: '#am-switch-mode'}">
          云适配版
    </span>
      <span class="am-footer-divider"> | </span>
      <a id="godesktop" data-rel="desktop" class="am-footer-desktop" href="javascript:">
          电脑版
      </a>
    </div>
    <div class="am-footer-miscs ">

          <p>由 <a href="http://www.yunshipei.com/" title="诺亚方舟"
                                                target="_blank" class="">诺亚方舟</a>
            提供技术支持</p>
        <p>CopyRight©2014  AllMobilize Inc.</p>
        <p>京ICP备13033158</p>
    </div>
  </footer>

  <div id="am-footer-modal"
       class="am-modal am-modal-no-btn am-switch-mode-m am-switch-mode-m-default">
    <div class="am-modal-dialog">
      <div class="am-modal-hd am-modal-footer-hd">
        <a href="javascript:void(0)" data-dismiss="modal" class="am-close am-close-spin " data-am-modal-close>&times;</a>
      </div>
      <div class="am-modal-bd">
          您正在浏览的是

        <span class="am-switch-mode-owner">
            云适配
        </span>

        <span class="am-switch-mode-slogan">
              为您当前手机订制的移动网站。
        </span>
      </div>
    </div>
  </div>
  
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-overlay" data-am-gallery="{ pureview: true }" >
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg"  alt="远方 有一个地方 那里种有我们的梦想"/>
                <h3 class="am-gallery-title">远方 有一个地方 那里种有我们的梦想</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-2.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>
                <h3 class="am-gallery-title">某天 也许会相遇 相遇在这个好地方</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-3.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-3.jpg"  alt="不要太担心 只因为我相信"/>
                <h3 class="am-gallery-title">不要太担心 只因为我相信</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-4.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-4.jpg"  alt="终会走过这条遥远的道路"/>
                <h3 class="am-gallery-title">终会走过这条遥远的道路</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
  </ul>
  
   <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-bordered" data-am-gallery="{  }" >
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg"  alt="远方 有一个地方 那里种有我们的梦想"/>
                <h3 class="am-gallery-title">远方 有一个地方 那里种有我们的梦想</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-2.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>
                <h3 class="am-gallery-title">某天 也许会相遇 相遇在这个好地方</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-3.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-3.jpg"  alt="不要太担心 只因为我相信"/>
                <h3 class="am-gallery-title">不要太担心 只因为我相信</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
      <li>
        <div class="am-gallery-item">
            <a href="http://s.amazeui.org/media/i/demos/bing-4.jpg" class="">
              <img src="http://s.amazeui.org/media/i/demos/bing-4.jpg"  alt="终会走过这条遥远的道路"/>
                <h3 class="am-gallery-title">终会走过这条遥远的道路</h3>
                <div class="am-gallery-desc">2375-09-26</div>
            </a>
        </div>
      </li>
  </ul>
    <div data-am-widget="gotop" class="am-gotop am-gotop-default" >
    <a href="#top" title="回到顶部">
        <span class="am-gotop-title">回到顶部</span>
          <i class="am-gotop-icon am-icon-chevron-up"></i>
    </a>
    
  </div>
    <div data-am-widget="gotop" class="am-gotop am-gotop-fixed" >
    <a href="#top" title="">
        <img class="am-gotop-icon-custom" src="http://amazeui.b0.upaiyun.com/assets/i/cpts/gotop/goTop.gif" />
    </a>
  </div>
</body>
</html>
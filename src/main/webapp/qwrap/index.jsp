<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://dev.qwrap.com/download/latest/apps/qwrap.js"></script>
<script type="text/javascript">
var a=namespace('.classmates.Tom');
a.age=10;
console.log(QW.classmates.Tom.age);
var w=W('<div>hello</div>');
console.log(w.getHtml());
</script>
</head>
<body>
	<!--http://dev.qwrap.com/resource/js/_docs/_youa/#/qw/base/namespace_.htm  -->
	 <div style="color:red">点我</div>
  <div id="d1" style="color:blue">点我</div>
  <script type="text/javascript" defer>
    var fun=function(e){
      alert(e.target.tagName);  //should be: DIV
      alert(this.tagName);  //should be: DIV
    };
    W('div').on('click',fun);
    var result = W('#d1').getXYAll();
    W('#d1').html('长度: ' + result.length + '<br />值: ' + result);
  </script>
</body>
</html>
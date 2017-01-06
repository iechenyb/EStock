var app = angular.module('app',[]);
app.controller('controller',main);
var scope ;	    
var optionLine ;	 
optionLine = {
	    title : {
	        text: '',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['分钟报价']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: false},
	            dataView : {show: false, readOnly: false},
	            magicType : {show: false, type: ['line']},
	            restore : {show: false},
	            saveAsImage : {show: false}
	        }
	    },
	    calculable : false,
	    xAxis : [
	        {
	            type : 'category',
	            axisLine: {onZero: true},
	            boundaryGap : true,
	            show:true,
	            splitNumber:20,
	            data :'[]'
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            min:'${min}',
	            max:'${max}',
	            boundaryGap:false,
	            splitNumber:6,
	            axisLabel : {
	            	margin:5,
	                formatter: function (value){return value.toFixed(2);}
	            }
	        }
	    ],
	    lineStyle:[
	               {type:"line"}
	               ],
	    series : [
	        {
	            name:'价格',
	            type:'line',
	            symbol:'none',
	            data: '[]',
	            markLine : {
	                data : [
	                    [{name: '',value: '11.40', xAxis: '09:30', yAxis: '11.40'},{xAxis:'15:00',yAxis: '11.40'}]
	    			  ]
                }
	        }
	    ]
	};
var optionKhis ;
optionKhis = {
	    title : {
	        text: ''
	    },
	    tooltip : {
	        trigger: 'axis',
	        formatter: function (params) {
	            var res = params[0].seriesName + ' ' + params[0].name;
	            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
	            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
	            return res;
	        }
	    },
	    legend: {
	        data:['日k线']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: false},
	            dataZoom : {show: false},
	            dataView : {show: false, readOnly: false},
	            magicType: {show: false, type: ['line', 'bar']},
	            restore : {show: false},
	            saveAsImage : {show: false}
	        }
	    },
	    dataZoom : {
	        show : false,
	        realtime: false,
	        start : 0,
	        end : 100
	    },
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : true,
	            show:true,
	            splitNumber:20,
	            axisTick: {onGap:false},
	            splitLine: {show:true},
	            data : []
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            scale:true,
	            boundaryGap: [0.01, 0.01]
	        }
	    ],
	    series : [
	        {
	            name:'',
	            type:'k', 
	            symbol:'none',
	            itemStyle: {
	                normal: {
	                    color: 'red',           // 阳线填充颜色
	                    color0: 'green',   // 阴线填充颜色
	                }
	            },
	            data:[]
	        }/*,
	        {
	            name:'降水量',
	            type:'line',
	            data:[5.6, 5.9, 8.0, 6.4, 6.7, 6.4, 6.6, 7.2, 6.7, 7.8, 6.0, 8.3]
	        }*/
	    ]
	};
/*function connectionToWebsocket(param){
	var url = pushUrl+param;
	ws = new WebSocket(url);
	ws.onopen = function () {
		 console.log("connect to websoket  server success! url="+url);
	};
	ws.onmessage = function (event) {
		try{
			var data=eval("("+event.data+")");
			var msgType = $("#code").val()+'#minqutoes';
			 if(data.msgType==msgType){
				 var obj = eval("("+data.content+")");
				 drawChart(obj);
			 }
		}catch(e){console.log(e);}
	};
	ws.onclose = function(event) {
		 console.log("disconnect to websoket  server success! url="+url);
	};
}
function disconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}
function send(){
	var data = "{msg:'"+message+"',"+"toUser:'"+toUser+"'}";
    ws.send(data);
}*/
function log(txt) {
	var time = new Date().toLocaleString();
	//<b>与服务器连接时间:<font>"+time+"</font></b>
	$('#console').html( txt+"&nbsp;&nbsp;&nbsp;&nbsp;");
	/*
    var console = document.getElementById('console');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    console.value = time1+": "+txt;
    p.appendChild(document.createTextNode(time1+": "+txt));
    console.appendChild(p);
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;*/
}
var context ;
var pushUrl ;
var progress ;
var basePath;
var code ;
var socket;
function main($scope,$q,$http) {
	 $scope.add = function(){
		 $.ajax({
			 url:"../.."+context+"ws/addZxgp.zc"+"?code="+$("#code").val(),
			 async:true,
			 success:function (data){
				 alert("已经为您将"+$("#code").val()+"添加到自选股！");
				  $("#addZx").html("已添加到自选股");
			 },
			 beforeSend :function(xmlHttp){ 
				 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
				 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
			 } 
		 	,error:function (e){
		 		alert("addZx error!");
		 	}
		 });
	 };
	//progress = $.AMUI.progress;
	//progress.set(0.4);
	context = $("#meta").val();
	basePath = $("#basePath").val();
	var defaultParam = "?code="+$("#code").val();
	code = $("#code").val();
	var myChart = echarts.init($("#lineChart")[0]);
	var myChart1 = echarts.init($("#kChart")[0]);
	$.ajax({
		 url:"../.."+context+"ws/kjson.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			/* optionKhis.title.text = data[0].name+"("+data[0].code.substring(2,data[0].code.length)+")";
			 optionKhis.series[0].data=data[0].ks;
			 optionKhis.xAxis[0].data=data[0].dates;
			 myChart1.setOption(optionKhis);*/
			 drawChartK(data[0]);
			// progress.set(0.8);
		 },error:function (e){
		 		alert("request error!");
		 }
	 });
	 $.ajax({
		 url:"../.."+context+"ws/linejson.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			drawChart(data[0]);
			//progress.set(0.6);
		 },
		 beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 	,error:function (e){
	 		alert("request error!");
	 	}
	 });
	 
	 
	 
	 //progress.done(true);
	/* $.ajax({
		 url:"../.."+context+"ws/url.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			 pushUrl = data;
			 connectionToWebsocket("?code="+$("#code").val()+"&type=minqutoes");
		 },
		beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 	,error:function (e){
	 		alert("#"+e);
	 	}
	 });*/
	 $.ajax({
		 url:"../.."+context+"phone/urlIO.zc",
		 async:false,
		 success:function (data){
			 pushUrl=data;
		 }
	 });
	 socket = io.connect(pushUrl);
	 socket.on('connect',function() {
	       console.log("connect to socketio server success! the url is "+pushUrl);
	       socket.emit('getmsg',{type:'MINQUTOES',code:$("#code").val()});
	 });
	 socket.on("connecting", function() {
			console.log("connecting...");
		});
		socket.on('disconnect', function() {
			console.log("disconnect to socketio server");
		});
		socket.on('connect_failed', function() {
			console.log("connect_failed");
			socket.connect();
		});
		socket.on('error', function(data) {
			console.log("error" + data);
			socket.connect();
		});
		/*socket.on('trading', function(data) {
			//console.log(data);
			boolean isTrade = data.isTrade ;
			if(!isTrade){
				console.log("行情结束！");
				socket.disconnect();
			}
		});*/
		socket.on('message', function(data) {
			console.log("message" + data);
		});
		socket.on('anything', function(data) {
			console.log("connection anything" + data);
		});
		socket.on('reconnect_attempt', function() {
			console.log("reconnect_attempt");
		});
		socket.on('reconnect_failed', function() {
			console.log("reconnect_failed");
			socket.connect();
		});
		socket.on('reconnect', function() {
			console.log("reconnect success");
		});
		socket.on('reconnecting', function(data) {
			console.log("reconnecting..." + data);
		});
	   socket.on($("#code").val()+"MINQUTOES",function(data){
		   //console.log(data);
		   drawChart(data);
	   });
}

function refresh() {
	var param = '?code='+$("#code").val();
	connectionToWebsocket(param+"&type=minqutoes");
	var myChart = echarts.init($("#lineChart")[0]);
	 $.ajax({
		 url:"../.."+context+"ws/linejson.zc"+param,
		 async:false,
		 success:function (data){
			 drawChart(data);
		 },
		 beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 });
	 myChart.setOption(optionLine);
}
var count = 0;
function drawChartK(data){
	var myChartk = echarts.init($("#kChart")[0]);
	try{
		if(data.ks.length>0){
			 optionKhis.title.text = data.name+"("+data.code.substring(2,data.code.length)+")";
			 optionKhis.series[0].data=data.ks;
			 optionKhis.xAxis[0].data=data.dates;
		     myChartk.setOption(optionKhis);
	   }
	}catch(e){
	 console.log(e);
	}
}
function drawChart(data){	
	 var myChart = echarts.init($("#lineChart")[0]);
	 optionLine.series[0].data='[]';
	 optionLine.xAxis[0].data='[]';
	 myChart.clear();
	 myChart.setOption(optionLine);
	 optionLine.series[0].data=data.y;
	 optionLine.xAxis[0].data=data.x;
	 optionLine.yAxis[0].max = data.max;
	 optionLine.yAxis[0].min = data.min;
	 optionLine.title.text=data.name+"("+data.code.substring(2,data.code.length)+")";
	 $('#time').html("<b>更新时间&nbsp;&nbsp;<font>"+data.time+"</font> &nbsp;&nbsp;(北京时间)</b>");
	 var color;
	 var flag ;
	 var arrow;
	 if(data.A>0){
		 color='red';
		 flag = "+";
		 arrow = "redupauto";
	 }else  if(data.A<0){
		 color='green'; 
		 flag = "-";
		 arrow = "greendownauto";
	 }else{
		 color='gray'; 
		 flag = "";
	 }
	 var gap = data.gap;
	 if(gap<0) gap = -gap;
	 var ud = '-';
	 var A = data.A;
	 if(data.A>0){
		 ud='+';
	 }else{
		 ud='-';
		 A = -data.A;
	 }
	 $('#dpzs').html("<span>上证:<font size=2 color="+data.colorsh+"><b>"+data.shzsprice+"("+data.shzsA+"%)</b></font>" +
	 		"&nbsp;&nbsp;深成:<font size=2 color="+data.colorsz+"><b>"+data.szzsprice+"("+data.szzsA+"%)</b></font></span>");	 
	 $('#infor').html(
			 "<font size=5 color="+color+"><b>"+data.price+"</b></font>"+
			 "<span class='"+arrow+"' style='display:inline-block;margin-top:10px;'></span><font size=5 color="+color+"><b>"+ud+gap+"("+ud+A+"%)</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '今开:<font size=2 color='+color+'><b>'+data.open.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '昨收:<font size=2 color=gray><b>'+data.close.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '最高:<font size=2 color='+color+'><b>'+data.realmax.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '最低:<font size=2 color='+color+'><b>'+data.realmin.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;<br>"+
			 '成交量:<font size=2 color=gray><b>'+data.cjl.toFixed(2)/10000+"(万)</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '成交额:<font size=2 color=gray><b>'+data.cje.toFixed(2)/10000+"(万)</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"
	 );
	try{
		 //var aveLine = [[{name: '昨日收盘价',value: data[0].close, xAxis: "09:30",yAxis: data[0].close},{ xAxis:"15:00",yAxis: data[0].close}]];
		 if(count==0){ 
			var aveLine = {
	             data : [
	                 [{name: '昨日收盘价',value: data.close, xAxis: '09:30', yAxis: data.close},{xAxis:'15:00',yAxis: data.close}]
	 			  ]
	         };
			 optionLine.series[0].markLine = aveLine;
		 }
	}catch(e){
		console.log(e);
	}
	 count++;
	 myChart.setOption(optionLine);
 }

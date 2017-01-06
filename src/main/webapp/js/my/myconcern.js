var app = angular.module('app',[]);
app.controller('controller',main);
var page ;	    
var basePath ;   
var pushUrl;
var http;
var optionLine ;	
var socket;
optionLine = {
	    title : {
	        text: '',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['日收盘价']
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
	            boundaryGap : false,
	            show:true,
	            splitNumber:20,
	            data :[]
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
	            	margin:10,
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
	            data: [],
	            itemStyle:{
	            	normal:{
	            		lineStyle:{
	            			color: '#0A90F5',// 折线颜色
	            			width:1.5
	            		}
	            	}
	            },
	            markLine : {
	                data : [
	                    [{name: '',value: '11.40', xAxis: '', yAxis: ''},{xAxis:'',yAxis: ''}]
	    			  ]
                }
	        }
	    ]
	};
var msgType ;
function main($scope,$q,$http) {
	page=$scope;
	http = $http;
	$scope.showDialog =showCloseHistory;
	basePath = $("#basePath").val();
	$.ajax({
		 url:basePath+"ws/myConcernJson.zc",
		 async:false,
		 success:function (data){
			 if(data!='noLogin'){
				 $scope.concers = data[0].data;
				 $('#time').html("<font color='gray' size=4>菜单->自选股票</font> &nbsp;&nbsp;&nbsp;&nbsp更新时间："+data[0].time+"&nbsp;&nbsp;&nbsp;&nbsp;北京时间");
			 };
		 }
	 });
	/* $.ajax({
		 url:basePath+"ws/url.zc"+"?code="+$("#username").val(),
		 async:true,
		 success:function (data){
			 pushUrl = data;
			 connectionToWebsocket("?code="+$("#username").val()+"&type=myConcern");
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
		 url:basePath+"phone/urlIO.zc",
		 async:false,
		 success:function (data){
			 pushUrl=data;
		 }
	 });
	 msgType= $("#username").val()+'myConcern';
	 socket = io.connect(pushUrl);
	 socket.on('connect',function() {
	       console.log("connect to socketio server success! the url is "+pushUrl);
	       socket.emit('getmsg',{type:'myConcern',code:$("#username").val()});
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
	   socket.on(msgType,function(data){
		   //console.log(data);
		   $scope.concers = data.data;
		   $('#time').html("<font color='gray' size=4>菜单->自选股票</font> &nbsp;&nbsp;&nbsp;&nbsp更新时间："+data.time+"&nbsp;&nbsp;&nbsp;&nbsp;北京时间");
		   $scope.$apply();
	   });
}

/*function connectionToWebsocket(param){
	var url = pushUrl+param;
	ws = new WebSocket(url);
	ws.onopen = function () {
		$('#console').html("WSPush server is ok,url is "+url);
	};
	ws.onmessage = function (event) {
		var data=eval("("+event.data+")");
		var msgType = $("#username").val()+'#myConcern';
		 if(data.msgType==msgType){
			 var objData = eval("("+data.content+")");
			 $('#time').html("<font color='gray' size=4>菜单->自选股票</font> &nbsp;&nbsp;&nbsp;&nbsp更新时间："+objData[0].time+"&nbsp;&nbsp;&nbsp;&nbsp;北京时间");
			 page.concers = [];
			 page.concers=objData[0].data;
			 page.$apply();
		 }
	};
	ws.onclose = function(event) {
	   $('#console').html("WSPush server is 断开.");
	};
}
*/
function drawChart(data){
	console.log(data);
	 var myChart = echarts.init($("#longShortRateTrend")[0]);
	 optionLine.series[0].data=[];
	 optionLine.xAxis[0].data=[];
//	 myChart.clear();
//	 myChart.setOption(optionLine);
	 optionLine.series[0].data=data[0].y;
	 optionLine.xAxis[0].data=data[0].x;
	 optionLine.yAxis[0].max = data[0].max;
	 optionLine.yAxis[0].min = data[0].min;
	 optionLine.title.text = data[0].name;
	 myChart.setOption(optionLine);
}

var myChart ;
function showCloseHistory(item) {
	page.title="";
	$('#your-modal').modal({
		width : $(window).width() * 0.8,
		height : Math.min($(window).height() * 0.8, 500),
	});
	myChart = echarts.init(document.getElementById('longShortRateTrend'));
	// 图表显示提示信息
	myChart.showLoading({
		text : "图表数据正在努力加载..."
	});

	http.get(basePath+"phone/close.zc"+"?code="+item.CODE_)
			.success(function(data) {
				drawChart(data);
				page.title=item.NAME_;
			}).error(function(data, status, headers, config) {
				myChart.hideLoading();
			});
}
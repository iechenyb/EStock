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
	    	show:false,
	        data:['分钟报价']
	    },
	    toolbox: {
	        show : false,
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
	            itemStyle:{
	            	normal:{
	            		lineStyle:{
	            			color: 'gray', 
	            			color0: 'green', 
	            			width:2
	            		}
	            	}
	            },
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
	            data: '[]',
	            itemStyle:{
	            	normal:{
	            		lineStyle:{
	            			color: 'gray',  	                       
	            			width:2
	            		}
	            	}
	            },
	            markLine : {
	                data : [
	                    [{name: 'haha',value: '4.75', xAxis: '09:30', yAxis: '4.75'},{xAxis:'15:00',yAxis: '4.75'}]
	    			  ]
                }
	        }
	    ]
};
var context ;
var pushUrl ;
var progress ;
var basePath;
var scope ;
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var hour ="";
    var min ="";
    var sec ="";
    if(date.getHours()>=0&&date.getHours()<=9){
    	hour = "0"+date.getHours();
    }else{
    	hour = date.getHours();
    }
    if(date.getMinutes()>=0&&date.getMinutes()<=9){
    	min = "0"+date.getMinutes();
    }else{
    	min = date.getMinutes();
    }
    if(date.getSeconds()>=0&&date.getSeconds()<=9){
    	sec = "0"+date.getSeconds();
    }else{
    	sec = date.getSeconds();
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hour + seperator2 + min
            + seperator2 + sec;
   return currentdate;
}
var socket;
function main($scope,$q,$http) {
	context = $("#meta").val();
	basePath = $("#basePath").val();
	scope = $scope;
	$scope.code = $("#code").val();
	var defaultParam = "?code="+$("#code").val();
	var myChart = echarts.init($("#lineChart")[0]);
	$.ajax({
		 url:"../.."+context+"phone/urlIO.zc",
		 async:false,
		 success:function (data){
			 pushUrl=data;
			 console.log("SocketIO push url is "+data);
		 }
	});
	socket = io.connect(pushUrl);
	$.ajax({
		 url:"../.."+context+"phone/linejson.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			drawChart(data[0]);
		 },
		 beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 	,error:function (e){
	 		alert("request error!");
	 	}
	 });
	  socket.on('connect',function() {
	       console.log("connect to SocketIo server success!");
	       socket.emit('getmsg',{type:'MINQUTOES',code:$("#code").val()});
	   });
		socket.on("connecting", function() {
			console.log("connecting...");
		});
		socket.on('disconnect', function() {
			console.log("disconnect to SocketIo server!");
		});
		socket.on('connect_failed', function() {
			console.log("connect_failed");
			socket.connect();
		});
		socket.on('error', function(data) {
			console.log("error" + data);
			socket.connect();
		});
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
		$("#time1").html("<center><font color=red size=5>页面初始化时间："+getNowFormatDate()+"</font></center>");
		socket.on($("#code").val(),function(data){
			try{
				console.log(scope.code);
			    drawChart(data);
			}catch(e){
				console.log("data="+data+",exce="+e);
			}
		});
	 $("#time1").html("<center><font color=red size=5>页面初始化时间："+getNowFormatDate()+"</font></center>");
}
function emitAgain(){
	 $.ajax({
		 url:"../.."+context+"phone/linejson.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			drawChart(data[0]);
		 },
		 beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 	,error:function (e){
	 		alert("request error!");
	 	}
	 });
	 socket.on('connect',function() {
	       console.log("connect to SocketIo server success!");
	       socket.emit('getmsg',{type:'MINQUTOES',code:$("#code").val()});
	   });
		socket.on("connecting", function() {
			console.log("connecting...");
		});
		socket.on('disconnect', function() {
			console.log("disconnect to SocketIo server!");
		});
		socket.on('connect_failed', function() {
			console.log("connect_failed");
			socket.connect();
		});
		socket.on('error', function(data) {
			console.log("error" + data);
			socket.connect();
		});
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
		$("#time1").html("<center><font color=red size=5>页面初始化时间："+getNowFormatDate()+"</font></center>");
		socket.on($("#code").val(),function(data){
			try{
				console.log(scope.code);
			    drawChart(data);
			}catch(e){
				console.log("data="+data+",exce="+e);
			}
		});
}
function drawChart(data){
	 var myChart = echarts.init($("#lineChart")[0]);
	 optionLine.title.text=data.name;
	 optionLine.series[0].data=data.y;
	 optionLine.xAxis[0].data=data.x;
	 optionLine.yAxis[0].max = data.max;
	 optionLine.yAxis[0].min = data.min;
	 optionLine.series[0].markLine.data[0]= [{name: '昨日收盘参考线',value: data.close, xAxis: '09:30', yAxis: data.close},{xAxis:'15:00',yAxis: data.close}];
	 myChart.setOption(optionLine);
	 $('#infor').html(
	 "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>推送时间 <font color=red size=5>"+data.time+"</font>,"+
	  "深证诚指：<font color='"+data.colorsz+"' size=5>"+data.szzsprice+"</font>,"+
	  "涨幅（涨跌额）：<font color='"+data.colorsz+"' size=5>"+data.szzsA+"%("+data.szzsA1+")</font>,"+
	  "上证指数：<font color='"+data.colorsh+"' size=5>"+data.shzsprice+"</font>,"+
	  "涨幅（涨跌额）：<font color='"+data.colorsh+"' size=5>"+data.shzsA+"%("+data.shzsA1+")</font>"
	 +"</b>");
 }

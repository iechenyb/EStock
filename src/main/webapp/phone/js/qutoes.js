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
	    	show : false,
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
	            show:false,
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
	            	margin:10,
	                formatter: function (value){
	                	var val = value.toFixed(2);
	                	return val;
	                }
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
	            markLine : {
	                data : [
	                    [{name: '',value: '11.40', xAxis: '', yAxis: ''},{xAxis:'',yAxis: ''}]
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
	    	show : false,
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
	            show:false,
	            axisTick: {onGap:false},
	            splitLine: {show:false},
	            data : ['20150101']
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
	            itemStyle:{
	            	normal:{
	            		lineStyle:{
	            			color: 'red',           // 阳线填充颜色
	                        color0: 'green'   // 阴线填充颜色
	            		}
	            	}
	            },
	            data:[[1,2,3,4],[4,3,2,1]]
	        }
	    ]
	};
function connectionToWebsocket(param){
	var url = "ws://"+pushUrl+"websocket"+param;
	ws = new WebSocket(url);
	ws.onopen = function () {
     console.log("connect to websoket  server success! url="+url);
	};
	ws.onmessage = function (event) {
		var data=eval("("+event.data+")");
		var msgType = $("#code").val()+'#minqutoes';
		 if(data.msgType==msgType){
			 var obj = eval("("+data.content+")");
			 drawChart(obj);
		 }
	};
	ws.onclose = function(event) {
		 console.log("websocket disconnection to server! ");
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
}
function log(txt) {
}
var context ;
var pushUrl ;
var progress ;
var basePath;
function main($scope,$q,$http) {
	context = $("#meta").val();
	basePath = $("#basePath").val();
	var defaultParam = "?code="+$("#code").val();
	var myChart = echarts.init($("#lineChart")[0]);
	 $.ajax({
		 url:"../.."+context+"phone/linejson.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			drawChart(data);
			progress.set(0.6);
		 },
		 beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 	,error:function (e){
	 		alert("request error!");
	 	}
	 });
	 var myChart1 = echarts.init($("#kChart")[0]);
	 $.ajax({
		 url:"../.."+context+"phone/kline.zc"+"?code="+$("#code").val(),
		 async:false,
		 success:function (data){
//			 optionKhis.title.text = data[0].name+"("+data[0].code.substring(2,data[0].code.length)+")";
			 optionKhis.series[0].data=data[0].ks;
			 optionKhis.xAxis[0].data=data[0].dates;
		 },error:function (e){
		 		alert("request error!");
		 }
	 });
	 myChart1.setOption(optionKhis);
	 progress.done(true);
	 $.ajax({
		 url:"../.."+context+"phone/url.zc"+"?code="+$("#code").val(),
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
	 });
}

function refresh() {
	var param = '?code='+$("#code").val();
	connectionToWebsocket(param+"&type=minqutoes");
	var myChart = echarts.init($("#lineChart")[0]);
	 $.ajax({
		 url:"../.."+context+"phone/linejson.zc"+param,
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

function drawChart(data){
	 var myChart = echarts.init($("#lineChart")[0]);
	 optionLine.series[0].data='[]';
	 optionLine.xAxis[0].data='[]';
	 myChart.clear();
	 myChart.setOption(optionLine);
	 optionLine.series[0].data=data[0].y;
	 optionLine.xAxis[0].data=data[0].x;
	 optionLine.yAxis[0].max = data[0].max;
	 optionLine.yAxis[0].min = data[0].min;
//	 optionLine.title.text=data[0].name+"("+data[0].code.substring(2,data[0].code.length)+")";
//	 $('#time').html("<b><font>"+data[0].time+"</font> 1分钟前更新 &nbsp;&nbsp;(北京时间)</b>");
	 var color;
	 var flag ;
	 var arrow;
	 if(data[0].A>0){
		 color='red';
		 flag = "+";
		 arrow = "redupauto";
	 }else  if(data[0].A<0){
		 color='green'; 
		 flag = "-";
		 arrow = "greendownauto";
	 }else{
		 color='gray'; 
		 flag = "";
	 }
	 var gap = data[0].gap;
	 if(gap<0) gap = -gap;
	 var ud = '-';
	 var A = data[0].A;
	 if(data[0].A>0){
		 ud='+';
	 }else{
		 ud='-';
		 A = -data[0].A;
	 }
	 var aveLine = [  
	                 [
	                  {    name: '昨日收盘价',
	                	   value: data[0].close, 
	                	   xAxis: data[0].start, 
	                	   yAxis: data[0].close
	                	},
	                    { xAxis:data[0].end,
	                	  yAxis: data[0].close
	                	}
	                 ]
	              ];
	 optionLine.series[0].markLine.data = aveLine;
	 myChart.setOption(optionLine);
 }

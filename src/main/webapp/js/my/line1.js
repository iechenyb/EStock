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
	            //color:'',
	            itemStyle:{
	            	normal:{
	            		lineStyle:{
	            			color: 'red',           // 阳线填充颜色
	                        color0: 'lightgreen',   // 阴线填充颜色
	            			width:1
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
		$('#console').html("");
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
	    $('#console').html("");
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
	progress = $.AMUI.progress;
	progress.set(0.4);
	context = $("#meta").val();
	basePath = $("#basePath").val();
	var defaultParam = "?code="+$("#code").val();
	var myChart = echarts.init($("#lineChart")[0]);
	 $.ajax({
		 url:"../.."+context+"ws/linejson.zc"+"?code="+$("#code").val(),
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
		 url:"../.."+context+"ws/kjson.zc"+"?code="+$("#code").val(),
		 async:false,
		 success:function (data){
			 optionKhis.title.text = data[0].name+"("+data[0].code.substring(2,data[0].code.length)+")";
			 optionKhis.series[0].data=data[0].ks;
			 optionKhis.xAxis[0].data=data[0].dates;
			 progress.set(0.8);
		 },error:function (e){
		 		alert("request error!");
		 }
	 });
	 myChart1.setOption(optionKhis);
	 progress.done(true);
	 $.ajax({
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

function drawChart(data){
	 var myChart = echarts.init($("#lineChart")[0]);
	 optionLine.series[0].data=[];
	 optionLine.xAxis[0].data=[];
	 myChart.clear();
	 myChart.setOption(optionLine);
	 optionLine.series[0].data=data[0].y;
	 optionLine.xAxis[0].data=data[0].x;
	 optionLine.yAxis[0].max = data[0].max;
	 optionLine.yAxis[0].min = data[0].min;
	 optionLine.title.text=data[0].name+"("+data[0].code.substring(2,data[0].code.length)+")";
	 $('#time').html("<b><font>"+data[0].time+"</font> 1分钟前更新 &nbsp;&nbsp;(北京时间)</b>");
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
	 $('#dpzs').html("<span>上证:<font size=2 color="+data[0].colorsh+"><b>"+data[0].shzsprice+"("+data[0].shzsA+"%)</b></font>" +
	 		"&nbsp;&nbsp;深成:<font size=2 color="+data[0].colorsz+"><b>"+data[0].szzsprice+"("+data[0].szzsA+"%)</b></font></span>");	 
	 $('#infor').html(
			 "<font size=5 color="+color+"><b>"+data[0].price+"</b></font>"+
			 "<span class='"+arrow+"' style='display:inline-block;margin-top:10px;'></span><font size=5 color="+color+"><b>"+ud+gap+"("+ud+A+"%)</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '今开:<font size=2 color='+color+'><b>'+data[0].open.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '昨收:<font size=2 color=gray><b>'+data[0].close.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '最高:<font size=2 color='+color+'><b>'+data[0].realmax.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"+
			 '最低:<font size=2 color='+color+'><b>'+data[0].realmin.toFixed(2)+"</b></font>&nbsp;&nbsp;&nbsp;&nbsp;"
	 );
	 /*$('#high').html('<font size=5 color='+color+'><b>最高：'+data[0].max.toFixed(2)+"</b></font>");
	 $('#open').html('<font size=5 color='+color+'><b>今开:'+data[0].open.toFixed(2)+"</b></font>");
	 $('#low').html('<font size=5 color='+color+'><b>最低:'+data[0].min.toFixed(2)+"</b></font>");
	 $('#close').html('<font size=5 color=gray><b>最低:'+data[0].close.toFixed(2)+"</b></font>");*/
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

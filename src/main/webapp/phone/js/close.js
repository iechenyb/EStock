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
	        data:['日收盘价']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['k']},
	            restore : {show: true},
	            saveAsImage : {show: true}
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
function disconnect() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}
var context ;
var pushUrl ;
var progress ;
var basePath;
function main($scope,$q,$http) {
	context = $("#meta").val();
	basePath = $("#basePath").val();
	var defaultParam = "?code="+$("#code").val();
	var myChart = echarts.init($("#closeChart")[0]);
	 $.ajax({
		 url:"../.."+context+"phone/close.zc"+"?code="+$("#code").val(),
		 async:true,
		 success:function (data){
			drawChart(data);
		 },
		 beforeSend :function(xmlHttp){ 
			 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
			 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
		 } 
	 	,error:function (e){
	 		alert("request error!");
	 	}
	 });
}

function refresh() {
	var param = '?code='+$("#code").val();
	connectionToWebsocket(param+"&type=minqutoes");
	var myChart = echarts.init($("#closeChart")[0]);
	 $.ajax({
		 url:"../.."+context+"phone/close.zc"+param,
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
	 var myChart = echarts.init($("#closeChart")[0]);
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

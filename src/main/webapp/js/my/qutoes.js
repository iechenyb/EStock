var app = angular.module('app',[]);
app.controller('controller',main);
var page ;	    
var basePath ;   
var pushUrl;
var http;
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
function main($scope,$q,$http) {
	page=$scope;
	http = $http;
	$scope.showDialog =showCloseHistory;
	basePath = $("#basePath").val();
	$scope.add =addtoZX;
}
function drawChart(data){
	 var myChart = echarts.init($("#longShortRateTrend")[0]);
	 optionLine.series[0].data=[];
	 optionLine.xAxis[0].data=[];
	 optionLine.series[0].data=data[0].y;
	 optionLine.xAxis[0].data=data[0].x;
	 optionLine.yAxis[0].max = data[0].max;
	 optionLine.yAxis[0].min = data[0].min;
	 optionLine.title.text = data[0].name;
	 myChart.setOption(optionLine);
}
function addtoZX(tmpcode){
	 $.ajax({
		 url:basePath+"ws/addZxgp.zc"+"?code="+tmpcode,
		 async:true,
		 success:function (data){
			 alert("已经为您将"+tmpcode+"添加到自选股！");
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
var myChart ;
function showCloseHistory(code) {
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
	http.get(basePath+"phone/close.zc"+"?code="+code)
			.success(function(data) {
				drawChart(data);
//				page.title=item.NAME_;
			}).error(function(data, status, headers, config) {
				myChart.hideLoading();
			});
}
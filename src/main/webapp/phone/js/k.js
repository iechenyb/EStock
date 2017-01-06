var app = angular.module('app',[]);
app.controller('controller',main);
var scope ;	    
var myChart ;
var option ;	 
option = {
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
        	    	show:false,
        	        data:['日k线']
        	    },
        	    toolbox: {
        	        show : false,
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
        	            itemStyle: {
        	                normal: {
        	                    color: 'red',           // 阳线填充颜色
        	                    color0: 'green',   // 阴线填充颜色
        	                }
        	            },
        	            data:[[1,2,3,4],[4,3,2,1]]
        	        }
        	    ]
        	};
var context ;       
function main($scope,$q,$http) {
	context = $("#meta").val();
	var param = '?code='+$("#code").val();
	var myChart = echarts.init(document.getElementById('main'));
	 $.ajax({
		 url:"../.."+context+"phone/kline.zc"+param,
		 async:false,
		 success:function (data){
			// option.title.text = data[0].name;
			 option.series[0].data=data[0].ks;
			 option.xAxis[0].data=data[0].dates;
		 }
	 });
	 myChart.setOption(option);
}

function refresh(){
	var param = '?&code='+$("#code").val();
	var myChart = echarts.init(document.getElementById('main'));
	 $.ajax({
		 url:"../.."+context+"phone/kline.zc"+param,
		 async:false,
		 success:function (data){
			// option.title.text = data[0].name;
			 option.series[0].data=data[0].ks;
			 option.xAxis[0].data=data[0].dates;
		 }
	 });
	 myChart.setOption(option);
}
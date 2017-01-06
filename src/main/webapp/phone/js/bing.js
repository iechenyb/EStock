var app = angular.module('app',[]);
app.controller('controller',main);
var scope ;	    
var myChart ;
var option ;	 
option = {
	    title : {
	        text: '今日涨跌统计',
	        subtext: '',
	        x:'center'
	    },
	    color:['#FF0000', '#008000', '#A9A9A9', '#FF00FF', '#006400','#808088'],
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },legend: {
	    	x:'left',
	    	y:'center',
	        orient: 'vertical',
	        data: ['上涨','下跌','停牌','涨停','跌停','平盘']
	    },
	    series : [
	        {
	            name: '涨跌统计',
	            type: 'pie',
	            radius : '80%',
	            center: ['50%', '60%'],
	            data:[
	                {value:395, name:'上涨'},
	                {value:310, name:'下跌'},
	                {value:234, name:'停牌'}
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0,0,0,1)'
	                }
	            }
	        }
	    ]
	};
var context ;  
var socket ;
var pushUrl;
function main($scope,$q,$http) {
	scope = $scope;
	$('#my-modal-loading').modal();
	context = $("#meta").val();
	var param = '?code='+$("#code").val();
	var myChart = echarts.init(document.getElementById('main'));
	 $.ajax({
		 url:"../.."+context+"phone/dpfx.zc"+param,
		 async:true,
		 success:function (data){
			 var total = 0;
			 $scope.zdfxList = data[0].lst;
			 $scope.dpzs = data[0].dpzs;
			 option.series[0].data=data[0].lst;
			 for ( i=0;i<data[0].lst.length;i++ ){
		    	 total = total+parseInt(data[0].lst[i].value);
		     }
			 $scope.total = total;
			 $scope.$apply();
			 option.color = ['#FF0000', '#008000', '#A9A9A9', '#FF00FF', '#006400','#808088'];
			 myChart.on("click",function(param){
				 try{
				    var para = param.data.type;
//				    window.top.location.href="../ws/zdfx.zc?flag="+para; 
//				    window.top.location.href.target="_blank";
				    $("#page").attr("href","../ws/zdfx.zc?flag="+para);
				    $("#page").attr("target",'_blank');
				    //$("#page").click();
				    document.getElementById("page").click();
			      }catch(e){console.log(param+","+e);}
			   }); 
			 myChart.setOption(option,true);
			 $('#my-modal-loading').modal("close");
		 }
	 });   	
	  $.ajax({
		 url:"../.."+context+"phone/urlIO.zc",
		 async:true,
		 success:function (data){
			 pushUrl=data;
			 socket = io.connect(pushUrl);
			 socket.on('connect',function() {
			       console.log("connect to socketio server success! the url is "+pushUrl);
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
					socket.on('trading', function(data) {
						//console.log(data);
						/*boolean isTrade = data.isTrade ;
						if(!isTrade){
							console.log("行情结束！");
							socket.disconnect();
						}*/
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
				   socket.on('pie',function(data){
					 var total=0;
				     scope.zdfxList = data.lst;
				     for ( i=0;i<data.lst.length;i++ ){
				    	 total = total+parseInt(data.lst[i].value);
				     }
				     scope.total = total;
					 scope.dpzs = data.dpzs;
					 option.series[0].data=data.lst;
					 option.color = ['#FF0000', '#008000', '#A9A9A9', '#FF00FF', '#006400','#808088'];
					 myChart.setOption(option,true);
					 $("#time").html("<center><font color='red' size=3><b>数据推送时间"+data.cur+"</b></font><font color='red' size=3><b><br>页面加载时间:"+getNowFormatDate()+"</b></font></center>");
					 scope.$apply();
				   });
			 });
		 }
	});
}
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
   $("#time").html("<center><font color=red size=5>页面初始化时间："+currentdate+"</font></center>");
   return currentdate;
}

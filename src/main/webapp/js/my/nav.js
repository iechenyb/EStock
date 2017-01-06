var app = angular.module('app',[]);
app.controller('controller',main);
var page ;
var basePath;
function main($scope,$q,$http){
	 page = $scope;
	 $scope.concerns=[];
	 $scope.industrys=[];
	 basePath = $('#basePath').val();
	 var username = $("#username").val();
	 $.ajax({
		 url:basePath+"ws/concern.zc?username="+username,
		 type: "get",
		 dataType: "json",
		 contentType: "application/json; charset=utf-8",
		 async:false,
		 success:function (data){
			 if(data!='noLogin'){
				 $scope.concerns = data;
			 }
		 },
		  error: function (data) {
			 //200的响应也有可能被认定为error，responseText中没有Message部分
			  //alert("server exception!");data.responseText
			  $scope.concerns=[];
		   },
		  complete: function (data) {
			   ;//after success or error
		 }
	 });
	 $.ajax({
		 url:basePath+"ws/industrys.zc",
		 type: "get",
		 dataType: "json",
		 contentType: "application/json; charset=utf-8",
		 async:false,
		 success:function (data){
			 if(data!='noLogin'){
				 $scope.industrys = data;
			 }
		 },
		  error: function (data) {
			 //200的响应也有可能被认定为error，responseText中没有Message部分
			  //alert("server exception!");data.responseText
			  $scope.industrys=[];
		   },
		  complete: function (data) {
			   ;//after success or error
		 }
	 });
}
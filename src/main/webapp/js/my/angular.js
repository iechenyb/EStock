/**
 * first method 
 * <html ng-app>
 * <body ng-controller="controller">
 * 
 */
/*
 function controller($scope) {
  $scope.phones = [
    {"name": "Nexus S",
     "snippet": "Fast just got faster with Nexus S."},
    {"name": "Motorola XOOM™ with Wi-Fi",
     "snippet": "The Next, Next Generation tablet."},
    {"name": "MOTOROLA XOOM™",
     "snippet": "The Next, Next Generation tablet."}
  ];
  $scope.test = "chenyb - test";
}*/
//==========================================
/**
 * second method 
 * <html  ng-app="app">
 * <body ng-controller="controller">
 */
var app = angular.module('app',[]);
app.controller('controller',main);
var scope ;
/*<script>
var myChart = echarts.init(document.getElementById('main'));
var option = {
    ...
}
myChart.setOption(option);
</script>*/
function tclick (){
	alert(1);
	scope.count = scope.count+1;
	scope.phones = [
	                 {"name": "56",
	                  "snippet": "fdghgj"},
	                 {"name": "5665",
	                  "snippet": "1213123."},
	                 {"name": "565™",
	                  "snippet": "3dfgdfgt."}
	               ];
	scope.phones.$apply();
}
function main($scope,$q,$http){
	 $scope.test = "chenyb111111";
	 scope = $scope;
	 scope.test = "xixixi";
	 $scope.testOnclick = tclick; 
	 $scope.phones = [
	                  {"name": "Nexus S",
	                   "snippet": "Fast just got faster with Nexus S."},
	                  {"name": "Motorola XOOM™ with Wi-Fi",
	                   "snippet": "The Next, Next Generation tablet."},
	                  {"name": "MOTOROLA XOOM™",
	                   "snippet": "The Next, Next Generation tablet."}
	                ];
}
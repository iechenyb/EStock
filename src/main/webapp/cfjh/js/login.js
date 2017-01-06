// JavaScript Document
//支持Enter键登录
		document.onkeydown = function(e){
			if($(".bac").length==0)
			{
				if(!e) e = window.event;
				if((e.keyCode || e.which) == 13){
					var obtnLogin=document.getElementById("submit_btn")
					obtnLogin.focus();
				}
			}
		}
		var basepath;
    	$(function(){
			//提交表单
			$('#submit_btn').click(function(){
				basepath = $('#basePath').val();
				show_loading(basepath);
				var page = $('#page').val();
				var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
				if($('#username').val() == ''){
					show_err_msg('<b>用户名还没填呢！</b>',basepath);	
					$('#username').focus();
				}else if($('#yzm').val() == ''){
					show_err_msg('验证码不能为空呢！');
					$('#yzm').focus();
				}else if($('#password').val() == ''){
					show_err_msg('<b>密码还没填呢！</b>',basepath);
					$('#password').focus();
				}else{
					//ajax提交表单，#login_form为表单的ID。 如：$('#login_form').ajaxSubmit(function(data) { ... });
					/*var ajax_option={
							url:"../../user/login.zc",//默认是form action
							success:function(data){
								alert(data);
							}
					}
					$('#login_form').ajaxSubmit(ajax_option);*/
					var params = "?username="+$("#username").val()+"&password="+$("#password").val()+"&yzm="+$("#yzm").val();
					$.ajax({
						 url:basepath+"cfgl/login.zc"+params,
						 async:true,
						 success:function (data){
							 try{
								 if(data[0].ret=='1'){
									 show_msg('<b>登录成功咯！  正在为您转到...</b>',basepath+page+'.jsp',basepath);
								 }else{
									 show_msg('<b>'+data[0].msg+'。</b>','#',basepath);
								 }
							 }catch(e){
								 show_msg('<b>登录验证失败咯！  请检查你的用户名和密码是否正确。</b>','#',basepath);
							 }
							 	
						 },
						 beforeSend :function(xmlHttp){ 
							 xmlHttp.setRequestHeader("If-Modified-Since","0"); 
							 xmlHttp.setRequestHeader("Cache-Control","no-cache"); 
						 } 
					 	,error:function (e){
					 		 show_msg('<b>登录验证失败咯！  请检查你的用户名和密码是否正确。</b>','#',basepath);
					 	}
					 });
				}
			});
		});
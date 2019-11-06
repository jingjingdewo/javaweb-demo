<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
	    <base href="<%=basePath%>">
	    <meta charset="UTF-8">
		<title>用户登录</title>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="css/font.css" type="text/css"></link>
		<link rel="stylesheet" href="css/xadmin.css" type="text/css"></link>
		<script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
  		<script type="text/javascript" src="js/X-admin/xadmin.js"></script>
  		<script type="text/javascript" src="lib/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
  		<style>
  			.text {
				width: 400px;
				position: absolute;
				bottom: 20px;
				right: 20px;
				height: 10px;
				z-index: 3;
				margin: auto;
				text-align: center;
			}
  		</style>
  	</head>
	<body class="login-bg">
		<div class="login layui-anim layui-anim-up">
			<div class="message">Java Web Demo</div>
			<div id="darkbannerwrap"></div>

			<form method="post" class="layui-form">
				<input name="username" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
				<hr class="hr15">
				<input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
				<hr class="hr15">
				<input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
				<hr class="hr20">
			</form>
			<div class="text">
	        	<span>Copyright ©2019 静畏人心 All Rights Reserved</span>
	        </div>
		</div>
		<script>
			$(function() {
				layui.use('form', function() {
					var form = layui.form;
					form.on('submit(login)', function(data) {
						$("[lay-filter=login]").attr("disabled","disabled");
						$("[lay-filter=login]").html("正在登录中...");
						window.setTimeout(function () {
					        $.ajax({
					            url: "log.htm",
					            data: data.field,
					            type: "post",
					            dataType: "json",
					            success: function (data) {
					            	if(data.code==1){
					               		$("[lay-filter=login]").html("验证通过，正在跳转中...");
					                       	window.setTimeout(function () {
					                    		window.location.href = "index.htm";
					                       	}, 1000);
										}else {
					                   	layer.msg(data.msg,{icon: 5});
					               		$("[lay-filter=login]").removeAttr("disabled","disabled");
					               		$("[lay-filter=login]").html("登录");
									}
					            }
					        });
					    }, 500);
						return false;
					});
				});
			})
		</script>
	</body>
</html>

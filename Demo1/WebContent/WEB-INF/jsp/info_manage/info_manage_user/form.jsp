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
		<title>修改用户信息</title>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" href="css/font.css" type="text/css"></link>
		<link rel="stylesheet" href="css/xadmin.css" type="text/css"></link>
		<script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
  		<script type="text/javascript" src="lib/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
  		<script type="text/javascript" src="js/X-admin/xadmin.js"></script>
	
	</head>
	<body>
		<div class="x-body">
			<form class="layui-form" lay-filter="form">
				<input type="hidden" name="id" value="${beanUser.id}"/>
				基本信息
				<hr class="layui-bg-red">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label for="username" class="layui-form-label"> <span class="x-red">*</span>用户名</label>
						<div class="layui-input-inline">
							<input type="text" id="username" name="username" required="" placeholder="输入用户名"
								lay-verify="username" autocomplete="off" class="layui-input" value="${beanUser.username}">
						</div>
					</div>
					<div class="layui-inline">
						<label for="nikename" class="layui-form-label"><span class="x-red">*</span>昵称</label>
						<div class="layui-input-inline">
							<input type="text" id="nikename" name="nikename" required="" placeholder="输入昵称"
								lay-verify="nikename" autocomplete="off" class="layui-input" value="${beanUser.nikename}">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label for="password" class="layui-form-label"> <span class="x-red">*</span>密码</label>
						<div class="layui-input-inline">
							<input type="password" id="password" name="password" required="" placeholder="输入密码"
								lay-verify="password" autocomplete="off" class="layui-input"  value="${beanUser.password}">
						</div>
					</div>
					<div class="layui-inline">
						<label for="password2" class="layui-form-label"> <span class="x-red">*</span>确认密码</label>
						<div class="layui-input-inline">
							<input type="password" id="password2" name="password2" required="" placeholder="确认密码"
								lay-verify="password2" autocomplete="off" class="layui-input" value="${beanUser.password}">
						</div>
					</div>
				</div>
				其他信息
				<hr class="layui-bg-cyan">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label"><span class="x-red">*</span>角色</label>
						<div class="layui-input-block">
							<input type="radio" name="role" value="1" title="普通用户">
							<input type="radio" name="role" value="2" title="高级用户">
						</div>
					</div>
				</div>
				<div class="layui-form-item" align="center">
					<button class="layui-btn" lay-filter="save" lay-submit="">
						保存</button>
				</div>
			</form>
		</div>
		<script>
	        layui.use(['form','layer'], function(){
	            var form = layui.form;
	            var layer = layui.layer;
	        
		        //自定义验证规则
		        form.verify({
		        	username: [/^[A-Za-z0-9]{3,16}$/, '用户名为3-16位数字或英文字母'],
		            nikename: function(value){
		              if(value.length < 2){
		                return '昵称至少得2个字符';
		              }
		            },
		            password: [/(.+){6,16}$/, '密码必须6到16位'],
		            password2: function(value){
		                if($('#pass_yhgl').val()!=$('#repass_yhgl').val()){
		                    return '两次密码不一致';
		                }
		            }
		        });
		        
		        //初始化radio
				$('input:radio[name="role"][value="${beanUser.role}"]').prop('checked', true);
				form.render('radio');
				
				//监听提交
		        form.on('submit(save)', function(data){
		             $.ajax({
						url: data.field.id==''?'ManageUser/Add.htm':'ManageUser/Update.htm' ,
						type: "post",
						async: false,
						data: data.field,
						success: function(res) {
							if(res.code == 0) {
								parent.layer.msg(data.msg, {
		                            time: 2000,
		                            icon: 1
		                        });
								var index = parent.layer.getFrameIndex(window.name); 
								parent.layer.close(index);
								parent.tabelModule.reload({});
							}else {
								parent.layer.msg(data.msg, {
		                            time: 2000,
		                            icon: 5
		                        });
							}
						}
					});
		            return false;
		        });
	        });
	    </script>
	</body>
</html>

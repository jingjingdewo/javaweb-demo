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
  		<script type="text/javascript" src="lib/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
  		<script type="text/javascript" src="js/X-admin/xadmin.js"></script>
  	</head>
  	
  	<body>
		<div class="x-body layui-anim layui-anim-up">
			<blockquote class="layui-elem-quote">你好，<span class="x-red">${beanUser.username}</span>！
			当前时间:2019-11-06 15:43:53</blockquote>
			<fieldset class="layui-elem-field">
				<legend>数据统计</legend>
				<div class="layui-field-box">
					<div class="layui-col-md12">
						<div class="layui-card">
							<div class="layui-card-body">
								<div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
									<div carousel-item="">
										<ul class="layui-row layui-col-space10 layui-this">
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>文章数</h3>
													<p>
														<cite>66</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>会员数</h3>
													<p>
														<cite>12</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>回复数</h3>
													<p>
														<cite>99</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>商品数</h3>
													<p>
														<cite>67</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>文章数</h3>
													<p>
														<cite>67</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>文章数</h3>
													<p>
														<cite>6766</cite></p>
												</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>系统通知</legend>
				<div class="layui-field-box">
					<table class="layui-table" lay-skin="line">
						<tbody>
							<tr>
								<td>
									<a class="x-a" href="/" target="_blank">2019/11/06 第一版Demo上线 </a>
								</td>
							</tr>
							<tr>
								<td>
									<a class="x-a" href="/" target="_blank">有问题请发送邮箱：710564610@qq.com</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>系统信息</legend>
				<div class="layui-field-box">
					<table class="layui-table">
						<tbody>
							<tr>
								<th>版本号</th>
								<td>0.0.1</td>
							</tr>
							<tr>
								<th>网页地址</th>
								<td>http://localhost:8080/Demo1/index.htm</td>
							</tr>
							<tr>
								<th>数据库</th>
								<td>MySql</td>
							</tr>
							<tr>
								<th>前端框架</th>
								<td>layui/X-admin</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>开发团队</legend>
				<div class="layui-field-box">
					<table class="layui-table">
						<tbody>
							<tr>
								<th>版权所有</th>
								<td>@静畏人心</td>
							</tr>
							<tr>
								<th>开发者</th>
								<td>静畏人心(710564610@qq.com)</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<blockquote class="layui-elem-quote layui-quote-nm">一个简单的JavaWeb Demo,方便开发人员使用。</blockquote>
		</div>
		<script>
			
		</script>
	</body>
</html>

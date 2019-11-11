<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="<%=basePath%>">
	    <meta charset="UTF-8">
	    <title>用户管理</title>
	    <meta name="renderer" content="webkit|ie-comp|ie-stand">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
	    <meta http-equiv="Cache-Control" content="no-siteapp"/>
	
	    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
	    <link rel="stylesheet" href="css/font.css" type="text/css"/>
	    <link rel="stylesheet" href="css/xadmin.css" type="text/css"/>
	    <script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
	    <script type="text/javascript" src="lib/layui-v2.5.5/layui/layui.js" charset="utf-8"></script>
	    <script type="text/javascript" src="js/X-admin/xadmin.js"></script>
	</head>
	<body class="layui-anim layui-anim-up">
		<input type="hidden" id="id" value="${beanUser.id}"/>
		<div class="x-nav">
					<span class="layui-breadcrumb">
						<a>首页</a>
						<a>信息管理</a>
						<a><cite>用户列表</cite></a>
					</span>
		    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
		       href="javascript:location.replace(location.href);" title="刷新">
		        <i class="layui-icon" style="line-height:35px">&#xe9aa;</i>
		    </a>
		</div>
		<div class="x-body">
		    <div class="layui-row">
		        <form class="layui-form layui-col-md12 x-so">
		            <input id="start" class="layui-input" placeholder="创建开始日" name="start">
		            <input id="end" class="layui-input" placeholder="创建截止日" name="end">
		            <input id="username" class="layui-input" placeholder="请输入用户名" name="username" type="text" autocomplete="off">
		            <div class="layui-input-inline">
		                <select id="role" name="role" lay-filter="reflashblur">
		                    <option value="">用户类型</option>
		                    <option value="1">普通用户</option>
		                    <option value="2">高级用户</option>
		                    <option value="3">管理员</option>
		                </select>
		            </div>
		            <button class="layui-btn" lay-submit lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
		        </form>
		    </div>
		
		    <script type="text/html" id="toolBar">
        		<div class="layui-btn-container">
            		<button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
            		<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">批量删除</button>
        		</div>
    		</script>
		    <table id="tabelModule" lay-filter="tabelModule"></table>
		    <script type="text/html" id="tableBar">
        		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    		</script>
		</div>
		<script>
		    var tabelModule;
		    layui.use(['table','laypage','form','laydate','layer'], function (){
		        var table = layui.table; //表格
		        var laypage = layui.laypage; //分页
		        var form = layui.form; //表单
		        var laydate = layui.laydate; //日期
		        var layer = layui.layer; //弹层
		
		        laydate.render({    //执行一个laydate实例
		            elem: '#start', //指定元素
		            ready: function (value) {
		                $('#start').html(value); //得到初始的日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
		            }
		        });
		        laydate.render({    //执行一个laydate实例
		            elem: '#end', //指定元素
		            ready: function (value) {
		                $('#end').html(value); //得到初始的日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
		            }
		        });
		
		        tabelModule = table.render({
		            elem: '#tabelModule',
		            id: 'Tabel',
		            height: 'full-160',
		            url: 'ManageUser/table.htm', //数据接口
		            method: 'post',
		            page: true, //开启分页
		            toolbar: '#toolBar',	//左侧按钮
		            //defaultToolbar:[],    //右侧筛选/导出/打印图标
		            cols: [[ //表头
		                {type: 'checkbox', fixed: 'left'},
		                {field: 'id', title: 'ID', width: 280, fixed: 'left'},
		                {field: 'username', title: '用户名', width: 100},
		                {field: 'nikename', title: '昵称', width: 100},
		                {field: 'role', title: '角色', width: 80, sort: true},
		                {field: 'createdate', title: '注册时间', width: 190, sort: true},
		                {fixed: 'right', title: '操作', align: 'center', toolbar: '#tableBar'}
		            ]]
		        });
		
		        //分页
		        laypage.render({
		            elem: 'pageDemo', //分页容器的id
		            count: 100, //总页数
		            skin: '#1E9FFF', //自定义选中色值
		            //skip: true, //开启跳页
		            jump: function (obj, first) {
		                if (!first) {
		                    layer.msg('第' + obj.curr + '页', {
		                        offset: 'b'
		                    });
		                }
		            }
		        });
		
		        //监听头工具栏事件
		        table.on('toolbar(tabelModule)', function (obj) {
		            var checkStatus = table.checkStatus(obj.config.id);
		            var data = checkStatus.data; //获取选中的数据
		            var ids = [];
		            for (var i = 0; i < data.length; i++) {
		                ids.push(data[i].id);
		            }
		            switch (obj.event) {
		                case 'add':
		                    x_admin_show('添加用户', 'ManageUser/form.htm', 720, 400);
		                    break;
		                case 'delete':
		                    if (data.length === 0) {
		                        layer.msg('请选择一行');
		                    } else {
		                        layer.confirm('确定要删除选中的' + data.length + '条数据?', function (index) {
		                            $.ajax({
		                                url: 'ManageUser/BatchDelete.htm',
		                                type: "post",
		                                async: false,
		                                traditional: true,
		                                data: {
		                                    ids: ids
		                                },
		                                success: function (res) {
		                                    if (res.code == 0) {
		                                        table.reload('Tabel', {page: {page: 1}});
		                                        layer.close(index);
		                                    } else {
		                                        layer.msg("删除失败", {icon: 5})
		                                    }
		                                }
		                            });
		                        });
		                    }
		                    break;
		            }
		            ;
		        });
		
		        //监听行工具事件
		        table.on('tool(tabelModule)', function (obj) {
		            var data = obj.data;
		            if (obj.event === 'del') {
		                layer.confirm('确定要删除用户"' + data.username + '"?', function (index) {
		                    $.ajax({
		                        url: 'ManageUser/Delete.htm',
		                        type: "post",
		                        async: false,
		                        data: {
		                            id: data.id
		                        },
		                        success: function (res) {
		                            if (res.code == 0) {
		                                table.reload('Tabel', {page: {page: 1}});
		                                layer.close(index);
		                            } else {
		                                layer.msg("删除失败", {icon: 5})
		                            }
		                        }
		                    });
		                });
		            } else if (obj.event == 'edit') {
		                x_admin_show('编辑用户', 'ManageUser/form.htm?id=' + data.id, 720, 400);
		            }
		        });
		
		        form.on('submit(search)', function (data) {
		            tabelModule.reload({where: data.field, page: {page: 1}});
		            return false;
		        });
		    });
		</script>
	</body>
</html>

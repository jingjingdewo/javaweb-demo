# javaweb-demo

说明：
该项目内容主要是为了一般的javaweb工程开发建立的Demo，前端框架为layui/X-admin，数据库为mysql，软件开发框架spring+springMVC，内容简单易懂，自定义程度较高。
目前保留了用户登录和用户管理模块，其中包含了一般常用的增删改查批处理等操作，能处理基本的业务逻辑内容，命名清晰简洁，方便学习和理解。  

1.首先将项目导入到eclipse/myeclipse/idea中（将目录中的lib.jar解压，放到javaweb-demo\Demo1\WebContent\WEB-INF\lib目录下，github好像上传不了这个外部包，如果项目中出现org.apache报红，手动add External JARS即可）  

2.配置好tomact，将工程add到tomact中  

3.在mysql中create名为demo的数据库，导入demo.sql文件，右键表->设计表->每个字段的注解都写了，方便理解  

4.启动tomact，在浏览器中输入http://localhost:8080/Demo1, 即可进入登录页面，用户名:admin,密码:admin  

5.前端layui开发文档：https://www.layui.com/doc/   （参考demo,需要什么就去网站上复制什么就好了，基本没难度）
登录界面效果图：  
![login](https://github.com/jingjingdewo/javaweb-demo/blob/master/LoginPage.png?raw=true)

用户管理界面效果图：  
![usermanage](https://github.com/jingjingdewo/javaweb-demo/blob/master/UserManage.png?raw=true)

图片有一丢丢大，有意向的朋友耐心等待30s左右  

如有问题可发邮箱：710564610@qq.com 或者加QQ：710564610(备注-javaweb Demo咨询)



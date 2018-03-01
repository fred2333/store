<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Horizon运营商管理平台</title>
	<link rel="stylesheet" type="text/css" href="/Horizon/Back_Admin/css/login.css"/>
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
	<script type="text/javascript" src="/Horizon/jQuery/jquery.form.js"></script>
	<script type="text/javascript">
	$(function() {
		// 单击按钮提交表单
		$(".but").click(function() {
			var getUrl = "${gotoURL}";
			if(getUrl == "") {
				getUrl = "/Back_Admin/main.jsp";
			}
			if($("#username").val() != "" && $("#password").val() != "") {
				$.ajax({
					type: "POST",  
	                url: "/Horizon/admin_login/AdminLoginCtrl",  
	                data: $("#form").serialize(),
	                async: false,
					success: function(data) {
						if(data=="OK") {
							location.href="/Horizon" + getUrl;
						} else {
							$("#msg").text(data);
						}
					},
					error: function() {
						$("#msg").text("登录异常");
					}
				});
				return false;
			}
		});
		// 文本框内容改变则提示文字消失
		$("#username").bind("input propertychange", function() {
			$("#msg").html("&nbsp;");
		});
		$("#password").bind("input propertychange", function() {
			$("#msg").html("&nbsp;");
		});
	});
	</script>
  </head>
  
<body>
<div class="logo">
	<img src="/Horizon/Front/img/mallTitle.PNG" width="173" height="72" alt="Horizon购物商城">
</div>
<div class="loginArea">
	<div id="login">  
	<h1>运营商管理平台</h1>
	<div id="msg" align="center" style="color:red">&nbsp;</div><br>
	<form id="form" >  
		<input type="text" required oninvalid="setCustomValidity('请输入用户名')" oninput="setCustomValidity('')" placeholder="用户名" id="username" name="username"></input>  
		<input type="password" required oninvalid="setCustomValidity('请输入密码')" oninput="setCustomValidity('')" placeholder="密码" id="password" name="password"></input>  
		<button class="but">登录</button>  
	</form>
	</div>  
</div>
</body>  
</html>

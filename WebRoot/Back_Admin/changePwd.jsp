<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
<script type="text/javascript">
$(function() {
	// 提交按钮
	$("#submit").click(function() {
		if($("#oldPwd").val() == "") {
			$("#msg1").text("请输入旧密码");
		}
		if($("#newPwd").val() == "") {
			$("#msg2").text("请输入新密码");
		}
		if($("#conPwd").val() == "") {
			$("#msg3").text("请输入确认新密码");
		}
		
		if($("#oldPwd").val() != "" && $("#newPwd").val() != "" && $("#conPwd").val() != "") {
			if($("#newPwd").val() != $("#conPwd").val()) {
				$("#msg3").text("新密码与确认密码不匹配");
			} else {
				$.ajax({
					type: "POST",  
	                url: "/Horizon/admin_login/AdminLoginCtrl",  
	                data: {
	                	username: "<%=(String)session.getAttribute("adminName") %>",
	                	password: $("#oldPwd").val()
	                },
	                async: false,
					success: function(data) {
						if(data=="密码错误") {
							$("#msg1").text("旧密码错误");
						} else {
							$.ajax({
								type: "POST",  
				                url: "/Horizon/admin/ChangePasswordCtrl",  
				                data: {
				                	username: "<%=(String)session.getAttribute("adminName") %>",
	                				password: $("#newPwd").val()
				                },
				                async: false,
								success: function() {
									alert("密码修改成功。");
								},
								error: function() {
									$("#msg").text("操作异常。");
								}
							});
						}
					},
					error: function() {
						$("#msg").text("操作异常。");
					}
				});
			}
		}
	});
	// 文本框内容改变则提示文字消失
	$("#oldPwd").bind("input propertychange", function() {
		$("#msg1").html("&nbsp;");
	});
	$("#newPwd").bind("input propertychange", function() {
		$("#msg2").html("&nbsp;");
	});
	$("#conPwd").bind("input propertychange", function() {
		$("#msg3").html("&nbsp;");
	});
	
});
</script>
</head>
  
  <body>
  <br><br>
  <form name="form1" method="post" action="">
    <p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旧密码：
      <input type="text" name="oldPwd" id="oldPwd">
    </p>
   	<div align="center" id="msg1" style="color:red">&nbsp;</div>
    <p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新密码：
      <input type="password" name="newPwd" id="newPwd">
    </p>
    <div align="center" id="msg2" style="color:red">&nbsp;</div>
    <p align="center">确认新密码： 
      <input type="password" name="conPwd" id="conPwd">
    </p>
    <div align="center" id="msg3" style="color:red">&nbsp;</div>
    <p align="center">
      <input type="button" id="submit" value="提交">
      <input type="reset" id="reset" value="重置">
    </p>
  </form>
  </body>
  
</html>

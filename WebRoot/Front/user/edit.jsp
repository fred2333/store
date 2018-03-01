<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>pwd.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/Front/css/user/css.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/Front/css/user/edit.css'/>">
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
	<script type="text/javascript" src="<c:url value='/Front/js/user/edit.js'/>"></script>
	<script src="<c:url value='/js/common.js'/>"></script>
  </head>
  
  <body>
    <div class="div0">
    	<span>修改账号信息</span>
    </div>

	<div class="div1">
		<form action="<c:url value='/UserServlet'/>" method="post" target="_top">
			<input type="hidden" name="method" value="updateU"/>
		<table style="border-collapse:separate; border-spacing:10px;">
			<tr> 
				<td><label class="error">${msg }</label></td>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;原邮箱:&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.sessionUser.email}</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;新邮箱:<input class="input" type="text" name="email" id="email" value="${user.email }"/></td>
				<td><label id="emailError" class="error"></label></td>
			</tr>
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;原手机:&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.sessionUser.phone}</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;新手机:<input class="input" type="text" name="phone" id="phone" value="${user.phone }"/></td>
				<td><label id="phoneError" class="error"></label></td>
			</tr>
			<tr>
				<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;原地址:&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.sessionUser.address}</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;新地址:<input class="input" type="text" name="address" id="address" value="${user.address }"/></td>
				<td><label id="addressError" class="error"></label></td>
			</tr>
			<tr>
				<td style="padding-left:70px"><input id="submit" type="submit" value="修改信息"/></td>
			</tr>
		</table>
		</form>
	</div>
  </body>
</html>

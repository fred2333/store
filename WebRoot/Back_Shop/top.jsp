<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<script type="text/javascript" src="/Horizon/Front/js/top.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	a {
		display:inline-block;
		text-decoration:none;
		color: #000000;
		font-weight: 900;
	} 
	a:hover {
		display:inline-block;
		text-decoration: underline;
		color: #000000;
		font-weight: 900;
	}
</style>
  </head>
  
  <body>

<%-- 根据用户是否登录，显示不同的链接 --%>
<c:choose>
	<c:when test="${empty sessionScope.sessionUser }">
		  <a href="<c:url value='/Front/user/login.jsp'/>" target="_parent">Horizon会员登录</a> |&nbsp; 
		  <a href="<c:url value='/Front/user/regist.jsp'/>" target="_parent">注册Horizon会员</a>	
	</c:when>
	<c:otherwise>
		  &nbsp;Horizon会员：${sessionScope.sessionUser.loginname }&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/Front/user/edit.jsp'/>" target="zz">修改账号信息</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/Front/user/pwd.jsp'/>" target="zz">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
	</c:otherwise>
</c:choose>
  </body>
</html>

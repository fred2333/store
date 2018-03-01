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
		font-size: 14px;
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
		  <a href="<c:url value='/CartItemServlet?method=myCart'/>" target="zz">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="zz">我的购物订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/Front/user/edit.jsp'/>" target="zz">修改账号信息</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/Front/user/pwd.jsp'/>" target="zz">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <c:choose>
			<c:when test="${empty param.SetUpShop }">
		 	 <a href="<c:url value='/Front/user/setUpShop.jsp'/>" target="zz"><font color="blue">我要开店</font></a>
		  	</c:when>
		  	<c:when test="${param.SetUpShop eq 'w' }">
		 	 <a href="<c:url value='/Front/user/setUpShopMsg.jsp?msg=w'/>" target="zz"><font color="blue">开店申请 · 审核中</font></a>
		  	</c:when>
		  	<c:when test="${param.SetUpShop eq '0' }">
		 	 <a href="<c:url value='/Front/user/setUpShopMsg.jsp?msg=0'/>" target="zz"><font color="#FF6600">开店申请 · 审核结果</font></a>
		  	</c:when>
		  	<c:when test="${param.SetUpShop eq '1' }">
		 	 <a href="<c:url value='/Front/user/setUpShopMsg.jsp?msg=1'/>" target="zz"><font color="#FF6600">开店申请 · 审核结果</font></a>
		  	</c:when>
		  	<c:when test="${param.SetUpShop eq '2' }">
		 	 <a href="<c:url value='/Back_Shop/main.jsp'/>" target="_Blank"><font color="blue">进入我的店铺</font></a>
		  	</c:when>
		  </c:choose>
		  &nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/Front/user/issue_feedback.jsp'/>" target="zz">问题反馈</a>
	</c:otherwise>
</c:choose>
  </body>
</html>

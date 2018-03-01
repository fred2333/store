<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>店铺首页</title>
    
    <link href="/Horizon/Back_Shop/css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/Horizon/Back_Shop/js/main.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){ 
			if("${param.newInfo}" == "1") {
				alert("请及时单击“修改信息”按钮添加您的店铺信息,添加店铺信息后店铺的创建才能生效。");
				location.href="/Horizon/Back_Shop/main.jsp";
			}
		});
    </script>
</head>
  
  <body>
  <div class="topBar"><iframe frameborder="0" scrolling="no" src="<c:url value='/Back_Shop/top.jsp'/>" id="top" name="top"></iframe></div>
  
  <div class="bodyArea">
	<div class="mallTitle">
	  <img id="imgTitle" src="/Horizon/Back_Shop/img/mallTitle.png" alt="Horizon店铺管理">
	</div>
	<div align="left" class="tabBar">
	  <div id="tab1" class="tabStyle">店铺信息</div>
	  <div id="tab2" class="tabStyle" style="margin-left:105px">商品管理</div>
	  <div id="tab3" class="tabStyle" style="margin-left:210px">订单管理</div>
	  <input type="hidden" class="shopID">
	  <div class="shopName">--- ---</div>
	</div>
    <div class="mainArea"></div>
  </div>
  </body>
</html>

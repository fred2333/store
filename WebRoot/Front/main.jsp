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
    
    <title>Horizon购物商城</title>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <link href="/Horizon/Front/css/main.css" rel="stylesheet" type="text/css">
    <link href="/Horizon/Front/css/jquery.mCustomScrollbar.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/Horizon/jQuery/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="/Horizon/Front/js/main.js"></script>
    <script type="text/javascript">
    	/**
		 * 检查开店申请审核状态
		 */
		function CheckShopApplicationStatus() {
			$.ajax({
				type: "get",
				url: "/Horizon/shop/CheckShopApplicationStatusCtrl",
				data: {
					loginname: "${sessionScope.sessionUser.loginname }"
				},
				success: function(data) {
					if(data == "n") {   //未申请开通店铺
						if($("#top").attr("src") != "/Horizon/Front/top.jsp") {
							$("#top").attr("src", "/Horizon/Front/top.jsp");
						}
					}
					if(data == "w") {   // 开店申请审核中
						if($("#top").attr("src") != "/Horizon/Front/top.jsp?SetUpShop=w") {
							$("#top").attr("src", "/Horizon/Front/top.jsp?SetUpShop=w");
						}
					}
					if(data.substring(0,1) == "0") {   // 开店申请被拒绝
						if($("#top").attr("src") != "/Horizon/Front/top.jsp?SetUpShop=0") {
							$("#top").attr("src", "/Horizon/Front/top.jsp?SetUpShop=0");
						}
					}
					if(data == "1") {   // 开店申请已通过审核
						if($("#top").attr("src") != "/Horizon/Front/top.jsp?SetUpShop=1") {
							$("#top").attr("src", "/Horizon/Front/top.jsp?SetUpShop=1");
						}
					}
				},
			});
		}
		
		/**
		 * 检查用户是否有店铺
		 */
		function CheckHavingShop() {
			if("${sessionScope.sessionUser.loginname }" != "" ) {  // 用户有登录
				$.ajax({
					type: "get",
					url: "/Horizon/shopInfo/GetShopInfoCtrl",
					data: {
						check: "1"
					},
					success: function(data) {
						if(data == "0") {  // 该用户没有店铺
							CheckShopApplicationStatus();
						}
						if(data == "1") {  // 该用户已有店铺
							if($("#top").attr("src") != "/Horizon/Front/top.jsp?SetUpShop=2") {
								$("#top").attr("src", "/Horizon/Front/top.jsp?SetUpShop=2");
							}
						}
					},
				});
			}
		}
		
		$(document).ready(function(){ 
			CheckHavingShop();
			$("#zz").load(function(){
				CheckHavingShop();
			});
		});
    </script>
 	<style type="text/css">
 	iframe {width: 100%; height: 100%}
 	</style>
</head>
  
  <body>
  <div class="topBar"><iframe frameborder="0" scrolling="no" src="<c:url value='/Front/top.jsp'/>" id="top" name="top"></iframe></div>
  
  <div class="bodyArea">
	<div class="list">
		<div align="right" class="mallTitle">
		  <img id="imgTitle" src="/Horizon/Front/img/mallTitle.PNG" alt="Horizon购物商城" width="173" height="72">
		</div>
		<div align="center" class="listItem1">排行榜</div>
		<div align="center" class="listCategory">商品分类</div>
		<div class="listContent">
		</div>
	</div>
	<div align="center" class="commodityArea">
	  <div class="mallTitle">
		  <div class="searchArea">	
			<div class="searchBox">
			  <div class="searchSelect" style="left:0px;" id="select1">店铺</div>
			  <div class="searchSelect" style="left:56px;" id="select2">商品</div>
			  <input name="panduan" type="hidden" value="shangping" id="panduan">
			  <input name="inputBox" type="text" class="searchInput">
			  <div class="searchButton">
				<img src="/Horizon/Front/img/searchButton.png" alt="搜索" width="26" height="26">
			  </div>
			</div>
		  </div>
	  </div>
	  <div id="scrollFlag">
	  <iframe frameborder="0" id="zz" name="zz" class="zz"></iframe>
	  </div>
	</div>
  </div>
  </body>
</html>

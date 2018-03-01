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
    <script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript">
    	$.ajax({
			type: "get",
			url: "/Horizon/shop/CheckShopApplicationStatusCtrl",
			data: {
				loginname: "${sessionScope.sessionUser.loginname }"
			},
			success: function(data) {
				$("#reason").text("拒绝原因：" + data.substring(1,data.Length));
			},
		});
		
		function restartShop() {  // 先删除旧的开店申请审核信息，再跳转到申请页面
			$.ajax({
				type: "get",
				url: "/Horizon/shop/DelShopVerifyInfo",
				data: {
					loginname: "${sessionScope.sessionUser.loginname }"
				},
				success: function(data) {
					location.href="/Horizon/Front/user/setUpShop.jsp";
				},
			});
		}
    </script>
  </head>
  
  <body>
  <c:if test="${param.msg eq 'w' }">
  	<h2 align="center">您的开店申请已进入审核状态，请耐心等待审核结果。</h2>
  </c:if>
  <c:if test="${param.msg eq '0' }">
  	<h2 align="center">您的开店申请已被运营管理员拒绝。</h2><br>
  	<div style="margin-left:17%">
  		<h3 id="reason"></h3><br>
  		您可以直接联系Horizon客服或通过“问题反馈”进一步确认申请被拒绝的原因。<br>
	  	Horizon客服联系电话：nnnn-nnnnnnnn<br>
	  	Horizon客服E-Mail：service@horizon.com<br><br>
	  	<a href="javascript:restartShop()">重新申请开店</a>
  	</div>
  </c:if>
  <c:if test="${param.msg eq '1' }">
  	<h2 align="center">您的开店申请成功通过审核。</h2><br>
  	<h3 align="center"><a target="_Blank" href="/Horizon/Back_Shop/main.jsp?newInfo=1">马上创建店铺</a></h3>
  </c:if>
  </body>
</html>

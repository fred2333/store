<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/Front/css/order/desc.css'/>">
	<script type="text/javascript">
	$(document).ready(function() {
		var valarr = new Array;
		$("#pan").click(function() {
		$("textarea").each(function(i){
			valarr[i] = $(this).val();
		});
		var priv = valarr.join(',');
		$.ajax({
			type: "post",
			data: {method: "add", comm: priv, orderid:"${order.oid}",lname:"${sessionScope.sessionUser.loginname}"},
			url: "/Horizon/CommentServlet",
			success: function(html){
				$("html").html(html);
			}
		});
	});
});
	</script>
  </head>
  
<body>
	<div class="divOrder">
		<span>订单号：${order.oid }
<c:choose>
	<c:when test="${order.status eq 1 }">(等待付款)</c:when>
	<c:when test="${order.status eq 2 }">(准备发货)</c:when>
	<c:when test="${order.status eq 3 }">(等待确认)</c:when>
	<c:when test="${order.status eq 4 }">(交易成功)</c:when>
	<c:when test="${order.status eq 5 }">(已取消)</c:when>
</c:choose>	
		　　　下单时间：${order.ordertime }</span>
	</div>
	<div class="divContent">
		<div class="div2">
		</div>
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名称</th>
							<th class="tt">评论区</th>
						</tr>
<c:forEach items="${order.orderItemList }" var="item">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="350px">
								<div class="Productname">
								  <img align="middle" width="50" src="${item.product.image_b }"/>
								  <a style="margin-left:28px" href="<c:url value='/ProductServlet?method=load&productid=${item.product.productid }&shopid=${item.product.shopid }'/>">${item.product.productName }</a>
								</div>
							</td>
							<td class="td">
								<span><textarea name="comment" rows="4" cols="50"></textarea></span>
							</td> 
						</tr>
</c:forEach>
					</table>
				</dd>
			</dl>
			<center><input style="font-size:20px" type="button" value="上 传 评 论" id="pan"></center>
			<br>
		</div>
	</div>
</body>
</html>


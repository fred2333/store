<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/pager/pager.css'/>" />
	 <script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/Front/css/product/desc.css'/>">
	<script type="text/javascript" src="<c:url value='/Front/js/product/desc.js'/>"></script>
  </head>
  
  <body>
  <div class="shopname">${sp.shopName}</div>
  <div class="descr">${sp.descr}</div>
  <div class="divproductName"></div>
  <div>
    <img align="top" src="${product.image_w }" style="display:block;margin-right:20px" class="img_image_w"/>
    <br>
    <div class="divproductDesc">${product.productName }
	    <ul>
	    	
	    	<li>特价：<span class="price_n">&yen;${product.currPrice }</span></li>
	    	<li>原价：<span class="spanPrice">&yen;${product.price }</span></li>
	    	<li style="font-size:16px;color:Red">销量：${product.salesNum }</li>
	    </ul>
		<hr class="hr1"/>
		<table>
			<tr>
				<td colspan="3">
					品牌：${product.brand }
				</td>
			</tr>
			<tr>
				<td colspan="3">
					生产日期：${product.proDate }
				</td>
			</tr>
			<tr>
				<td colspan="3">进货时间：${product.purDate }</td>
			</tr>
			<tr>
				<td>商品详情： ${product.productDesc}</td>
			</tr>
		</table>
		<div class="divForm">
			<form id="form1" action="<c:url value='/CartItemServlet'/>" method="post">
				<input type="hidden" name="method" value="add"/>
				<input type="hidden" name="productid" value="${product.productid }"/>
				<input type="hidden" name="shopid" value="${product.shopid }"/>
  				我要买：<input id="cnt" style="width: 40px;text-align: center;" type="text" name="quantity" value="1"/>件
  			</form>
  			<a id="btn" href="javascript:$('#form1').submit();"></a>
  		</div>
	</div>
  </div>
  		<div style="margin-top: 430px;">
  		<c:forEach items="${cmlist }" var="comm">
  		<br><br><span style="font-size:27px"> ${comm.loginname}的评论：</span><br><br>
  		<span style="font-size:20px"> ${comm.desc}</span><br>
  		</c:forEach>
  		</div>
  </body>
</html>

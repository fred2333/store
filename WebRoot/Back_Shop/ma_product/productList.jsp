<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<link rel="stylesheet" type="text/css" href="/Horizon/Back_Shop/css/productList.css" />
<link rel="stylesheet" type="text/css" href="/Horizon/pager/pager.css" />
<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
<script type="text/javascript" src="/Horizon/Back_Shop/js/productList.js"></script>
  </head>
  
  <body>
<div class="divProduct">
<ul class="uiStyle">


<c:forEach items="${pb.bean }" var="product">
 <li class="liStyle">
  <div class="inner">
    <input type="hidden" >
    <a class="pic" target="_blank" href="/Horizon/product/GetProductByIDCtrl?productid=${product.productid }">
    	<img class="imgStyle" src="${product.image_b }" border="0"/></a>
    <p class="price" >
		<span class="price_n">&yen;${product.currPrice }</span>
		<span class="price_r">&yen;${product.price }</span>
	</p>
	<p class="pStyle">
		<a id="Productname" title="${product.productName }" target="_blank" href="/Horizon/product/GetProductByIDCtrl?productid=${product.productid }">${product.productName }</a>
	</p>

	<p class="salesVolume" style="font-size:13px">
		<b>销量：${product.salesNum }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
	</p>
  </div>
 </li>
</c:forEach>

</ul>
</div>
<div class="divStyle" style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/pager/pager.jsp" %>
</div>
  </body>
 
</html>


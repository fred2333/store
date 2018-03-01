<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="/Horizon/pager/pager.css" />
	<link rel="stylesheet" type="text/css" href="/Horizon/Front/css/product/list.css" />
     <script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
	<script type="text/javascript" src="<c:url value='/Front/js/product/list.js'/>"></script>
  </head>
  
  <body>
	<div class="intro">&nbsp;&nbsp;&nbsp; ${sp.shopName} &nbsp;&nbsp;<span style="font-size:20px">上架商品</span></div>
<ul>
<c:forEach items="${pb.beanList }" var="product">
  <li>
  <div class="inner">
    <a class="pic" href="<c:url value='/ProductServlet?method=load&productid=${product.productid }&shopid=${product.shopid }'/>"><img src="${product.image_b }" border="0"/></a>
    <p class="price">
		<span class="price_n">&yen;${product.currPrice }</span>
		<span class="price_r">&yen;${product.price }</span>
	</p>
	<p><a id="Productname" title="${product.productName }" href="<c:url value='/ProductServlet?method=load&productid=${product.productid }&shopid=${product.shopid }'/>">${product.productName }</a></p>
	<%-- url标签会自动对参数进行url编码
	<c:url value="/ProductServlet" var="authorUrl">
		<c:param name="method" value="findByAuthor"/>
		<c:param name="author" value="${product.author }"/>
	</c:url>
	<c:url value="/ProductServlet" var="pressUrl">
		<c:param name="method" value="findByPress"/>
		<c:param name="press" value="${product.press }"/>
	</c:url>
	 --%>
	<p><span>品牌： </span>${product.brand }</p>
	<p class="publishing">
		<span>生产日期：</span>${product.proDate }
		</p>
	<p class="publishing_time"><span>进货时间：</span>${product.purDate }</p>
  </div>
  </li>
</c:forEach>
</ul>

<div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/pager/pager_order.jsp" %>
</div>

  </body>
 
</html>


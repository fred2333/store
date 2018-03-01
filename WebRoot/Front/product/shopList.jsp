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

    <style type="text/css">
<!--
table {
	border-bottom: 0;
	border-top: 0;
	border-right: 0;
	border-left: 0;
}

th {
	border-bottom-style: solid;
	border-bottom-color: #999999;
	border-top: 0;
	border-right: 0;
	border-left: 0;
}

td {
	border-bottom-style: solid;
	border-bottom-color: #999999;
	border-top: 0;
	border-right: 0;
	border-left: 0;
}
-->
    </style>
    <script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript">
		function getImg(shopid) {
			$.ajax({
				type: "get",
				url: "/Horizon/ProductServlet",
				data: {
					method:"getProImgPath",
					shopid: shopid,
					imgNum: "0"
				},
				success: function(data) {
					$("#i_" + shopid).append("<img height='100' src='" + data + "'></img>");
				}
			});
			$.ajax({
				type: "get",
				url: "/Horizon/ProductServlet",
				data: {
					method:"getProImgPath",
					shopid: shopid,
					imgNum: "1"
				},
				success: function(data) {
					$("#i_" + shopid).append("&nbsp;&nbsp;&nbsp;<img height='100' src='" + data + "'></img>");
				}
			});
		}
	</script>
    
</head>
  
  <body>
  <table width="722" border="1" align="center" cellspacing="0" cellpadding="0">
    <tr>
      <th height="34" colspan="3" align="left" scope="col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索到的店铺：</th>
    </tr>
<c:forEach items="${pb.beanList }" var="shop">
    <tr height="50">
      <td width="228" align="left">&nbsp;&nbsp;<a href="/Horizon/ProductServlet?method=findByShop&shopid=${shop.shopid }">${shop.shopname }</a></td>
      <td align="left">&nbsp;&nbsp;主营：${shop.busi }</td>
      <td width="230" align="center">
      	<div id="i_${shop.shopid }"></div>
      </td>
    </tr>
    <script type="text/javascript">getImg("${shop.shopid }");</script>
</c:forEach>
  </table>
  </body>
  
</html>

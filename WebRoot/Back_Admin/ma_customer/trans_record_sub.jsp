<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<link href="/Horizon/Back_Admin/css/ma_info.css" rel="stylesheet"
		type="text/css">
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/Horizon/Back_Admin/js/trans_record.js"></script>
</head>

<body>
	<div style="height:40px">
		<input type="button" value="返回" 
		onClick="location.href='/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getTrRecordList&currentPage=1&loginName='">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顾客：${param.loginName }
	</div>
	<div style="height:40px" align="center">
		搜索下单日期范围：
		<input type="text" id="startDate" style="width:200px" placeholder="开始日期">
		&nbsp;&nbsp;—&nbsp;&nbsp;
		<input type="text" id="endDate" style="width:200px" placeholder="结束日期">&nbsp;&nbsp;
		<input type="button" value="搜索" 
		onClick="location.href='/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getOrderList&currentPage=1&loginName=encodeURI(encodeURI(${param.loginName }))&startDate=' + $('#startDate').val() + '&endDate=' + $('#endDate').val()">
	</div>
	<table width="100%" class="imagetable" cellspacing="0" cellpadding="0">
		<tr>
			<th width="200">交易订单ID</th>
			<th>下单时间</th>
			<th>消费金额</th>
			<th>交易状态</th>
		</tr>
		<c:forEach items="${pb.bean }" var="order">
			<tr align="center">
				<td>${order.oid }</td>
				<td>${order.ordertime }</td>
				<td>${order.total }</td>
		<c:choose>
			<c:when test="${order.status==1 }"><td>未付款</td></c:when>
			<c:when test="${order.status==2 }"><td>未发货</td></c:when>
			<c:when test="${order.status==3 }"><td>未确认收货</td></c:when>
			<c:when test="${order.status==4 }"><td>交易成功</td></c:when>
			<c:when test="${order.status==5 }"><td>已取消</td></c:when>
		</c:choose>
			</tr>
		</c:forEach>
	</table>

	<!--分页 -->
	<div class="pager" align="right">
		<c:if test="${pb.currentPage<=1 }">
			<div style="position: relative;font-size:20px;font-weight:900;width:30px;height:30px;float:left;left:20%" align="center">--</div>
		</c:if>
		<c:if test="${pb.currentPage>1 }">
			<div style="position: relative;font-size:20px;font-weight:900;width:90px;height:30px;float:left;left:20%" align="center"><a href="${pb.url }&currentPage=${pb.currentPage-1}">上一页</a></div>
		</c:if>

		<c:forEach begin="${pb.currentPage-5>0?pb.currentPage-5:1}"
			end="${pb.currentPage + 4 > pb.totalPage ? pb.totalPage : pb.currentPage + 4 }" var="i">
			<div style="position: relative;font-size:20px;font-weight:900;width:30px;height:30px;float:left;left:25%" align="center"><a href="${pb.url }&currentPage=${i }">${i}</a></div>
		</c:forEach>

		<c:if test="${pb.currentPage>=pb.totalPage }">
			<div style="position: relative;font-size:20px;font-weight:900;width:30px;height:30px;float:left;left:30%" align="center">--</div>
		</c:if>
		<c:if test="${pb.currentPage<pb.totalPage }">
			<div style="position: relative;font-size:20px;font-weight:900;width:90px;height:30px;float:left;left:30%" align="center">
				<a href="${pb.url }&currentPage=${pb.currentPage+1}">下一页</a>
			</div>
		</c:if>
		<div style="position: relative;font-size:20px;font-weight:900;height:30px;float:left;left:40%" align="center">共${pb.totalPage}页，第${pb.currentPage}页</div>
	</div>
	<!-- 分页结束  -->
</body>
</html>

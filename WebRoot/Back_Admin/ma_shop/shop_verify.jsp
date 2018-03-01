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
	<link href="/Horizon/Back_Admin/css/shop_verify.css" rel="stylesheet"
		type="text/css">
	<style type="text/css">
    	.pop_up {
    		position:absolute;
    		width:600px;
    		height:330px;
    		left:50%;
    		margin-left:-300px;
    		top:30px;
    		background-color:#c5d0f7;
    		z-index:10;
    		display:none;
    	}
	</style> 
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/Horizon/Back_Admin/js/shop_verify.js"></script>
</head>

<body>
	<div style="height:40px" align="center">
		搜索用户名/真实姓名：
		<input type="text" id="searchUser" style="width:300px">&nbsp;&nbsp;
		<input type="button" value="搜索" 
		onClick="location.href='/Horizon/admin/ma_shop/ShopManagementCtrl?method=shopVerifyList&currentPage=1&keyword='+encodeURI(encodeURI($('#searchUser').val()))">
	</div>
	<table width="100%" class="imagetable" cellspacing="0" cellpadding="0">
		<tr>
		  	<th width="123">用户名</th>
			<th width="78">真实姓名</th>
			<th>身份证号码</th>
			<th>常用地址</th>
			<th>联系电话</th>
			<th>开户银行</th>
			<th>银行卡号</th>
			<th width="100">审核结果</th>
			<th width="50">操作</th>
		</tr>
		<c:forEach items="${pb.bean }" var="shopVerify">
			<tr align="center">
			  	<td >${shopVerify.loginname }</td>
				<td>${shopVerify.name }</td>
				<td>${shopVerify.idcardNum }</td>
				<td>${shopVerify.address }</td>
				<td>${shopVerify.phone }</td>
				<td>${shopVerify.bank }</td>
				<td>${shopVerify.bankcardNum }</td>
				<td>
			<c:choose>
				<c:when test="${empty  shopVerify.status }">
					<a href="javascript:allow('${shopVerify.loginname }')">允许</a>
					<a href="javascript:refuse('${shopVerify.loginname }')">拒绝</a>
				</c:when>
				<c:when test="${shopVerify.status==0 }">已拒绝</c:when>
				<c:when test="${shopVerify.status==1 }">已允许</c:when>
			</c:choose>
				</td>
				<td><a href="javascript:delVerifyInfo('${shopVerify.loginname }')">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pop_up" >
		<br>
		<table width="560" height="293" border="0" align="center">
		  <tr>
		    <th height="37" align="left" scope="col">开店申请用户：<span class="login_name"></span></th>
	      </tr>
		  <tr>
			<th width="560" height="46" align="left" scope="col">请输入开店申请拒绝理由: </th>
		  </tr>
		  <tr>
			<td height="160" align="left" valign="top">
				<textarea id="reason" style="height:160px;width:560px;font-size:16px" ></textarea>
			</td>
		  </tr>
		  <tr>
			<td height="40" align="center" valign="middle">
				<input type="button" value="提交" onClick="submitRefuse($('.login_name').text())"></td>
		  </tr>
	  </table>

			<br>
			<br>
			<br>
	</div>

	<!--分页 -->
	<form action="${pb.url }&currentPage=${pb.currentPage-1}" method="post" id="last">
		<input type="hidden" name="keyword" value="${param.keyword }">
	</form>
	<form action="${pb.url }&currentPage=${pb.currentPage+1}" method="post" id="next">
		<input type="hidden" name="keyword" value="${param.keyword }">
	</form>
	<div class="pager" align="right">
		<c:if test="${pb.currentPage<=1 }">
			<div style="position: relative;font-size:20px;font-weight:900;width:30px;height:30px;float:left;left:20%" align="center">--</div>
		</c:if>
		<c:if test="${pb.currentPage>1 }">
			<div style="position: relative;font-size:20px;font-weight:900;width:90px;height:30px;float:left;left:20%" align="center"><a href="javascript:$('#last').submit()">上一页</a></div>
		</c:if>

		<c:forEach begin="${pb.currentPage-5>0?pb.currentPage-5:1}"
			end="${pb.currentPage + 4 > pb.totalPage ? pb.totalPage : pb.currentPage + 4 }" var="i">
			<form action="${pb.url }&currentPage=${i}" method="post" id="${i}">
				<input type="hidden" name="keyword" value="${param.keyword }">
			</form>
			<div style="position: relative;font-size:20px;font-weight:900;width:30px;height:30px;float:left;left:25%" align="center"><a href="javascript:$('#${i}').submit()">${i}</a></div>
		</c:forEach>

		<c:if test="${pb.currentPage>=pb.totalPage }">
			<div style="position: relative;font-size:20px;font-weight:900;width:30px;height:30px;float:left;left:30%" align="center">--</div>
		</c:if>
		<c:if test="${pb.currentPage<pb.totalPage }">
			<div style="position: relative;font-size:20px;font-weight:900;width:90px;height:30px;float:left;left:30%" align="center">
				<a href="javascript:$('#next').submit()">下一页</a>
			</div>
		</c:if>
		<div style="position: relative;font-size:20px;font-weight:900;height:30px;float:left;left:40%" align="center">共${pb.totalPage}页，第${pb.currentPage}页</div>
	</div>
	<!-- 分页结束  -->
</body>
</html>

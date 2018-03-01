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
    <script type="text/javascript" src="/Horizon/Back_Admin/js/issue_feedback.js"></script>
    <style type="text/css">
    	.pop_up {
    		position:absolute;
    		width:800px;
    		height:330px;
    		left:50%;
    		margin-left:-400px;
    		top:30px;
    		background-color:#c5d0f7;
    		z-index:10;
    		display:none;
    	}
	</style> 
</head>

<body>
	<div style="height:40px" align="center">
		搜索登录名/问题标题/问题内容：
		<input type="text" id="searchIss" style="width:300px">&nbsp;&nbsp;
		<input type="button" value="搜索" 
		onClick="location.href='/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getIssueList&currentPage=1&keyword='+encodeURI(encodeURI($('#searchIss').val()))">
	</div>
	<table width="100%" class="imagetable" cellspacing="0" cellpadding="0">
		<tr>
			<th width="200">顾客登录名</th>
			<th>回复邮箱地址</th>
			<th>问题标题</th>
			<th>发布时间</th>
			<th colspan="2">操作</th>
		</tr>
		<c:forEach items="${pb.bean }" var="issue">
			<tr align="center">
				<td>${issue.loginname }</td>
				<td>${issue.email }</td>
				<td><span id="tit_${issue.issueid }">${issue.issue_title }</span></td>
				<td>${issue.datetime }</td>
				<td width="100"><a href="javascript:showIssue('${issue.issueid }','${issue.loginname }')">查看内容</a></td>
				<td width="75"><a href="javascript:delIssue('${issue.issueid }')">删除</a></td>
			</tr>
			<input type="hidden" id="con_${issue.issueid }" value="${issue.issue_con }">
		</c:forEach>
	</table>
	<div class="pop_up" >
		<br>
		<table width="671" height="293" border="0" align="center">
		  <tr>
		    <th height="37" align="left" scope="col">顾客：<span class="login_name"></span></th>
	      </tr>
		  <tr>
			<th width="640" height="46" align="left" scope="col">标题: <span class="issue_title"></span></th>
		  </tr>
		  <tr>
			<td height="160" align="left" valign="top"><span class="iss_con"></span></td>
		  </tr>
		  <tr>
			<td height="40" align="center" valign="middle">
				<input type="button" value="关闭" onClick="$('.pop_up').css({'display':'none'})"></td>
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

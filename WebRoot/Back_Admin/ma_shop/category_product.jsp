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
    <link rel="stylesheet" type="text/css" href="/Horizon/Back_Admin/css/category_product.css">
    <script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/Horizon/Back_Admin/js/category_product.js"></script>
  </head>
  
  <body>
    <h2 style="text-align: center;"><strong>商品分类列表</strong></h2>
	<div class="AddOneLevel">
	  <img src="/Horizon/Back_Admin/img/addCategory.png" height="150" width="150" style="margin-top:-54px">
	  <div style="margin-top:-93px;color:#0000CC" align="center"><strong>添加一级分类</strong></div>
	</div>
    <table width="762" border="1" align="center" cellpadding="0" cellspacing="0">
    	<tr class="trTitle">
    	  <th width="90">级别</th>
    		<th width="25%">分类名称</th>
    		<th>描述</th>
    		<th colspan="3">操作</th>
    	</tr>
    	
	<c:forEach items="${parents }" var="parent">    	
	    	<tr class="trOneLevel" id="${parent.cid }">
	    	  <td><div align="center">一级分类</div></td>
	    		<td align="center">${parent.cname }</td>
	    		<td align="center">${parent.desc }</td>
	    		<td width="113">
	    		  <div align="center" id="${parent.cid }" class="AddTwoLevel"><a style="cursor:pointer">添加二级分类</a></div></td>
	    	    <td width="55"><div align="center" class="modify1"><a style="cursor:pointer">修改</a></div></td>
	    	    <td width="48"><div align="center" class="del1"><a style="cursor:pointer">删除</a></div></td>
	    	</tr>
	   <c:forEach items="${parent.children }" var="child">
	    	<tr class="trTwoLevel" id="${child.cid }">
	    	  <td id="${parent.cid }" align="right">二级分类</td>
	    		<td align="center">${child.cname }</td>
	    		<td align="center">${child.desc }</td>
	    		<td colspan="2">
	    		  <div align="center" class="modify2"><strong><a style="cursor:pointer">修改</a></strong></div></td>
	    	    <td align="right"><div align="center" class="del2"><strong><a style="cursor:pointer">删除</a></strong></div></td>
	    	</tr>
	   </c:forEach>
	</c:forEach>
  </table>
  
    <div align="center" class="pop_up">
		<h2 id="popTitle">添加一级分类</h2>
		<input type="hidden" id="currentID" >
		<div align="left" style="margin-left: 30px;">
			<font size="3"><b>分类名称：</b></font><br>
			<input id="caName" type="text" value="" style="height:30px;width:285px;font-size:14px">
		</div>
		<div align="left" id="selectCate" style="margin-left: 30px; margin-top:10px">
			<font size="3"><b>一级分类：</b></font><br>
			<select name="pid" id="pid" style="height:30px;width:285px">
<c:forEach items="${parents }" var="parent">
    		<option value="${parent.cid }" <c:if test="${parent.cid eq child.parent.cid }">selected="selected"</c:if> >${parent.cname }</option>
</c:forEach>
    	</select>
		</div>
		<div align="left" style="margin-left: 30px; margin-top:10px">
			<font size="3"><b>分类描述：</b></font><br>
			<textarea id="desc" rows="6" style="width:285px;font-size:15px"></textarea>
		</div>
		<div align="center" style="margin-top:20px;">
			<input id="ok" type="button" value="确定">&nbsp;&nbsp;&nbsp;
			<input id="cancel" type="button" value="取消">
		</div>
	</div>
  </body>
</html>

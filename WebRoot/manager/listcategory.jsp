<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>列出所有分类</title>
    
  </head>
  
  <body style="text-align:center;">
  	<h2>分类信息</h2>
  	<table border="1" width="50%" align="center">
  		<tr style="text-align:center;">
  			<td>分类名称</td>
  			<td>分类描述</td>
  			<td>操作</td>
  		</tr>
  		<c:forEach var="c" items="${categories }">
	  		<tr style="text-align:center">
	  			<td>${c.name}</td>
	  			<td>${c.description}</td>
	  			<td>
	  			<!-- 没有什么用 -->
	  				<!-- <a href='javascript:void(0)'>删除</a> -->
	  				<a href="${pageContext.request.contextPath }/manager/DeleteCategoryServlet?category_id=${c.id}">删除</a>
	  				<!--  <a href='javascript:void(0)'>修改</a> --> 
	  				<a href="${pageContext.request.contextPath }/manager/UpdateCategoryServlet?method=getCategory&category_id=${c.id}">修改</a>
	  			</td>
	  		</tr>
  		</c:forEach>
  	</table>

  </body>
</html>

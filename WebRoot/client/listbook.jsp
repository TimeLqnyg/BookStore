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
    
    <title>My JSP 'listbook.jsp' starting page</title>
    <style type="text/css">
   table
  {
  border-collapse:collapse;
  }

table,th, td
  {
  border: 0px solid black;
  }
  	a{
			color: #888;
		}
		
		a:HOVER{
			color: #FF832C;
		}
   </style>
	

  </head>
  
  <body>
  						<table >
  						<td>
						<div id="image" style ="float:left;">
  							<img src="${pageContext.request.contextPath }/images/${book.image}" height=400 width=250/>
  						</div>
  						</td>
  						<td>
  						<div id="bookindo" style="float:right;text-align:left;font-size: 20">
  							<ul>
  								<li>${book.name }</li>
  								<li>作者：${book.author }</li>
  								<li>售价：${book.price }</li>
  								<li>
  									简介：${book.description }
  								</li>
  								<li>
  									<a href="${pageContext.request.contextPath }/client/BuyServlet?bookid=${book.id}">加入购物车</a>
  								</li>
  							</ul>
  						</div>
  						</td>
  						</table>
  </body>
</html>

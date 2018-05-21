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
    
    <title>首页头</title>
    
    <style type="text/css">
    	body{
    		background-image: url('${pageContext.request.contextPath }/images/head_background2.jpg');
    		background-repeat: no-repeat;
    		background-size: 150px 120px;
    		background-position:em;
    	}
    	a{
    		color: #888;
    	}
    	
    	a:HOVER {
			color: #FF832C;
		}
		.abc{ 
  			height: 37px;
  			background: gray url('${pageContext.request.contextPath }/images/top.png');
			}
    </style>
  </head>
  
  <body style="text-align:center;">

  	<div style="width: 100%;background:black;">
   <div style="float: right;">
   		<c:if test="${user==null }">
   		<form action="${pageContext.request.contextPath}/client/LoginServlet " method="post">
   			用户名：<input type="text" name="username" style="width:60px"/>
   			密码：<input type="password" name="password" style="width:60px"/>
   			<input type="submit" value="登录"/>
   			<input type="button" value="注册" 
   			onclick="javascript:window.parent.body.location.href='${pageContext.request.contextPath}/client/register.jsp'"/>
   		</form>
   		</c:if>
   		
   		<c:if test="${user!=null }">
   			欢迎您：${user.username } <a href="${pageContext.request.contextPath }/client/LoginOutServlet">注销</a>
   		</c:if>
   </div>

   </div>
   <br/>
   <div>
   <p style="font-size:40px">NEW TECH BOOKSTORE</p>
   </div>
   <div class="abc">
   		<a href="${pageContext.request.contextPath }/client/IndexServlet?method=getAll" target="body">首页</a> &nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="${pageContext.request.contextPath }/client/listcart.jsp" target="body">查看购物车</a>&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="${pageContext.request.contextPath }/client/ClientListOrderServlet?userid=${user.id}" target="body">查看订单</a>
   </div>

  </body>
</html>

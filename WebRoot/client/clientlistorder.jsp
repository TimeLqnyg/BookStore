<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户端显示订单</title>
    
<style type="text/css">
   table
  {
  border-collapse:collapse;
  }

table,th, td
  {
  border: 1px solid black;
  }
   a{
  color: #888;
  font-size:20px;
  line-height: 10px;
  margin:10px;
  float: center;
}

a:hover{
  color: #FF832C;
}  
  #footer{
  margin-top: 15px;
  height: 120px;
}

#footer ul{
  height: 40px;
  background: #EEE;
}

#footer li{
  float: left;
  margin: 10px;
}

#footer address{
  font-size:12px;
  font-family:'微软雅黑'；
  margin: 10px;
}
  
   </style>
  </head>
  
  <body style="text-align:center;">
	<h2>订单列表</h2>
	<table width="80%" border="1" align="center" style="text-align: center;">
		<tr>
			<td>订单号</td>
			<td>订单人</td>
			<td>订单时间</td>
			<td>订单状态</td>
			<td>总价</td>
			<td>操作</td>
		</tr>
		<c:forEach var="order" items="${orders }">
			<tr>
				<td>${order.id }</td>
				<td>${order.user.username }</td>
				<td>${order.ordertime }</td>
				<td>${order.state }</td>
				<td>${order.price }</td>
				<td>
					<a href="${pageContext.request.contextPath }/client/ClientOrderDetailServlet?orderid=${order.id}">查看明细</a>
					<a href="${pageContext.request.contextPath }/client/DeleteOrderServlet?orderid=${order.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<table width="80%" border="1" align="center" style="text-align: center;">
		<tr>
			<td><img width="350px" height="250px" alt="" src="${pageContext.request.contextPath }/images/img_4.jpg" ></td>
			<td><img width="350px" height="250px" alt="" src="${pageContext.request.contextPath }/images/img_5.jpg" ></td>
			<td><img width="350px" height="250px" alt="" src="${pageContext.request.contextPath }/images/img_6.jpg" ></td>
		</tr>
		
	</table>
	<div id ="footer">
            <ul>
              <li><a href="#">常用文档下载</a></li>
              <li><a href="#">支付方式</a></li>
              <li><a href="#">网站地图</a></li>
              <li><a href="#">合作伙伴</a></li>
              <li><a href="#">友情链接</a></li>
              <li><a href="#">在线咨询</a></li>
            </ul>
            <address>&copy; 2017 江苏科技大学 版权所有 http://www.just.com 苏ICP备07020337号</address> 
          </div>
  </body>
</html>

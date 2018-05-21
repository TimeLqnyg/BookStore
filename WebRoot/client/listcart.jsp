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
    
    <title>购物车显示列表</title>
   <style type="text/css">
   table
  {
  border-collapse:collapse;
  }

table,th, td
  {
  border: 1px solid black;
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
   </style>
  </head>
  	
  <body style="text-align:center;">
 	<c:if test="${user==null }">
 		对不起  请先登录
 	</c:if>
 	<c:if test="${user!=null }">
 		<H2>购物车列表</H2>
 		<table width="40%" border="1" align="center" style="text-align:center;font-weight: bold;font-family: 'Monotype Corsiva'">
	 		<tr>
	 			<td>书名</td>
	    		<td>作者</td>
	    		<td>单价</td>
	    		<td>数量</td>
	    		<td>小计</td>
	    		<td>操作</td>
	 		</tr>
	 		<c:forEach var ="me" items="${cart.map }">
	 		<tr>
	 			<td>${me.value.book.name }</td>
	 			<td>${me.value.book.author }</td>
	 			<td>${me.value.book.price }</td>
	 			<td>${me.value.quantity}</td>
	 			<td>${me.value.price}</td>
	 			<td>
	 				<a href='javascript:void(0)' >删除</a>
	 			</td>
	 		</tr>
	 		</c:forEach>
	 		<tr>
	 			<td colspan="1">总价</td>
	 			<td colspan="5">${cart.price }</td>
	 		</tr>
 		</table>
 		<a href="${pageContext.request.contextPath }/client/OrderServlet">购买</a>
 	</c:if>
 	<br/>
 	<table width="80%" border="1" align="center" style="text-align: center;">
		<tr>
			<td><img width="300px" height="200px" alt="" src="${pageContext.request.contextPath }/images/img_1.jpg" ></td>
			<td><img width="300px" height="200px" alt="" src="${pageContext.request.contextPath }/images/img_2.jpg" ></td>
			<td><img width="300px" height="200px" alt="" src="${pageContext.request.contextPath }/images/img_3.jpg" ></td>
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
            <address>&copy;  2017 江苏科技大学 版权所有 http://www.just.com 苏ICP备07020337号</address> 
          </div>
  </body>
</html>

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
    
    <title>首页体</title>
    
    <style>
    	#category{
  			height: 380px;
 		 	width: 200px;
  			border: 1px solid #EEE;
  			float: left;
			}

		.subtitle{ 
  			height: 37px;
  			background: gray url('${pageContext.request.contextPath }/images/index_main_top_left_corner.gif');
			}

.subtitle img{
  margin: 5px 0 0 10px;
  float: left;
}

.subtitle h1{
  font-size:16px;
  line-height: 32px;
  font-family:'微软雅黑'；
  margin:10px; 
  float: left;
}

.subtitle a{
  color: #888;
  font-size:12px;
  line-height: 20px;
  margin:10px;
  float: right;
}

.subtitle a:hover{
  color: #FF832C;
} 

.four{
  width: 180px;
  height: 400px;
  background: #EEE;
  margin: 10px;
  float: left;
}


.four ul{
  float: left;
  margin-left: 0px;
  margin-right:10px;
}

		a{
			color: #888;
		}
		
		a:HOVER{
			color: #FF832C;
		}

.four li{
 	
  padding-left:10px;
  padding-right:10px; 
  height: 29px;
}

.four h2{
  margin:6px 0 6px 10px;
  font-size:16px;
  line-height: 16px;
  font-family:'微软雅黑'；
  color:#888;
}
 
    	
    </style>

  </head>
  
  <body >
  	<div id="content" style="margin:0 auto;"><!-- 外边距上下为0，左右为自适应 -->
  		<div id="category" >
  			<div class="subtitle">
                <img src="${pageContext.request.contextPath }/images/circle.gif" alt="" />
                <h1>分类列表</h1>
                <a href='javascript:void(0)'>MORE&gt;&gt;</a>
              </div>
			<div class="four">
  			<ul>
  				<c:forEach var="category"  items="${categories }">
  				<li>
  					<a id="ctg" href="${pageContext.request.contextPath }/client/IndexServlet?method=listBookWithCategory&category_id=${category.id}">${category.name}</a>
  				</li>
  				</c:forEach>
  			</ul>           
  			</div>	
  			
  		</div>
  		<div>
  		 <div style="text-align: center;float: left;">
   	<form action="${pageContext.request.contextPath}/client/ListBookServlet" >
   		<input name="bookname" type="text" style="width: 646px;height: 30px;" />
   		<input type="submit" style="width: 100px;height: 30px;" value="搜索" >
   	</form>
   </div>
  		<div style="float:left;">
  			<img width="740px" height="400px" alt="" src="${pageContext.request.contextPath }/images/img_body.jpg" >
  		</div>
  		</div>
  		<div id="bookandpage" style="float:right; margin-left:30px;">
  			<div id="books">
  			
  				<c:forEach var="book" items="${page.list }">
  					<div id="book" style="height:150;" margin-top:20px>
  						<div id="image" style ="float:left;">
  							<img src="${pageContext.request.contextPath }/images/${book.image}" height=150 width=100/>
  						</div>
  						<div id="bookindo" style="float:left;text-align:left">
  							<ul>
  								<li>${book.name }</li>
  								<li>作者：${book.author }</li>
  								<li>售价：${book.price }</li>
  								<li>
  									<a href="${pageContext.request.contextPath }/client/BuyServlet?bookid=${book.id}">加入购物车</a>
  								</li>
  							</ul>
  						</div>
  						<div style="clear:both"></div><!-- 做div浮动后，为了不影响后续页面的显示，在这里清楚浮动效果 -->
  					</div>
  				 </c:forEach>
  			</div>
  			<div style="clear:both"></div><!-- 做div浮动后，为了不影响后续页面的显示，在这里清楚浮动效果 -->
  			<div id ="page" style="margin-top:20px;text-align:center;">
  				当前是[${page.pagenum}]页 &nbsp;&nbsp;
  				<c:forEach var="pagenum" begin="${page.startpage }" end="${page.endpage }">
  					[<a href="${pageContext.request.contextPath }/client/IndexServlet?method=${param.method}&pagenum=${pagenum}&category_id=${param.category_id}">${pagenum}</a>]
  				</c:forEach>&nbsp;&nbsp;
  				总共[${page.totalpage}]页，共[${page.totalrecord}]条记录
  			</div>
  		</div>
  	</div>
  
           
     
  
  
  </body>
</html>

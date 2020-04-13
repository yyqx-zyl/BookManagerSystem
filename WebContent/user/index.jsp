<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header">												
    		<div id="tool-bar">&nbsp;&nbsp; 欢迎光临智远图书网， [
    			<c:if test="${!empty user }" var="res">${userName }</c:if>
    			<c:if test="${!res }"><a href="${pageContext.request.contextPath}/user/user-login.jsp">请登录</a></c:if>]
    			&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/index.jsp">首页
    			</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/OrderBookController?op=showCar&userId=${userName }">我的购物车</a>
    			&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;</div>
    		<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
    	</div>
    	<form id="search-bar" action="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=1" method="post">
    		书名：<input type="text" class="txt" name="condition" />
    		<input id="search-btn" type="submit" value=" 搜索图书 " />
    	</form>
    	<div id="main">
    		<div class="section-left">
    			<div class="box-left">
    				<div class="box-title">分类畅销榜</div>
    				<div class="box-content">
						<p>·<a href="${pageContext.request.contextPath}/InfoBookController?op=findByid&id=0">全部</a></p>
    					<c:forEach items="${Clist }" var="cc">
    						<p>·<a href="${pageContext.request.contextPath}/InfoBookController?op=findByid&id=${cc.id }">${cc.category }</a></p>    	
    					</c:forEach>
    									
    				</div>
    			</div>
    		</div>
    		<div class="section-right">
    			<div class="box-right">
    				<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					<span class="fr"><a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=1">首页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=${pg.currPage-1 }">上一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=${pg.currPage+1 }">下一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=${pg.totalPages }">尾页</a>&nbsp;</span>
    					 共有图书${pg.totalCount }种，分${pg.totalPages }页显示，每页显示${pg.pageSize }个商品
    				</div>
    				
    				<c:forEach items="${pg.pageLists}" var="i">
    					<div class="box-item">				<!-- ${i.photo } -->
	    					<div class="img-box"><img src="${pageContext.request.contextPath}/static/photo/${i.photo}" /></div>
	    					<div class="info-box">
	    						<span style="font-size: 14px; "><a href="#">${i.bookName }</a></span><br />
								作者：${i.author }&nbsp;&nbsp;著<br />
								分类：${i.cate.category }<br />
								出版社：${i.publisher }<br />							
								售价：￥<span style="color: #990000;">${i.price}</span>	<br/>					
	    					</div>
	    					<a href="javascript:addcar(${i.id},${i.price})" class="btn-buy">购&nbsp;&nbsp;买</a>    					
	    					<div class="cf"></div>
    					</div> 
    				</c:forEach>
    			 				
    				<div class="paging">
    					 <span class="fr"><a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=1">首页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=${pg.currPage-1 }">上一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=${pg.currPage+1 }">下一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/InfoBookController?op=Index&pageIndex=${pg.totalPages }">尾页</a>&nbsp;</span>    					
    				</div>
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		function addcar(id,price){
			$.post("${pageContext.request.contextPath}/OrderBookController?op=orderAdd",
					{id:id,price:price},function(res){
						if (res.result=="true") {
							alert("增加成功！！！");
						}else {
							alert("增加失败！！！");
						}
			});
		}
	</script>
  </body>
</html>
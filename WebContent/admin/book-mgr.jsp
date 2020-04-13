<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<%@ include file="admintop.jsp" %>
    	<div id="main">
			<div class="section-left">    	
				<h2>图书信息列表</h2>		
				<table class="table" cellspacing="0" style="font-size: 12px;">
			    	<tr>
			    		<td class="header" width="100">书名</td>
			    		<td class="header" width="60">作者</td>
			    		<td class="header" width="60">类型</td>
			    		<td class="header" width="60">售价</td>
			    		<td class="header" width="60">操作</td>
			    	</tr>
			    	<c:forEach items="${lists }" var="ll">
						<tr>
				    		<td>${ll.bookName }</td>
				    		<td>${ll.author }</td>
				    		<td>${ll.cate.category }</td>
				    		<td>￥${ll.price }</td>
				    		<td><a href="${pageContext.request.contextPath}/InfoBookController?op=deleteBook&id=${ll.id}">删除</a>&nbsp;
				    			<a href="${pageContext.request.contextPath}/InfoBookController?op=findeBook&id=${ll.id}">编辑</a></td>
				    	</tr>	
					</c:forEach>
			    </table>
			</div>
			<div class="section-right">
				<h2>添加图书信息</h2>
				<form action="${pageContext.request.contextPath}/InfoBookController?op=AddBook" method="post" id="addBook">
					<p>图书书名：<input type="text" name="bookName"  /></p>
					<p>图书作者：<input type="text" name="author"  /></p>
					<p>图书分类：
						<select name="categoryid">		
									<c:forEach items="${Clist }" var="cc">		
										<option value="${cc.id }">${cc.category }</option>
									</c:forEach>
								</select>
					</p>
					<p>图书售价：<input type="text" name="price"  /></p>
					<p>图书出版社：<input type="text" name="publisher"  /></p>   
					<p>图书图片：<input type="file" name="photo"  /></p>    				 				
					<p><input type="submit" value=" 保 存 "  /></p>
				</form>
			</div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>

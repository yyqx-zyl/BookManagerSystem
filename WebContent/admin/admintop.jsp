<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div id="header"><h1>智远教育--图书网后台管理系统</h1></div>
    	<div id="info">
    		<c:if test="${!empty user }" var="res">${userName }</c:if>
    		&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/admin-login.jsp">登出</a>
    	</div>
    	<div class="menu">
    		<ul>
    			<li><a href="${pageContext.request.contextPath}/admin/admin-home.jsp">首页</a></li>
    			<li><a href="${pageContext.request.contextPath}/InfoBookController?op=category">图书分类管理</a></li><!--admin/category-mgr.jsp  -->
    			<li><a href="${pageContext.request.contextPath}/InfoBookController?op=book">图书管理</a></li>
    			<li><a href="#">购书订单管理</a></li>
    		</ul>
    	</div>
</body>
</html>
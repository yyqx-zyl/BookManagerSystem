<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
	<div id="tool-bar">&nbsp;&nbsp; 欢迎光临智远图书网，[
		<c:if test="${!empty bookUser }" var="res">${showUser }&nbsp;<a href="${pageContext.request.contextPath}/UserBookController?op=loginOut">退出</a></c:if>
		<c:if test="${!res }">[<a href="user-login.jsp">请登录</a>]&nbsp;[<a href="user-regist.jsp">免费注册</a>]&nbsp;&nbsp;&nbsp;</c:if>
	]&nbsp;&nbsp;&nbsp;<a href="index.jsp">首页</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/OrdersController?op=showCar">购物车</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="#">帮助</a></div>
	<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
</div>
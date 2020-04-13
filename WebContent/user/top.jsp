<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="header">												
    		<div id="tool-bar">&nbsp;&nbsp; 欢迎光临智远图书网，
    			 [<c:if test="${!empty user }" var="res">${userName }&nbsp;
    			<a href="${pageContext.request.contextPath}/UserBookController?op=loginOut">退出</a></c:if>
    			<c:if test="${!res }"><a href="${pageContext.request.contextPath}/user/user-login.jsp">请登录</a></c:if>]
    			&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/index.jsp">首页
    			</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath}/OrderBookController?op=showCar&userId=${userName }">我的购物车</a>
    			&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;</div>
    		<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
</div>
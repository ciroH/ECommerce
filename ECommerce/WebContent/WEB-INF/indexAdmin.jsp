<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/AdminStyle.css" rel="Stylesheet">
<% User admin = (User)session.getAttribute("user"); %>
<title><%= "Welcome, "+ admin.getName() %></title>
</head>
<body>
	<div class = "header">
		<img class="logo" alt="logo" src="${pageContext.request.contextPath}/ImageResources/logo-transparent.png">
	</div>
</body>
</html>
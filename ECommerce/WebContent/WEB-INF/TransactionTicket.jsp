<%@page import="entities.Transaction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket</title>
<% Transaction transaction = (Transaction)request.getAttribute("ticket"); %>
</head>
<body>

<%= transaction.getDetail() %>

</body>
</html>
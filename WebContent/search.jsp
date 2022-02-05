<%@page import="entities.Product"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>

<% LinkedList<Product> searchResult = (LinkedList<Product>)request.getAttribute("results");%>
</head>

<body>

	<% for(Product p : searchResult){ %>
<p>	<%= 	p.getName() %>
	<%	}	%>

</body>
</html>
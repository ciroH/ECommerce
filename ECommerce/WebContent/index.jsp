<%@page import="java.util.LinkedList"%>
<%@page import="data.DataProduct"%>
<%@page import="entities.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style/indexStyle.css" rel="Stylesheet">
<title>Welcome!</title>

<% DataProduct dp= new DataProduct();
   LinkedList<Product> products = dp.getAll();
    %>
   
</head>
<body>

	<form action="Search" method="get">
	<div>
	<input id="searchField" name="searchField" type="text" placeholder="Search that item that you need now!">
	<button id="searchButton" type="submit">⌕</button>
	</div>
	</form>
	
	<h1>Products:</h1>
	<% for(Product p : products){ %>
<p>	<%= 	p.getName() %>
	<%	}	%>
	

</body>
</html>
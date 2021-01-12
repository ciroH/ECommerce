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
<!-- find a way of fowarding to this same page,cleaning the attributes "search" and "category", but keeping the session attribute "user" (using redirect on the logo of the page would delete the session too)( this same thing must be done when searching product or clicking on a category) -->
<!-- maybe i can solve this using a new attribute on the request, "state" , that is null if i'm needing to see the clean index, "search" if i'm searching products or "category" if i clicked a category. before fowarding i change the value of the "state" attribute, and then, i use two if-else if-else (one in the <head> to load results, or categories, or the things on the clean index, and another in the <body> to show those products  -->
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
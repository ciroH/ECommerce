<%@page import="entities.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<%	Product productToAdd = (Product)request.getAttribute("productToAdd");
	int quantity = (int)request.getAttribute("quantity");	%>

</head>	<!--  talvez pueda directamente redirigir acá desde el index con un <a> tag, ya que el shoppingCart siempre va a estar cargado en la Session -->
<body>

</body>
</html>
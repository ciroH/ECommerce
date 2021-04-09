<%@page import="logic.LogicProduct"%>
<%@page import="java.util.HashMap"%>
<%@page import="entities.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<%  LogicProduct logic = new LogicProduct();
	HashMap<Integer,Integer> shoppingCart = new HashMap<>();
	HashMap<Product,Integer> cartToShow =  new HashMap<>(); //This is a Hashmap with K == Product and V == quantity, i didn't kept this into the Session because the Attributes of a Product stored on the DB can change trought a session and the user would end up with two copies of the same Product on the shoppingCart attached to his session.
	float price;
	shoppingCart.putAll((HashMap<Integer,Integer>)request.getSession().getAttribute("shoppingCart"));
	for(HashMap.Entry<Integer,Integer> product : shoppingCart.entrySet()){
		cartToShow.put(logic.idSearch(product.getKey()) , product.getValue());
	} %>
</head>	<!--  talvez pueda directamente redirigir acá desde el index con un <a> tag, ya que el shoppingCart siempre va a estar cargado en la Session -->
<body>

<div id="cartWrapper">
	<table id="cartTable">
	<thead>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Quantity</th>		
			<th>Price</th>
		</tr>		
	</thead>
	<tbody>
	<% for(HashMap.Entry<Product,Integer> product : cartToShow.entrySet()){  %>
		<% price = product.getValue() * product.getKey().getPrice(); %>
		<tr>
			<td>--image--</td>
			<td><%= product.getKey().getName() %></td>
			<td><%= product.getValue() %></td>
			<td><%= price %></td>
		</tr>
	<% } %>
	</tbody>
	</table>
</div>

</body>
</html>
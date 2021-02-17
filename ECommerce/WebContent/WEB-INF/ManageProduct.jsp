<%@page import="entities.Product"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/AdminStyle.css" rel="stylesheet">
<title>Insert title here</title>
<%LinkedList<Product> products = (LinkedList<Product>)request.getAttribute("productList");
   //me tengo que traer esta LinkedList denuevo cada vez que hago un forward a esta página %>
<!-- Reemplazar los request.getAttribute("trigger") por una variable -->
</head>
<body>

<!--  a la hora de añadir/borrar/modificar productos, guardar en una tabla aparte el id de quien lo hizo, junto con el id del producto, la fecha, y un field que sea "added","deleted", o "modified", esto se hace dentro de los servlets AddProduct, DeleteProduct, ModifyProduct (Método storeLog de la clase DataProduct) -->
	<!-- anted de checkear por cada trigger terngo que asegurar que el attribute de la request no esté en null -->	

<!--		<form action="/Menu" method="post">
		<input name="option" value="menu" hidden="1">
		<input type="image" src="${pageContext.request.contextPath}/ImageResources/logo-transparent.png">
		</form>
  -->		
		<%if((request.getAttribute("trigger").equals("add"))){ %>
			<form action="AddProduct" method="post">
				<input name="id" type="text" disabled>
				<input name="name" type="text" maxlength="50" required>
				<input name="description" type="text" maxlength="250">
				<input name="price" type="number" min="0" max="1000000" step=".01" required>
				<input name="oldPrice" type="text" disabled>
				<input name="category" type="text" maxlength="25" required>
				<input name="stock" type="number" min="0" max="5000000" step="1" required>
				<img id="addImage" alt="Add Image" src="${pageContext.request.contextPath}/ImageResources/add-image.png" >
				<input type="image" src="${pageContext.request.contextPath}/ImageResources/save.png">
						
			</form>
		<% } %>
		<% String errorTrigger = (String)request.getAttribute("trigger");
			if(errorTrigger.equals("errorAdd")){ %>
					<%= "An error has occurred. Check if the product doesn't exist already, and try later." %>
		<% }else if(errorTrigger.equals("errorDelete")){ %>
					<%= "An error has occurred while trying to connect to the DataBase, try again." %>
		<% } %>
<div>
	<table id="ProductTable">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>	<!-- Scrollable with Css-->
				<th>Price</th>
				<th>Old Price</th>
				<th>Category</th>
				<th>Stock</th>
				<% if(!(request.getAttribute("trigger").equals("delete"))){ %>
				<th>Images</th>			<!--  redirects to a servlet that shows the images in another jsp together with options to add or delete Images -->
				<% } %>
				<th>
				<form action="AddProduct" method="post">	
					<input type="image" src="${pageContext.request.contextPath}/ImageResources/add.png">
				</form>	
				<form action="DeleteProduct" method="post">
				<input type="image" src="${pageContext.request.contextPath}/ImageResources/delete.png" >
				</form>
				<img id="modify" alt="modify" src="${pageContext.request.contextPath}/ImageResources/modify.png" >
				</th>			
			</tr>	
		</thead>
		<tbody>

		<%for(Product product: products){ %>
			<tr>
			<td><%= product.getId() %></td>
			<td><%= product.getName() %></td>
			<td><%= product.getDescription() %></td>
			<td><%="$"+product.getPrice() %></td>
			<td><%="$"+product.getOldPrice() %></td>
			<td><%= product.getCategory() %></td>
			<td><%= product.getStock() %></td>
			<% if((request.getAttribute("trigger").equals("delete")) || (request.getAttribute("trigger").equals("errorDelete"))){  %>
				<td><form action="DeleteProduct" method="post">
					<input name="id" type="hidden" value="<%= product.getId() %>" >
					<input type="image" src="${pageContext.request.contextPath}/ImageResources/confirm-delete.png" >
		   		</form></td>
			<% }else { %>
			
				<td><img id="viewImage" alt="view" src="${pageContext.request.contextPath}/ImageResources/view.png" ></td>
			
			<% } %>
			</tr>
		<% } %>
		
		</tbody>
	
	
	</table>
	
</div>

</body>
</html>
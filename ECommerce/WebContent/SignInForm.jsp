<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In!</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<% String warning = (String)request.getAttribute("warning"); %>
</head>
<body>
	<form action="SignIn" method="post">
		<div>
			
			<input id="name" name="name" type="text" placeholder="Name" required="required">
			<br>
			<input id="inputEmail" name="inputEmail" type="email" placeholder="Email" required="required">	
			<br>
			<input id="inputPassword" name="inputPassword" type="password" placeholder="Password" required="required">
			<br>
			<input id="inputPasswordCheck" name="inputPasswordCheck" type="password" placeholder="Confirm Password" required="required">
			<br>
			<button type="submit">Sign In!</button>
		</div>
	</form>
	<% if(warning!=null){
		if(warning.equals("existing")){ %>
		<h1> That Email Address is already Registered!</h1>
	<% } else if(warning.equals("password")){  %>
		<h1> Verify Password! </h1>
	<% } 
	  } %>
	
</body>
</html>
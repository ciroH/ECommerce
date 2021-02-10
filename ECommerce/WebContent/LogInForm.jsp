<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style/indexStyle.css" rel="stylesheet">
<title>Log In!</title>
<% String warning = (String)request.getAttribute("warning"); %>
</head>

<body>
<h1>Please Login to continue</h1>
	<form action="LogIn" method="post">
		<div>
			<label>Email Address:</label> 
			<input id="inputEmail" name="inputEmail" type="text" placeholder="Email" required="required">
			<label>Password:</label>
			<input id="inputPassword" name="inputPassword" type="password" placeholder="Password" required="required">
			<button type="submit">Log In!</button>
		</div>
	</form>
	<% if(warning!=null){
		if(warning.equals("non-valid")){ %>
		<h1>The user name or password is not valid!</h1>
	<% 	 } 
	    } %>
</body>

</html>
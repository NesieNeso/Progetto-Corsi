<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\styles\Login.css">
<meta charset="ISO-8859-1">
<title>Pagina Homepage</title>
</head>
<body>
	<div id = "Login">
		<h1> Login </h1>
		<form method="post" action="redirector"> 
			<input type = "text"  placeholder="Name"> </br>
			<input type = "password" placeholder="Password"> </br>
			
			<input type = "submit" value="Login">
		</form>
		
		<input id = "bnt" type = "button" value="Registrati">
	</div>
	
	<script type="text/javascript">
    	document.getElementById("bnt").onclick = function () {
        location.href = "/login?registrazione=reg";
    };
</script>
</body>
</html>
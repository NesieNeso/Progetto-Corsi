<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Admin</title>
</head>
<body>
		<div style="color:blue;text-align:center">
			<h1 style="font-family:verdana; ;"> Pagina di controllo totale </h1> </br>
			 
			
			<form action="/Admin/HomeAdmin/allUsers" method="post">
				<input id = "1" type = "submit" value="Mostra Tutti">
			</form>
			<form action="/Admin/HomeAdmin/onlyUsers" method="post">
				<input id = "2" type = "submit" value="Mostra Users">
			</form>
			<form action="/Admin/HomeAdmin/onlyAdmin" method="post">
				<input id = "3" type = "submit" value="Mostra Admin">
			</form>
			
			
		</div>
		
</html>
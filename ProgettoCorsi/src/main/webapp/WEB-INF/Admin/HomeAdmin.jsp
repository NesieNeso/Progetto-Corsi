<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="\styles\Admin.css">
<meta charset="ISO-8859-1">
<title>Pagina Admin</title>
</head>
<body>
	<div id = centrer>
		<div id=centerCenter>
			<div id = "Admin">
			<h1 style="font-family:verdana; ;"> Pagina di controllo totale </h1> </br>
			 
			 	<form action="/Admin/HomeAdmin/clearResults" method="post">
					<input id = "0" type = "submit" value="ClearResult">
				</form>
				<form action="/Admin/HomeAdmin/allUsers" method="post">
					<input id = "1" type = "submit" value="Mostra Tutti">
				</form>
				<form action="/Admin/HomeAdmin/onlyUsers" method="post">
					<input id = "2" type = "submit" value="Mostra Users">
				</form>
				<form action="/Admin/HomeAdmin/onlyAdmin" method="post">
					<input id = "3" type = "submit" value="Mostra Admin">
				</form>
				<form action="/Admin/HomeAdmin/onlyBanned" method="post">
					<input id = "4" type = "submit" value="Mostra Bannati">
				</form>
				
				<form action="/Admin/HomeAdmin/banner" method="post">
					<input type="email" name="mailDaBannare" placeholder="mail utente da bannare">
					<input id = "10" type = "submit" value="Banna">
				</form>
				
				<form action="/Admin/HomeAdmin/sbanner" method="post">
					<input type="email" name="mailDaSbannare" placeholder="mail utente da sbannare">
					<input id = "11" type = "submit" value="Sbanna">
				</form>
			
				${risultato}
				
			</div>	
		</div>
	</div>	
</html>
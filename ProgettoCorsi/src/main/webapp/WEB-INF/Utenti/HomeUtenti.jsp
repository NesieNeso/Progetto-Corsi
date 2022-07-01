<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagina Utente</title>
</head>
<body>
		<div style="color:blue;text-align:center">
			<h1 style="font-family:verdana; ;"> Pagina di visualizzazione utente </h1> </br>
		</div>
		<div style="color:black;text-align:left">
			<h1 style="font-family:verdana;font-size:20px ;"> Username: ${username} </h1> </br>
			<h1 style="font-family:verdana;font-size:20px ;"> Email: ${email} </h1> </br>
			<h1 style="font-family:verdana;font-size:20px ;"> Corsi a cui sei iscritto: </h1>
		</div>
		<div style="color:black;text-align:center">
  		<h1 style="font-family:verdana;font-size:20px ;"> ${corsi} </h1> </br>
		</div>
		<div style="color:black;text-align:center">
			<input id = "bntIscr" type = "button" value="Iscriviti a un nuovo corso">
		</div>

</body>
</html>
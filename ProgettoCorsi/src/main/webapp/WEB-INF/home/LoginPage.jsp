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
	<div id = centrer>
		<div id=centerCenter>
			<div id = "Login">
				<h1> Ciao  </h1>
				<h3> Corsi  Italiani Assolutamente Ottimi</h3>
				<form method="post" action="redirector"> 
					<input id = "email" name="email" type = "email"  placeholder="email"> <br>
					<div class= "error"> ${errorEmail} </div><br>
					<input id = "pass" name="password" type = "password" placeholder="Password"> <br>
					<div class= "error"> ${errorPass} </div><br>
					
					<input id="log" name ="bnt" type = "submit" value="Login">
					<input id= "reg" name ="bnt" type = "submit" value="Registrati">
					<div class ="error"> ${errorLogin} </div>
				</form>
				
			</div>
		</div>
	</div>
	

</body>
</html>
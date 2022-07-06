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
	<div  style="background:${color};text-align:center;height:50px;width:100%">  </div>
	<div id = centrer>
		<div id=centerCenter>
			<div id = "Login">
				<h1> Gis</h1>
				<h3> ${testoTitolo}</h3>
				
				<form style="display:${visible}" method="post" action="login"> 
					<input id = "email" name="email" type = "email"  placeholder="${inputEmail}"> <br>
					<div class= "error"> ${errorEmail} </div><br>
					<input id = "pass" name="password" type = "password" placeholder="${inputPassword}"> <br>
					<div class= "error"> ${errorPass} </div><br>
					<input id="log" class="bntconfig" name ="bnt" type = "submit" value="${bntLogin}">
				</form>
			

				<form style="display:${invisible}"  method="post" action="registrazione">
					<input id = "email" name="email" type = "email"  placeholder="${inputEmail}"> <br>
					<div class= "error"> ${errorEmail} </div><br>
					<input id = "pass" name="password" type = "password" placeholder="${inputPassword}"> <br>
					<div class= "error"> ${errorPass} </div><br>
				
					<div style="display:${invisible}">
						<input id = "nome" name="nome" type = "text"  placeholder="${inputNome}"> <br>
						<div class= "error"> ${errorEmail} </div><br>
						<input id = "cognome" name="cognome" type = "text" placeholder="${inputCognome}"> <br>
						<div class= "error"> ${errorPass} </div><br>
					</div>
					
					
					<input id= "reg" class="bntconfig" name ="bnt" type = "submit" value="${bntRegist}">
					
				</form>

				<div class ="error"> ${errorLogin} </div>
				
				
				<div id="formBean">
					<div>
						<form class="bean" method="post" action="changeLing"> 
							<label> ${lingua} </label> <br>
							<input class="bntconfig" name ="bnt" type = "submit" value="${bntLingua}"> 
						 </form>
						
						<form class="bean" method="post" action="changeColor"> 
							<label> ${stile} </label> <br>
							<input class="bntconfig" name ="bnt" type = "submit" value="${bntColore}">  
						</form>
					</div>
					<form   method="post" action="strReg"> 
						<input style="${bntshadow}" class="bntconfig"  id= "StartRegLog" name ="bnt" type = "submit" value="${bntRegiNo}">
					</form>
				</div>
				  
			</div>
		</div>
	</div>
	

</body>
</html>
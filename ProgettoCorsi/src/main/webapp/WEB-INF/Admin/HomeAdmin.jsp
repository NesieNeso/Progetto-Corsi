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
			
			<input id = "1" type = "button" value="Mostra Tutti">
			<input id = "2" type = "button" value="Mostra Users">
			<input id = "3" type = "button" value="Mostra Admin">
		</div>
		
		<script type="text/javascript">
    	document.getElementById("1").onclick = function () {
    		location.href = "./Admin/HomeAdmin/allUsers";
    	};
    	</script>
    	<script type="text/javascript">
    	document.getElementById("2").onclick = function () {
        
    	};
    	</script>
    	<script type="text/javascript">
    	document.getElementById("3").onclick = function () {
        	
    	};
    	</script>
		
</html>
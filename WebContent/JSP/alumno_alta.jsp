<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Listado de alumnos UNPAZ</title>
	<meta charset="UTF-8">
	<meta name="description" content="Listado de alumnos de UNPAZ">
	<meta name="keywords" content="Alumnos,UNPAZ,Listado">
	<meta name="author" content="Practica Integradora Team">
</head>
<body>
	
	<h1>Alta de alumno</h1>
	
	<c:if test="${STATUS == 0}">
		<h3>Error al guardar!!</h3>
	</c:if>
	
	<form action="/UNPAZ/AlumnoAlta" method="post">
		<label for="">Legajo</label>
		<input type="number" min="1" max="9999" required name="legajo" placeholder="Solo números" >
		<br>
		<label for="">Nombre</label>
		<input type="text" name="nombre" required>
		<br>
		<label for="">Apellido</label>
		<input type="text" name="apellido" required>
		<br>
		<label for="">Email</label>
		<input type="email" name="email" required>
		<br>
		<label for="">DNI</label>
		<input type="number" name="dni" required>
		<br>
		<input type="submit" value="Enviar">
	</form>
	<a href="/UNPAZ/Alumnos">Volver</a>
	
</body>
</html>
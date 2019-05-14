<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!--Inicio CDN Boostrap-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!--Fin CDN Boostrap-->

<title>Listado de alumnos UNPAZ</title>
<meta charset="UTF-8">
<meta name="description" content="Listado de alumnos de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Listado">
<meta name="author" content="Practica Integradora Team">
</head>
<body>
	<div class="page-header"
		style="background-color: #107aa3; color: #107aa3">
		<h1>header</h1>
	</div>

	<div class="page-header" style="background-color: #f2f2f2">
		<a href="./"><img class="mr-3" src="./res/unpaz.png"
			alt="Logotipo Unpaz" style="width: 15%"></a>
	</div>

	<div class="container">
		<h1>Listado de alumnos</h1>

		<c:if test="${STATUS == 1}">
			<div class="alert alert-success" role="alert">La operación se
				ejecutó correctamente :)</div>

		</c:if>

		<a class="btn btn-primary" href="./AlumnoAlta">Nuevo alumno</a>
		<table class="table table-bordered table-striped table-dark">
			<tr>
				<th>Legajo</th>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Email</th>
				<th>Género</th>
				<th>Acciones</th>
			</tr>
			<c:forEach var="alumno" items="${ALUMNOS}">
				<tr>
					<td><c:out value="${alumno.getFile()}" /></td>
					<td><c:out value="${alumno.getDni()}" /></td>
					<td><c:out value="${alumno.getFirstname()}" /></td>
					<td><c:out value="${alumno.getLastname()}" /></td>
					<td><c:out value="${alumno.getEmail()}" /></td>
					<td><c:out value="${alumno.getGender()}" /></td>
					<td>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<a class="btn btn-primary"
								href="./AlumnoEditar?legajo=<c:out value="${alumno.getFile()}"/>"
								role="button">Editar</a> <a class="btn btn-primary"
								href="./AlumnoBaja?legajo=<c:out value="${alumno.getFile()}"/>"
								role="button">Eliminar</a>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="page-footer" style="background-color: #107aa3">
		<div class="row">
			<div class="col-sm-12">
				<p style="padding-left: 2%; padding-top: 1%">
					<strong>Maquetación Web: </strong>Lucas Frías
			</div>
		</div>
	</div>
</body>
</html>
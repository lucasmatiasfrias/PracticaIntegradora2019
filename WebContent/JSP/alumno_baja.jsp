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

<title>Eliminar Alumno de UNPAZ</title>
<meta charset="UTF-8">
<meta name="description" content="Alta de alumno de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Alta">
<meta name="author" content="Practica Integradora Team">
</head>
<body>
	<div class="page-header"
		style="background-color: #107aa3; color: #107aa3">
		<h1>header</h1>
	</div>

	<div class="page-header" style="background-color: #f2f2f2">
		<a href="/PracticaIntegradoraUnpaz2019/"><img class="mr-3" src="./res/unpaz.png"
			alt="Logotipo Unpaz" style="width: 15%"></a>
	</div>

	<div class="container">
		<h1>Eliminación de alumno</h1>

		<c:if test="${STATUS == 0}">
			<h3>
				Error al obtener Usuario
				<c:out value="${ALUMNO.getFile()}"></c:out>
				!
			</h3>
		</c:if>

		<form action="./AlumnoBaja" method="post">
			<div class="form-group">
				<label for="">Legajo</label> <input class="form-control"
					type="number" min="1" max="9999" readonly name="legajo"
					value="<c:out value="${ALUMNO.getFile()}"></c:out>">
			</div>
			<div class="form-group">
				<label for="">DNI</label> <input class="form-control" type="number"
					min="1" max="99999999" readonly name="dni"
					value="<c:out value="${ALUMNO.getDni()}" ></c:out>">
			</div>
			<div class="form-group">
				<label for="">Nombre</label> <input class="form-control" type="text"
					name="nombre" readonly
					value="<c:out value="${ALUMNO.getFirstname()}" ></c:out>">
			</div>
			<div class="form-group">
				<label for="">Apellido</label> <input class="form-control"
					type="text" name="apellido" readonly
					value="<c:out value="${ALUMNO.getLastname()}" ></c:out>">
			</div>
			<div class="form-group">
				<label for="">Email</label> <input class="form-control" type="email"
					name="email" readonly
					value="<c:out value="${ALUMNO.getEmail()}" ></c:out>">
			</div>
			<div class="form-group">
				<label for="">Género</label> <input class="form-control" type="text"
					name="genero" readonly
					value="<c:out value="${ALUMNO.getGender()}" ></c:out>">
			</div>
			<button type="submit" class="btn btn-primary">Eliminar</button>
		</form>
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
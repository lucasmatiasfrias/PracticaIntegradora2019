<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Listado de alumnos de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Listado">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Listado de alumnos UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Listado de alumnos</h1>

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
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
</body>
</html>
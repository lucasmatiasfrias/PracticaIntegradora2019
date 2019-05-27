<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Listado de alumnos de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Listado">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Listado de materias UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Listado de materias</h1>
		<a class="btn btn-primary" href="./MateriaAlta">Nueva Materia</a>
		<table class="table table-bordered table-striped table-dark">
			<tr>
				<th>Codigo de Materia</th>
				<th>Descripcion</th>
				<th>Acciones</th>
			</tr>
			<c:forEach var="materia" items="${MATERIAS}">
				<tr>
					<td><c:out value="${materia.getId()}" /></td>
					<td><c:out value="${materia.getDescription()}" /></td>
					<td>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<a class="btn btn-primary"
								href="./MateriaEditar?id=<c:out value="${materia.getId()}"/>"
								role="button">Editar</a> <a class="btn btn-primary"
								href="./MateriaBaja?id=<c:out value="${materia.getId()}"/>"
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Inscripciones a materias UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Inscripciones a materias</h1>
		<label for="">Seleccione Materia</label><select
			name="materiaSeleccionada" class="browser-default custom-select"
			onchange="reloadRegistrations(this.options[this.selectedIndex].value)"
			style="padding-buttom: 10%">
			<option value='0'>Todas</option>
			<c:forEach var="materia" items="${MATERIAS}">
				<option value='<c:out value="${materia.getId()}" ></c:out>'
					<c:if test="${materiaSeleccionada==materia.getId()}">
								<c:out value="selected"></c:out>
							</c:if>><c:out
						value="${materia.getDescription()}"></c:out></option>
			</c:forEach>
		</select>
		<table class="table table-bordered table-striped table-dark">
			<tr>
				<th>Materia</th>
				<th>Lejajo del Alumno</th>
				<th>DNI del Alumno</th>
				<th>Nombre del Alumno</th>
				<th>Apellido del Alumno</th>
			</tr>
			<c:forEach var="inscripcion" items="${INSCRIPCIONES}">
				<tr>
					<td><c:out value="${inscripcion.getSubject()}" /></td>
					<td><c:out value="${inscripcion.getStudentFile()}" /></td>
					<td><c:out value="${inscripcion.getStudentDNI()}" /></td>
					<td><c:out value="${inscripcion.getStudentFirstname()}" /></td>
					<td><c:out value="${inscripcion.getStudentLastname()}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
	<script type="text/javascript">
		function reloadRegistrations(idMateria) {
			window.location.href = "./Inscripciones?materiaSeleccionada="
					+ idMateria;
		}
	</script>
</body>
</html>
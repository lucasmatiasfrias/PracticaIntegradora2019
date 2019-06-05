<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
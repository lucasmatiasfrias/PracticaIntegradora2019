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
		<h1>Inscripciones</h1>
		<label for="">Seleccione Materia</label><select
			name="materiaSeleccionada" class="browser-default custom-select"
			onchange="loadRegistrations(this.options[this.selectedIndex].value)"
			style="padding-buttom: 10%" id="subjectsComboBox">
			<option value='0'>Todas</option>
			<c:forEach var="materia" items="${MATERIAS}">
				<option value='<c:out value="${materia.getId()}" ></c:out>'
					<c:if test="${materiaSeleccionada==materia.getId()}">
								<c:out value="selected"></c:out>
							</c:if>><c:out
						value="${materia.getDescription()}"></c:out></option>
			</c:forEach>
		</select>
		<div id="subjectsTable"></div>
	</div>
	<br>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
	<script type="text/javascript" src="JS/loadRegistrations.js" charset="UTF-8"></script>
</body>
</html>
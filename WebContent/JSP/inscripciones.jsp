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
		<c:if test="${materiaSeleccionada > 0 }">
			<a class="btn btn-primary"
				href="./InscripcionAlta?materia=<c:out value="${materiaSeleccionada}"/>">Inscribir
				alumno</a>
		</c:if>
	</div>
	<br>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
	<script type="text/javascript">
		window.onload = function() {
			loadRegistrations(document.getElementById("subjectsComboBox").value);
		};

		function loadRegistrations(idMateria) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var myArr = JSON.parse(this.responseText);
					var tableDiv = document.getElementById("subjectsTable");
					tableDiv.innerHTML = "";
					createTable(myArr, tableDiv);
				}
			};
			xhttp.open("POST", "./Inscripciones?materiaSeleccionada="
					+ idMateria, true);
			xhttp.send();
		}

		function createTable(json, tableDiv) {
			var table = '<table class="table table-bordered table-striped table-dark"><tr><th>Materia</th><th>Lejajo del Alumno</th><th>DNI del Alumno</th><th>Nombre del Alumno</th><th>Apellido del Alumno</th><th>Email del Alumno</th></tr>';
			for (var i = 0; i < json.length; i++) {
				table += "<tr>";
				table += "<td>" + json[i].subject.description + "</td>";
				table += "<td>" + json[i].user.file + "</td>";
				table += "<td>" + json[i].user.dni + "</td>";
				table += "<td>" + json[i].user.firstname + "</td>";
				table += "<td>" + json[i].user.lastname + "</td>";
				table += "<td>" + json[i].user.email + "</td>";
				table += "</tr>";
			}
			tableDiv.innerHTML = table;
		}
	</script>
</body>
</html>
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
		<c:if test="${RESULTADO_ABM!=null}">
			<div class="alert alert-info" role="alert">
				<h4>
					<c:out value="${RESULTADO_ABM.getResultMsg()}"></c:out>
				</h4>
			</div>
		</c:if>
		<h1>Listado de alumnos</h1>

		<a class="btn btn-primary" href="./AlumnoAlta">Nuevo alumno</a>
		<div id="usersTable"></div>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
	<script type="text/javascript">
		window.onload = function() {
			loadUsers();
		};

		function loadUsers() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var myArr = JSON.parse(this.responseText);
					var tableDiv = document.getElementById("usersTable");
					tableDiv.innerHTML = "";
					createTable(myArr, tableDiv);
				}
			};
			xhttp.open("POST", "./Alumnos", true);
			xhttp.send();
		}

		function createTable(json, tableDiv) {
			var table = '<table class="table table-bordered table-striped table-dark"><tr><th>Legajo</th><th>DNI</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Género</th><th>Acciones</th></tr>';
			for (var i = 0; i < json.length; i++) {
				table += "<tr>";
				table += "<td>" + json[i].file + "</td>";
				table += "<td>" + json[i].dni + "</td>";
				table += "<td>" + json[i].firstname + "</td>";
				table += "<td>" + json[i].lastname + "</td>";
				table += "<td>" + json[i].email + "</td>";
				table += "<td>" + json[i].gender + "</td>";
				table += "<td><div class='btn-group btn-group-toggle' data-toggle='buttons'><a class='btn btn-primary' href='./AlumnoEditar?legajo="
						+ json[i].file
						+ "' role='button'>Editar</a> <a class='btn btn-primary' href='./AlumnoBaja?legajo="
						+ json[i].file
						+ "' role='button'>Eliminar</a>"
						+ "<a class='btn btn-primary'	href='./InscripcionAlta?legajoAlumno="
						+ json[i].file
						+ "' role='button'>Inscribir a Materia</a></div></td>";
				table += "</tr>";
			}
			tableDiv.innerHTML = table;
		}
	</script>
</body>
</html>
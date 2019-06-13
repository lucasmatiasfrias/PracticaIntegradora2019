<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description"
	content="Inscripción de Alumno en Materias UNPAZ">
<meta name="keywords" content="Inscripcion,UNPAZ,Alta">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Inscripción de Alumno en Materias UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Inscripción a Materia</h1>
		<hr>
		<h3>Alumno</h3>
		<table class="table table-bordered table-dark">
			<thead>
				<tr>
					<th scope="col">Legajo</th>
					<th scope="col">DNI</th>
					<th scope="col">Nombre y Apellido</th>
					<th scope="col">Email</th>
				</tr>
			</thead>
			<tbody>
				<tr id="alumnoRow">
					<td>${ALUMNO.getFile()}</td>
					<td>${ALUMNO.getDni()}</td>
					<td>${ALUMNO.getFullname()}</td>
					<td>${ALUMNO.getEmail()}</td>
				</tr>
			</tbody>
		</table>
		<hr>
		<h3>Materias a inscribir</h3>
		<div class="input-group mb-3">
			<select name="materiaSeleccionada"
				class="browser-default custom-select" style="padding-buttom: 10%"
				id="subjectsComboBox">
				<c:forEach var="materia" items="${MATERIAS}">
					<option value='${materia.getId()}'>${materia.getDescription()}</option>
				</c:forEach>
			</select>
			<div class="input-group-append">
				<button class="btn btn-outline-info" type="button"
					onclick="addCurrentSubject()">Agregar</button>
			</div>
		</div>
		<table class="table table-bordered table-dark">
			<thead>
				<tr>
					<th scope="col">Código</th>
					<th scope="col">Descripción</th>
				</tr>
			</thead>
			<tbody id="materiasBody">
			</tbody>
		</table>
		<button class="btn btn-primary" style="margin-top: 1%"
			onclick="enrol(${ALUMNO.getFile()})">Confirmar Inscripción</button>
	</div>
	<br>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
	<script type="text/javascript">
	function addCurrentSubject(){
		var subject=document.getElementById("subjectsComboBox");
		if(document.getElementById(subject.options[subject.selectedIndex].value)==null){
			var subjectsTableBody=document.getElementById("materiasBody");
			var newTr = document.createElement("tr");
			newTr.id=subject.options[subject.selectedIndex].value;
			var ThCodMateria= document.createElement("td");
			var ThDescMateria=document.createElement("td");
			ThCodMateria.appendChild(document.createTextNode(subject.options[subject.selectedIndex].value));
			ThDescMateria.appendChild(document.createTextNode(subject.options[subject.selectedIndex].innerHTML));
			newTr.appendChild(ThCodMateria);
			newTr.appendChild(ThDescMateria);
			subjectsTableBody.appendChild(newTr);	
		}else
			alert("Solo debe agregar la materia una vez!");
	}
	
	function enrol(studentFile){
		var json=JSON.stringify(new Registration(studentFile, getSubjectsIds()));
		var xhttp = new XMLHttpRequest();
		xhttp.open("POST", "./InscripcionPorAlumnoAlta?json=" + json, true);
		xhttp.send();
		
	}
	
	function getSubjectsIds(){
		var subjectsDOM=document.getElementById("materiasBody").getElementsByTagName("tr");
		var subjectsIds=[];
		for(var i=0; i<subjectsDOM.length;i++){
			subjectsIds[i]=subjectsDOM[i].getAttribute("id");
		}
		return subjectsIds;
	}
	
	class Registration{
		constructor(studentFile, subjectsIds) {
		    this.studentFile = studentFile;
		    this.subjectsIds = subjectsIds;
		  }
	}
	</script>
</body>
</html>
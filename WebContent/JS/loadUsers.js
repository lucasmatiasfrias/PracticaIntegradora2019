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
				+ "' role='button'>Inscribir a Materias</a></div></td>";
		table += "</tr>";
	}
	tableDiv.innerHTML = table + "</table>";
}
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
	xhttp
			.open("POST", "./Inscripciones?materiaSeleccionada=" + idMateria,
					true);
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
	tableDiv.innerHTML = table + "</table>";
}
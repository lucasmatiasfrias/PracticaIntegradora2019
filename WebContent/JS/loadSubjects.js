window.onload = function() {
	loadSubjects();
};

function loadSubjects() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myArr = JSON.parse(this.responseText);
			var tableDiv = document.getElementById("subjectsTable");
			tableDiv.innerHTML = "";
			createTable(myArr, tableDiv);
		}
	};
	xhttp.open("POST", "./Materias", true);
	xhttp.send();
}

function createTable(json, tableDiv) {
	var table = '<table class="table table-bordered table-striped table-dark"><tr><th>Código de Materia</th><th>Descripción</th><th>Acciones</th></tr>';
	for (var i = 0; i < json.length; i++) {
		table += "<tr>";
		table += "<td>" + json[i].id + "</td>";
		table += "<td>" + json[i].description + "</td>";
		table += "<td><div class='btn-group btn-group-toggle' data-toggle='buttons'><a class='btn btn-primary' href='./MateriaEditar?id="
				+ json[i].id
				+ "' role='button'>Editar</a> <a class='btn btn-primary' href='./MateriaBaja?id="
				+ json[i].id + "' role='button'>Eliminar</a><a class='btn btn-primary' href='./InscripcionPorMateriaAlta?idMateria="
				+ json[i].id + "' role='button'>Inscribir Alumnos</a></div></td>";
		table += "</tr>";
	}
	tableDiv.innerHTML = table + "</table>";
}
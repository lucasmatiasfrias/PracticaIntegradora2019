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
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var res=this.responseText.split(";");
				alert(res[0])
				window.location.href=res[1];
			}
		};
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
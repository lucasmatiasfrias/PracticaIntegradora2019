<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Alta de alumno de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Alta">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Baja Alumno de UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Baja de alumno</h1>

		<form action="./AlumnoBaja" method="post">
			<div class="form-group">
				<label for="">Legajo</label> <input class="form-control"
					type="number" min="1" max="9999" readonly name="legajo"
					value='<c:out value="${ALUMNO.getFile()}"></c:out>'>
			</div>
			<div class="form-group">
				<label for="">DNI</label> <input class="form-control" type="number"
					min="1" max="99999999" readonly name="dni"
					value='<c:out value="${ALUMNO.getDni()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Nombre</label> <input class="form-control" type="text"
					name="nombre" readonly
					value='<c:out value="${ALUMNO.getFirstname()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Apellido</label> <input class="form-control"
					type="text" name="apellido" readonly
					value='<c:out value="${ALUMNO.getLastname()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Email</label> <input class="form-control" type="email"
					name="email" readonly
					value="<c:out value="${ALUMNO.getEmail()}" ></c:out>">
			</div>
			<div class="form-group">
				<label for="">Género</label> <input class="form-control" type="text"
					name="genero" readonly
					value='<c:out value="${ALUMNO.getGender()}" ></c:out>'>
			</div>
			<button type="submit" class="btn btn-primary">Eliminar</button>
		</form>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
</body>
</html>
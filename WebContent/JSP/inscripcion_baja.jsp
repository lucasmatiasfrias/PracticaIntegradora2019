<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Baja de alumno de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Baja">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Baja de Inscripción UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>¿Eliminar inscripción?</h1>

		<form action="./InscripcionBaja?id=${ID_INSCRIPCION}" method="post">
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<h3>Alumno</h3>
						<div class="form-group">
							<label for="">Legajo</label> <input class="form-control"
								type="number" min="1" max="9999" readonly name="legajo"
								value='<c:out value="${ALUMNO.getFile()}"></c:out>'>
						</div>
						<div class="form-group">
							<label for="">DNI</label> <input class="form-control"
								type="number" min="1" max="99999999" readonly name="dni"
								value='<c:out value="${ALUMNO.getDni()}" ></c:out>'>
						</div>
						<div class="form-group">
							<label for="">Nombre</label> <input class="form-control"
								type="text" name="nombre" readonly
								value='<c:out value="${ALUMNO.getFirstname()}" ></c:out>'>
						</div>
						<div class="form-group">
							<label for="">Apellido</label> <input class="form-control"
								type="text" name="apellido" readonly
								value='<c:out value="${ALUMNO.getLastname()}" ></c:out>'>
						</div>
						<div class="form-group">
							<label for="">Email</label> <input class="form-control"
								type="email" name="email" readonly
								value="<c:out value="${ALUMNO.getEmail()}" ></c:out>">
						</div>
						<div class="form-group">
							<label for="">Género</label> <input class="form-control"
								type="text" name="genero" readonly
								value='<c:out value="${ALUMNO.getGender()}" ></c:out>'>
						</div>
					</div>
					<div class="col-sm-6">
						<h3>Materia</h3>
						<div class="form-group">
							<label for="">Código</label> <input class="form-control"
								type="number" min="1" max="9999999" required name="code"
								readonly value='<c:out value="${MATERIA.getId()}"></c:out>'>
						</div>
						<div class="form-group">
							<label for="">Descripción</label> <input class="form-control"
								type="text" required name="description" readonly
								value='<c:out value="${MATERIA.getDescription()}"></c:out>'>
						</div>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary" style="margin: 1%">Eliminar</button>
		</form>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Modificación de alumno de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Modificación">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Modificación de Alumno de UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Modificación de alumno</h1>

		<form action="./AlumnoEditar" method="post">
			<div class="form-group">
				<label for="">Legajo</label> <input class="form-control"
					type="number" min="1" max="9999" readonly name="legajo"
					value='<c:out value="${ALUMNO.getFile()}"></c:out>'>
			</div>
			<div class="form-group">
				<label for="">DNI</label> <input class="form-control" type="number"
					min="1" max="99999999" required name="dni"
					value='<c:out value="${ALUMNO.getDni()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Nombre</label> <input class="form-control" type="text"
					name="nombre" required
					value='<c:out value="${ALUMNO.getFirstname()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Apellido</label> <input class="form-control"
					type="text" name="apellido" required
					value='<c:out value="${ALUMNO.getLastname()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Email</label> <input class="form-control" type="email"
					name="email" required
					value='<c:out value="${ALUMNO.getEmail()}" ></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Género</label><select name="genero"
					class="browser-default custom-select">
					<c:forEach var="genero" items="${GENEROS}">
						<option value='<c:out value="${genero}"></c:out>'
							<c:if test="${genero.equals(ALUMNO.getGender())}">
								<c:out value="selected"></c:out>
							</c:if>><c:out
								value="${genero}"></c:out></option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Actualizar</button>
		</form>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
</body>
</html>
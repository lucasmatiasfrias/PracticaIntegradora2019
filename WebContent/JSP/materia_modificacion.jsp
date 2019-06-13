<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Modificación de materia de UNPAZ">
<meta name="keywords" content="Materias,UNPAZ,Modificación">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Modificación de Materia UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Modificación de materia</h1>
		<form action="./MateriaEditar" method="post">
			<div class="form-group">
				<label for="">Código</label> <input class="form-control"
					type="number" min="1" max="9999999" readonly required name="code"
					value='<c:out value="${MATERIA.getId()}"></c:out>'>
			</div>
			<div class="form-group">
				<label for="">Descripción</label> <input class="form-control"
					type="text" required name="description"
					value='<c:out value="${MATERIA.getDescription()}"></c:out>'>
			</div>
			<button type="submit" class="btn btn-primary">Actualizar</button>
		</form>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
</body>
</html>
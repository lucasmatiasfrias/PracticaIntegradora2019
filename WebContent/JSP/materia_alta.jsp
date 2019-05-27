<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Alta de alumno de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Alta">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Alta de Materia UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<h1>Alta de materia</h1>
		<form action="./MateriaAlta" method="post">
			<div class="form-group">
				<label for="">Código</label> <input class="form-control"
					type="number" min="1" max="9999999" required name="code"
					placeholder="Solo números">
			</div>
			<div class="form-group">
				<label for="">Descripción</label> <input class="form-control"
					type="text" required name="description">
			</div>
			<button type="submit" class="btn btn-primary">Guardar</button>
		</form>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
</body>
</html>
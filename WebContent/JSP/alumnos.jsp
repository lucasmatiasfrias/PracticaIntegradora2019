<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Listado de alumnos de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Listado">
<jsp:include page="static/metaData.jsp" />
<jsp:include page="static/boostrapCDN_CSS.jsp" />
<title>Listado de alumnos UNPAZ</title>
</head>
<body>
	<jsp:include page="static/header.jsp" />
	<jsp:include page="static/navigation.jsp" />
	<div class="container">
		<jsp:include page="static/resultadoABM.jsp" />
		<h1>Listado de alumnos</h1>

		<a class="btn btn-primary" href="./AlumnoAlta">Nuevo alumno</a>
		<div id="usersTable"></div>
	</div>
	<jsp:include page="static/footer.jsp" />
	<jsp:include page="static/boostrapCDN_JS.jsp" />
	<script type="text/javascript" src="JS/loadUsers.js" charset="UTF-8"></script>
</body>
</html>
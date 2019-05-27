<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="description" content="Alta de alumno de UNPAZ">
<meta name="keywords" content="Alumnos,UNPAZ,Alta">
<jsp:include page="JSP/static/metaData.jsp" />
<jsp:include page="JSP/static/boostrapCDN_CSS.jsp" />
<title>Sistema de Alumnos UNPAZ</title>
</head>
<body>
	<jsp:include page="JSP/static/header.jsp" />
	<jsp:include page="JSP/static/navigation.jsp" />
	<div class="jumbotron">
		<h1 class="display-4">Bienvenidos al Sistema Único de Alumnos de
			UNPAZ!</h1>
		<p class="lead">La intención es crear un clásico CRUD de alumnos,
			materias e inscripciones integrando: Java EE, MVC, Persistencia en
			Base de Datos y Tecnologías web Frontend</p>
		<hr class="my-4">
		<div class="row">
			<div class="col-sm-6">
				<div class="card" style="width: 70%;">
					<img src="./res/unpaz_portada.png" class="card-img-top"
						alt="portada">
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="JSP/static/footer.jsp" />
	<jsp:include page="JSP/static/boostrapCDN_JS.jsp" />
</body>
</html>
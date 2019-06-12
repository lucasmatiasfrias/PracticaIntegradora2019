<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${RESULTADO_ABM!=null}">
	<div class="alert alert-info" role="alert">
		<h4>
			<c:out value="${RESULTADO_ABM.getResultMsg()}"></c:out>
		</h4>
	</div>
</c:if>
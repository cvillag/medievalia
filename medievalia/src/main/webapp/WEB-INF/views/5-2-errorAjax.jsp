<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
	<%	if(request.getAttribute("mensaje2") != null){%>
	<div class="alert alert-danger">
		<fmt:message key="${mensaje2}" />
	</div>
	<%} %>
	<input type="button" class="btn btn-default" onclick="location.href='main.do'" value='<fmt:message key="general.inicioSesion"/>' />

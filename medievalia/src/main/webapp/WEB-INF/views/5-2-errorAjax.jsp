<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<div class="container theme-showcase" role="main">
		<%	if(request.getAttribute("mensaje2") != null){%>
		<div class="alert alert-danger"><fmt:message key="${mensaje2}"/></div>
		<%} %>
		<input type="button" onclick="location.href='main.do'" value="Aceptar"/>
</div>
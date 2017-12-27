<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<% User usr = (User)request.getAttribute("usuario"); %>
<div class="container">
	<div class="jumbotron">
		<h3>
			<fmt:message key="p4-0.bienvenida" />&nbsp;<%=usr.getUser_long_name()%>
		</h3>
	</div>
	<p class="text-justify"><fmt:message key="intro.general.txt1"></fmt:message></p>
	<% if(usr.getUser_role() == Constants.ROLE_PROFESOR){ %>
	<p class="text-justify"><fmt:message key="intro.general.txt2"></fmt:message></p>
	<p class="text-justify"><fmt:message key="intro.general.txt3"></fmt:message></p>
	<%} else if(usr.getUser_role() == Constants.ROLE_ALUMNO){%>
	<p class="text-justify"><fmt:message key="intro.general.txt4"></fmt:message></p>
	<%} %>
	<p class="text-justify"><fmt:message key="intro.general.txt5"></fmt:message></p>
</div>
<%@page import="com.cvilla.medievalia.domain.User"%>
<% User usr = (User)request.getAttribute("usuario"); %>
<div class="container">
	<div class="jumbotron">
		<h1>
			<fmt:message key="p4-0.bienvenida" />
		</h1>
		<p>
			<%=usr.getUser_long_name()%>
		</p>
	</div>
</div>
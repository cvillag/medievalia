<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
User u = (User)request.getAttribute("usuario");
%>
<div class="container">
	<div class="starter-template">
		<%if (u != null){ %>
		<p>Id Usuario: <%= u.getId()%></p>
		<p>Nombre del usuario: <%= u.getUser_long_name()%></p>
		<p>Login de usuario: <%= u.getUser_name() %></p>
		<p>Rol de usuario: <%= u.getUser_role()%></p>
		<%} %>
		<input type="button" onclick="location.href='inicio.do'" value="volver"/>
	</div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%
User u = (User)request.getAttribute("usuario");
%>
<div class="container">
	<div class="starter-template">
		<%if (u != null){ %>
		<p>
			Id Usuario:
			<%= u.getId()%></p>
		<p>
			Nombre del usuario:
			<%= u.getUser_long_name()%></p>
		<p>
			Login de usuario:
			<%= u.getUser_name() %></p>
		<p>
			Rol de usuario:
			<%= u.getUser_role()%></p>
		<%} %>
		<input type="button" onclick="location.href='main.do'" value="Aceptar" />
	</div>
</div>

<%if(u.getUser_role() == Constants.ROLE_ADMIN || u.getUser_role() == Constants.ROLE_PROFESOR){ %>
<div class="container">
	<legend>
		<h3>
			<span id="displayGroup1" class="glyphicon glyphicon-chevron-down"></span>
			<fmt:message key="p1-3.2.gruposDir" />
		</h3>
	</legend>
</div>
<div id="group-block1" class="container">
	<p>Lorem ipsum</p>
</div>
<div class="container">
	<legend>
		<h3>
			<span id="displayGroup2" class="glyphicon glyphicon-chevron-down"></span>
			<fmt:message key="p1-3.2.gruposProf" />
		</h3>
	</legend>
</div>
<div id="group-block2" class="container">
	<p>Lorem ipsum</p>
</div>
<%} %>
<div class="container">
	<legend>
		<h3>
			<span id="displayGroup3" class="glyphicon glyphicon-chevron-down"></span>
			<fmt:message key="p1-3.2.gruposAlum" />
		</h3>
	</legend>
</div>
<div id="group-block3" class="container">
	<p>Lorem ipsum</p>
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
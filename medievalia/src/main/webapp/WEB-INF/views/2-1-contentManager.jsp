<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Tema"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<% 
	String message = (String)request.getAttribute("message");
	HttpSession s = request.getSession();
	Group g = (Group) ses.getAttribute("grupoActual");
	@SuppressWarnings(value="unchecked")
	List<Tema> listaTemas = (List<Tema>) request.getAttribute("listaTemas");
	User director = (User)request.getAttribute("director");
%>
<%if(message.equals("p3.1.msg.ok") && g != null){ %>
<div class="container">
	<div class="row">
		<div class="panel panel-default col-lg-4">
			
			<div class="panel-heading">
				<h3 class="panel-title"><%= g.getName()%></h3>
			</div>
			<div class="panel-body">
				<p><fmt:message key="p2-1.director"></fmt:message>&nbsp;<%=director.getUser_long_name() %></p>
				<p><%=g.getDescription() %></p>
			</div>
		</div>
		<div class="panel panel-default col-lg-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.numTemas"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<p><%=listaTemas.size() %></p>
				<p><a href="topicManager.do"><button type="button" class="btn btn-default" id="btnListaTemas"><fmt:message key="p2-1.btnListaTemas"></fmt:message></button></a></p>
			</div>
		</div>
		<div class="panel panel-default col-lg-4">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="p2-1.participantes"></fmt:message></h3>
			</div>
			<div class="panel-body">
				<!-- TODO: Insertar lista de profesores y número de alumnos -->
				<p>Profe, profe2, profe3</p>
				<p><fmt:message key="p2-1.numAlumnos"></fmt:message>&nbsp;5</p>
				<p><button type="button" class="btn btn-default" id="btnMatricularAlumnos"><fmt:message key="p2-1.listaUsuarios.btn"></fmt:message></button></p>
			</div>
		</div>
	</div>
</div>
<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<div class="panel panel-danger">
		<div class="panel-heading">
			<p><fmt:message key="p3.1.msg.grpNoExiste"></fmt:message></p>
		</div>
	</div>	
<%} %>


<%@ include file="/WEB-INF/views/footer.jsp"%>
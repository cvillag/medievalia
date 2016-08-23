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
<div class="container">
	<%if(message.equals("p3.1.msg.ok") && g != null){ %>
	<h2>
		<%= g.getName()%> 
	</h2>
	<label><fmt:message key="p2-1.director"></fmt:message>&nbsp;<%=director.getUser_long_name() %></label>
	<p><%=g.getDescription() %></p>
	<%} else if (message.equals("p3.1.msg.grpNoExiste")){%>
	<p>
		<fmt:message key="p3.1.msg.grpNoExiste"></fmt:message>
	</p>
	<%} %>
</div>
<div class="container">
	<label><fmt:message key="p2-1.numTemas"></fmt:message>&nbsp;<%=listaTemas.size() %></label>
</div>
<div class="container">
	<label><fmt:message key="p2-1.listaProfesores"></fmt:message></label>
</div>
<div class="container">
	<label><fmt:message key="p2-1.numAlumnos"></fmt:message></label>
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
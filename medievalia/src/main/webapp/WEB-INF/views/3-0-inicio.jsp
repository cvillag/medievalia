<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
User u = (User)request.getAttribute("usuario");
%>
<div class="container">
	<div class="jumbotron">
		<h1><fmt:message key="p3-0.bienvenida"/></h1>
		<p><fmt:message key="p3-0.mensaje"/></p>
	</div>
	<div class="starter-template">
		<%if (u != null){ %>
		<p>Usuario: <%= u.getId() + "," + u.getUser_long_name() + "," + u.getUser_name() + "," + u.getUser_pass() + "," + u.getUser_role()%></p>
		<%} %>
		<input type="button" onclick="location.href='hello.do'" value="volver"/>
		<p><fmt:message key="${mensaje}"/>,<%=u.getUser_long_name()  %></p>
	</div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
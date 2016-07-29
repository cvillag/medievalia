<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%
User u = (User)request.getAttribute("usuario");
%>
<div class="container">
	<div class="jumbotron">
		<h1><fmt:message key="p1-1.bienvenida"/></h1>
		<p><%=u.getUser_long_name() %></p>
	</div>
</div>
<input type="hidden" name="idUser" id="idUser" value="<%=u.getId() %>"/>
<%@ include file="/WEB-INF/views/footer.jsp"%>
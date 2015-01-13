<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<div class="container theme-showcase" role="main">
	<div class="jumbotron">
		<h1><fmt:message key="bienvenida"/></h1>
		<p><%= request.getAttribute("mje") %></p>
	</div>
	<form class="form-horizontal" role="form" method="post" action="addCosa.do">
		<div class="form-group">
			<label class="col-xs-3 control-label" for="nombre"><fmt:message key="intro.nombre"/></label>
			<div class="col-xs-4">
				<input class="form-control" type="text" id="nombre" name="nombre"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label" for="pass"><fmt:message key="intro.pass"/></label>
			<div class="col-xs-4">
				<input class="form-control" type="password" id="pass" name="pass"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-xs-offset-3 col-xs-2">
				<button type="submit" class="btn btn-default"><fmt:message key="intro.btn1"/></button>
			</div>
		</div>
	</form>
	<hr>
	<% List<User> list = (List<User>) request.getAttribute("users"); %>
	<h3>Usuarios</h3>
	<% for( User us : list){%>
		<p><%= us.getUser_name() %></p>
	<%} %>
</div>
<%@ include file="/WEB-INF/views/footer.jsp" %>
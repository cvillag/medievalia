<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Charge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%	
User usr = (User)ses.getAttribute("user");
@SuppressWarnings(value="unchecked")
List<Charge> cargos =  (List<Charge>) request.getAttribute("listaCargos"); %>
<form id=formUser">
	<input type="hidden" id="userrole" value="<%=usr.getUser_role()%>">
</form>
<div class="container">
	<div class="row">
		<h1>
			<fmt:message key="p2.2.cargos.titulo"></fmt:message>
		</h1>
	</div>
	<div class="row">
		<div class="panel panel-default col-lg-12">
			<div class="panel-heading">
				<button id="displayCreate" class="btn btn-default btn-xs">
					<span id="displayCreatei" class="glyphicon glyphicon-chevron-down"></span>
				</button>
				&nbsp;
				<fmt:message key="p2-2.crear" />
			</div>	
			<div id="group-block1" class="panel-body">
				<p><fmt:message key="p2.2.cargos.nombre"></fmt:message><input type="text" id="newChargeName">
				<button type="button" class="btn btn-default" id="createButton">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				<button type="button" class="btn btn-default" id="cancelButton">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button></p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel panel-default col-lg-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.cargos.todos"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaCompleta" type="text"></form></p>
				<table class="table table-hover table-striped table-condensed table-scrollable">
					<thead>
						<tr>
							<th>
								<fmt:message key="p2.2.cargos.tabla01"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.cargos.tabla02"></fmt:message>
							</th>
						</tr>
					</thead>
					<tbody  id="listaCompleta">
					</tbody>
				</table>
			</div>
		</div>
<%if (usr.getUser_role() == Constants.ROLE_ALUMNO){ %>		
		<div class="panel panel-default col-lg-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.cargos.usuario"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaUsuario" type="text"></form></p>
				<div id="listaUsuario"></div>
			</div>
		</div>
	
<%}
else if (usr.getUser_role() == Constants.ROLE_PROFESOR){ %>
		<div class="panel panel-default col-lg-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.cargos.profe"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaProfe" type="text"></form></p>
				<div id="listaProfe"></div>
			</div>
		</div>
<%} %>
	</div>
</div>
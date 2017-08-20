<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Personage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%	
User usr = (User)ses.getAttribute("user");
@SuppressWarnings(value="unchecked")
List<Personage> personajes =  (List<Personage>) request.getAttribute("listaPersonajes"); 
@SuppressWarnings(value="unchecked")
String validar = (String) request.getAttribute("validar");
%>

<div class="container">
	<div class="row">
		<h1>
			<fmt:message key="p2.8.personajes.titulo"></fmt:message>
		</h1>
	</div>
	<div class="row">
		<div class="panel panel-default col-sm-12">
			<div class="panel-heading">
				<button id="displayCreate" class="btn btn-sm btn-default btn-xs">
					<span id="displayCreatei" class="glyphicon glyphicon-chevron-down"></span>
				</button>
				&nbsp;
				<fmt:message key="p2-8.crear"/>
			</div>	
			<div id="group-block1" class="panel-body">
				<p><fmt:message key="p2.8.personajes.nombre"></fmt:message><input type="text" id="newPersonageName"></p>
				<p><fmt:message key="p2.8.personajes.otro"></fmt:message><input type="text" id="newPersonageDesc">
				<button type="button" class="btn btn-sm btn-default" id="createButton">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				<button type="button" class="btn btn-sm btn-default" id="cancelButton">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button></p>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="panel panel-default col-sm-6">
				<div class="panel-heading">
					<p><fmt:message key="p2.8.personajes.todos"></fmt:message></p>
				</div>
				<div class="panel-body">
					<p><form><fmt:message key="p2.8.personajes.filtro"></fmt:message><input id="filtroBusquedaCompleta" type="text"></form></p>
					<table class="table table-hover table-striped table-condensed table-scrollable">
						<thead>
							<tr>
								<th>
									<fmt:message key="p2.8.personajes.tabla01"></fmt:message>
								</th>
								<th>
									<fmt:message key="p2.8.personajes.tabla02"></fmt:message>
								</th>
							</tr>
						</thead>
						<tbody  id="listaCompleta">
						</tbody>
					</table>
				</div>
			</div>
	</div>
</div>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Charge"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%	
User usr = (User)ses.getAttribute("user");
@SuppressWarnings(value="unchecked")
List<Charge> cargos =  (List<Charge>) request.getAttribute("listaCargos"); 
@SuppressWarnings(value="unchecked")
String validar = (String) request.getAttribute("validar");
%>
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
		<div class="panel panel-default col-sm-12">
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
<%if (validar == null){ %>		
		<div class="panel panel-default col-sm-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.cargos.usuario"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaUsuario" type="text"></form></p>
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
					<tbody  id="listaalumno">
					</tbody>
				</table>
			</div>
		</div>
	
<%}
else{ %>
		<div class="panel panel-default col-sm-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.cargos.profe"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaProfe" type="text"></form></p>
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
					<tbody  id="listaProfe">
					</tbody>
				</table>
			</div>
		</div>
<%} %>
		<div class="panel panel-default col-sm-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.cargos.todos"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><fmt:message key="p2.2.cargos.filtro"></fmt:message><input id="filtroBusquedaCompleta" type="text"></form></p>
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
	</div>
</div>

<!-- Diálogos modales de creación de cargos -->
<div id="modalCreaCargo1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal1"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCreaCargo2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal2"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCreaCargo3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal3"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCreaCargo4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal4"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de modificación de cargos -->
<div id="modalModificaCargo1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal11"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaCargo2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal12"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaCargo3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal13"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaCargo4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal14"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaCargo5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal15"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de borrado de cargos -->
<div id="modalBorraCargo1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal20"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal21"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalBorraCargo2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal20"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal22"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
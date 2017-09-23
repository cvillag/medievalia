<%@page import="com.cvilla.medievalia.domain.TipoObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.ObjetoDOM"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%	
User usr = (User)ses.getAttribute("user");
String validar = (String) request.getAttribute("validar");

TipoObjetoDOM tipo = (TipoObjetoDOM) request.getAttribute("tipo");
%>
<form id="formUser">
	<input type="hidden" id="userrole" value="<%=usr.getUser_role()%>">
</form>
<div class="container">
	<div class="row">
		<h1>
			<fmt:message key="p2.2.objetos.titulo"></fmt:message>
		</h1>
	</div>
	<div class="row">
		<div class="panel panel-default col-sm-12">
			<div class="panel-heading">
				<button id="displayCreate" class="btn btn-sm btn-default btn-xs">
					<span id="displayCreatei" class="glyphicon glyphicon-chevron-down"></span>
				</button>
				&nbsp;
				<fmt:message key="p2-2.crear" />
			</div>	
			<div id="group-block1" class="panel-body">
				<p><fmt:message key="p2.2.objetos.nombre"></fmt:message><input type="text" id="newObjectName">
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
<%if (validar == null){ %>		
		<div class="panel panel-default col-sm-6">
			<div class="panel-heading">
				<p><fmt:message key="p2.2.objetos.usuario"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaUsuario" type="text"/></form></p>
				<table class="table table-hover table-striped table-condensed table-scrollable">
					<thead>
						<tr>
							<th>
								<fmt:message key="p2.2.objetos.tabla01"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.objetos.tabla02"></fmt:message>
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
				<p><fmt:message key="p2.2.objetos.profe"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><input id="busquedaProfe" type="text"/></form></p>
				<table class="table table-hover table-striped table-condensed table-scrollable">
					<thead>
						<tr>
							<th>
								<fmt:message key="p2.2.objetos.tabla01"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.objetos.tabla02"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.objetos.tabla03"></fmt:message>
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
				<p><fmt:message key="p2.2.objetos.todos"></fmt:message></p>
			</div>
			<div class="panel-body">
				<p><form><fmt:message key="p2.2.objetos.filtro"></fmt:message><input id="filtroBusquedaCompleta" type="text"></form></p>
				<table class="table table-hover table-striped table-condensed table-scrollable">
					<thead>
						<tr>
							<th>
								<fmt:message key="p2.2.objetos.tabla01"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.objetos.tabla04"></fmt:message>
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

<div id="modalDetalleObjeto" class="modal fade modal-lg" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>&nbsp;<span id="nombreObjetoDetalle"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contenidoDetalle">
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>


<!-- Diálogos modales de creación de objetos -->
<div id="modalCreaObjeto1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>&nbsp;<%=tipo.getNombreDOM() %>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal1"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCreaObjeto2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>&nbsp;<%=tipo.getNombreDOM() %>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<%=tipo.getNombreDOM() %>&nbsp;<fmt:message key="p2-2.modal2"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCreaObjeto3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>&nbsp;<%=tipo.getNombreDOM() %>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal3"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCreaObjeto4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal0"></fmt:message>&nbsp;<%=tipo.getNombreDOM() %>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal4"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de adición de atributos complejos -->

<div id="modalAddAtributoC1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal50"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal51"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalAddAtributoC2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal50"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal52"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalAddAtributoC3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal50"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal53"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalAddAtributoC4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal54"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal52"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalAddAtributoC5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal50"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal55"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de borrado de atributos complejos -->

<div id="modalRemAtributoC1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal60"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal61"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalRemAtributoC2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal60"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal62"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalRemAtributoC3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal60"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal63"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalRemAtributoC4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal60"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal64"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalRemAtributoC5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal60"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal65"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- SIN USO AUN -->

<!-- Diálogos modales de modificación de objetos -->
<div id="modalModificaObjeto1" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaObjeto2" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaObjeto3" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaObjeto4" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalModificaObjeto5" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de borrado de objetos -->
<div id="modalBorraObjeto1" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalBorraObjeto2" class="modal fade" role="dialog">
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
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<!-- Diálogos modales de validación de objetos -->
<div id="modalValidaObjeto1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal30"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal31"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalValidaObjeto2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal30"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal32"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modales de error -->
<div id="modalNoPrivilegios" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal40"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal41"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
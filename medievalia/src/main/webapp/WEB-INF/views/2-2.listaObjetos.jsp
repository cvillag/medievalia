<%@page import="com.cvilla.medievalia.domain.InstanciaObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.ListaRelaciones"%>
<%@page import="com.cvilla.medievalia.domain.TipoObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%	
User usr = (User)ses.getAttribute("user");
String validar = (String) request.getAttribute("validar");

TipoObjetoDOM tipo = (TipoObjetoDOM) request.getAttribute("tipo");
@SuppressWarnings("unchecked")
List<ListaRelaciones> listarel = (List<ListaRelaciones>) request.getAttribute("listasRelacion");
%>
<form id="formUser">
	<input type="hidden" id="userrole" value="<%=usr.getUser_role()%>">
</form>
<div class="container">
	<div class="row">
		<h1>
			<fmt:message key="p2.2.objetos.titulo"></fmt:message> <%=tipo.getNombreDOM() %>
		</h1>
	</div>
	<div class="row">
		<div class="panel panel-default col-sm-12">
			<div class="panel-heading">
				<button id="displayCreate" class="btn btn-sm btn-info btn-xs">
					<span id="displayCreatei" class="glyphicon glyphicon-chevron-down"></span>
				</button>
				&nbsp;
				<fmt:message key="p2-2.crear" />
			</div>	
			<div id="group-block1" class="panel-body">
				<p><fmt:message key="p2.2.objetos.nombre"></fmt:message><input type="text" id="newObjectName">
				<button type="button" class="btn btn-sm btn-info" id="createButton">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				<button type="button" class="btn btn-sm btn-info" id="cancelButton">
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
				<p><form><fmt:message key="p2.2.objetos.filtro"></fmt:message><input id="busquedaUsuario" type="text"/></form></p>
				<table class="table table-hover table-striped table-condensed table-responsive">
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
				<p>
					<form>
						<fmt:message key="p2.2.objetos.filtro"></fmt:message>
						<input id="busquedaProfe" type="text">
					</form>
				</p>
				<table class="table table-hover table-striped table-condensed table-responsive">
					<thead>
						<tr>
							<th>
								<fmt:message key="p2.2.objetos.tabla01"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.objetos.tabla02"></fmt:message>
							</th>
							<th>
								<fmt:message key="p2.2.objetos.tabla04"></fmt:message>
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
				<table class="table table-hover table-striped table-condensed table-responsive">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>


<div id="modalDetalleObjeto2" class="modal fade modal-lg" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>&nbsp;<span id="nombreObjetoDetalle2"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contenidoDetalle2">
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalcanc" class="btn btn-sm btn-info">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" id="modalsave" class="btn btn-sm btn-info">
					<fmt:message key="general.guardar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalDetalleObjetoProfe" class="modal fade modal-lg" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>&nbsp;<span id="nombreObjetoDetalleProfe"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contenidoDetalleProfe">
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalcanc" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button disabled type="button" id="modalokValidaProfe" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.validar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaObjetoProfe" class="modal fade modal-lg" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>&nbsp;<span id="nombreObjetoValidaProfe"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contenidoValidaProfe">
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalcanc" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button disabled type="button" id="modalokValidaProfe" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.validar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalModifyObjetoProfe" class="modal fade modal-lg" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>&nbsp;<span id="nombreObjetoModifyProfe"></span>
				</h4>
			</div>
			<div class="modal-body">
				<div id="contenidoModifyProfe">
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalcancp" class="btn btn-sm btn-info">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" id="modalsavep" class="btn btn-sm btn-info">
					<fmt:message key="general.guardar" ></fmt:message>
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal61"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de modificación de atributos sencillos -->

<div id="modalModAtributoS1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal70"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal71"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalModAtributoS2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal70"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal72"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalModAtributoS3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal70"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal73"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalModAtributoS4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal70"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal74"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>



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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalModificaObjeto6" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal16"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de validación de atributos complejos -->

<div id="modalValidaAtributoC0" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<span id="textoRelacionAC"></span>
				</div>
				
				<label for="textoValidaciónAC"><fmt:message key="p2-2.modal81"></fmt:message></label>
				<input type="text" id="textoValidaciónAC">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" id="validaAC" class="btn btn-sm btn-success" data-dismiss="modal">
					<fmt:message key="p2-2.modal80.button1" ></fmt:message>
				</button>
				<button type="button" id="noValidaAC" class="btn btn-sm btn-warning" data-dismiss="modal">
					<fmt:message key="p2-2.modal80.button2" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaAtributoC1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal82"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaAtributoC2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal83"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaAtributoC3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal84"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaAtributoC4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal85"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaAtributoC5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal86"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalValidaAtributoC6" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal80"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal87"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos de información de validación -->

<div id="modalMuestraTextoAC" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal90"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal91"></fmt:message><span id="modalTextoValidacion"></span>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalMuestraTextoACNoVal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal90"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal92"></fmt:message>:<span id="modalTextoNoValidacion"></span>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal previo de validación de objeto -->

<div id="modalValidaObjetoText" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal111"></fmt:message>
				</div>
				<input type="text" id="textoValidaciónOB">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" id="validaOB" class="btn btn-sm btn-success" data-dismiss="modal">
					<fmt:message key="p2-2.modal110.button1" ></fmt:message>
				</button>
				<button type="button" id="noValidaOB" class="btn btn-sm btn-warning" data-dismiss="modal">
					<fmt:message key="p2-2.modal110.button2" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal de mensaje de atributo complejo no validado -->
<div id="modalPendienteValidacionAtributo" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal100"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal de borrado de objeto -->
<div id="modalBorraObjeto0" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal20"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal21"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" id="modalDelete">
					<fmt:message key="general.borrar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

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
					<fmt:message key="p2-2.modal22"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorraObjeto1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal20"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-2.modal23"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal de validación de objeto -->

<div id="modalNoPrivilegios" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal114"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalNoObject" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal112"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalErrorParam" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-2.modal112"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalObjetoValidado" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal116"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalObjetoNoValidado" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal110"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-2.modal117"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modales de muestra de texto de validación -->

<div id="modalMuestraTextoNoOB" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal90"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal94"></fmt:message><span id="modalTextoNoValidacionOB"></span>
				</div>
				<div id="marcarleidotextovalNV" class="checkbox">
					<label>
						<input type="checkbox" checked id="marcaleidonv"> <fmt:message key="p2-2.modal95"></fmt:message>
						<input type="hidden" id="idObjetoTxtNVal">
					</label>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" id="botonmarcaleidonv" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalMuestraTextoOB" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal90"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal93"></fmt:message><span id="modalTextoValidacionOB"></span>
				</div>
				<div id="marcarleidotextoval" class="checkbox">
					<label>
						<input type="checkbox" checked id="marcaleido"> <fmt:message key="p2-2.modal95"></fmt:message>
						<input type="hidden" id="idObjetoTxtVal">
					</label>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info"  id="botonmarcaleido" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Modales de tipos de relación con sus instancias -->

<%
if(listarel != null && listarel.size() > 0 ){
	for(ListaRelaciones rel : listarel){
		if(rel.getAc() != null && rel.getAc().getIdTipoRelacion() != 0){
%>
<div id="modalRelacion<%=rel.getAc().getIdTipoHijo() %>" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal120"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
					<div class="alert alert-info form-group">
						<fmt:message key="p2-2.modal121"></fmt:message>
					</div>
					<label for="selectRelacion"><fmt:message key="p2-2.modal122"></fmt:message></label>
					<select id="selectRelacion<%=rel.getAc().getIdTipoHijo()%>" name="selectRelacion" class="form-control selectpicker" data-live-search="true">
						<%
						List<InstanciaObjetoDOM> li = rel.getLi();
						for(InstanciaObjetoDOM o : li){
						%>
						<option value="<%=o.getIdInstancia()%>" data-tokens="<%=o.getNombre()%>"><%=o.getNombre() %></option>
						<%} %>
					</select>
				<%if(rel.getAc().getConFecha() == 1){ %>
					<br>
					<label><fmt:message key="p2-2.modal123"></fmt:message></label>
					<form class="form-inline">
						<div class="form-group">
							<label for="diaI<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" name="diaI<%=rel.getAc().getIdTipoHijo() %>" id="diaI<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="mesI<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" name="mesI<%=rel.getAc().getIdTipoHijo() %>" id="mesI<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="anioI<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" name="anioI<%=rel.getAc().getIdTipoHijo() %>" id="anioI<%=rel.getAc().getIdTipoHijo() %>" size="4" class="inputDays form-control">
						</div>
					</form>
					<br>
					<label><fmt:message key="p2-2.modal124"></fmt:message></label>
					<form class="form-inline">
						<div class="form-group">
							<label for="diaF<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" name="diaF<%=rel.getAc().getIdTipoHijo() %>" id="diaF<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="mesF<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" name="mesF<%=rel.getAc().getIdTipoHijo() %>" id="mesF<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="anioF<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" name="anioF<%=rel.getAc().getIdTipoHijo() %>" id="anioF<%=rel.getAc().getIdTipoHijo() %>" size="4" class="inputDays form-control">
						</div>
					</form>
				<%} %>
				<input type="hidden" name="conFecha" id="conFecha<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="inst" id="instHR<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="tipo" id="tipoHR<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="pag" id="pag<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="name" id="name<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="idInstanciaModificar" id="idInstanciaModificar<%=rel.getAc().getIdTipoHijo()%>">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" class="btn btn-sm btn-info buttonmodalasignacionrelacion" data-dismiss="modal" data-tipo="<%=rel.getAc().getIdTipoHijo() %>">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalModifyRelacion<%=rel.getAc().getIdTipoHijo() %>" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal120"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
					<div class="alert alert-info form-group">
						<fmt:message key="p2-2.modal121"></fmt:message>
					</div>
					<label for="selectRelacionM"><fmt:message key="p2-2.modal122"></fmt:message></label>
					<select id="selectRelacionM<%=rel.getAc().getIdTipoHijo()%>" name="selectRelacionM" class="form-control selectpicker" data-live-search="true">
						<%
						List<InstanciaObjetoDOM> li2 = rel.getLi();
						for(InstanciaObjetoDOM o : li2){
						%>
						<option value="<%=o.getIdInstancia()%>" data-tokens="<%=o.getNombre()%>"><%=o.getNombre() %></option>
						<%} %>
					</select>
				<%if(rel.getAc().getConFecha() == 1){ %>
					<br>
					<label><fmt:message key="p2-2.modal123"></fmt:message></label>
					<form class="form-inline">
						<div class="form-group">
							<label for="diaIM<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" name="diaIM<%=rel.getAc().getIdTipoHijo() %>" id="diaIM<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="mesIM<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" name="mesIM<%=rel.getAc().getIdTipoHijo() %>" id="mesIM<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="anioIM<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" name="anioIM<%=rel.getAc().getIdTipoHijo() %>" id="anioIM<%=rel.getAc().getIdTipoHijo() %>" size="4" class="inputDays form-control">
						</div>
					</form>
					<br>
					<label><fmt:message key="p2-2.modal124"></fmt:message></label>
					<form class="form-inline">
						<div class="form-group">
							<label for="diaFM<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" name="diaFM<%=rel.getAc().getIdTipoHijo() %>" id="diaFM<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="mesFM<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" name="mesFM<%=rel.getAc().getIdTipoHijo() %>" id="mesFM<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="anioFM<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" name="anioFM<%=rel.getAc().getIdTipoHijo() %>" id="anioFM<%=rel.getAc().getIdTipoHijo() %>" size="4" class="inputDays form-control">
						</div>
					</form>
				<%} %>
				<input type="hidden" name="conFechaM" id="conFechaM<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="instM" id="instHRM<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="tipoM" id="tipoHRM<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="pagM" id="pagM<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="nameM" id="nameM<%=rel.getAc().getIdTipoHijo()%>">
				<input type="hidden" name="idInstanciaModificarM" id="idInstanciaModificarM<%=rel.getAc().getIdTipoHijo()%>">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" class="btn btn-sm btn-info buttonmodalmodifyrelacion" data-dismiss="modal" data-tipo="<%=rel.getAc().getIdTipoHijo() %>">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalViewRelacion<%=rel.getAc().getIdTipoHijo() %>" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal120"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
					<div class="alert alert-info form-group">
						<fmt:message key="p2-2.modal121"></fmt:message>
					</div>
					<label for="selectRelacionV"><fmt:message key="p2-2.modal122"></fmt:message></label>
					<select id="selectRelacionV<%=rel.getAc().getIdTipoHijo()%>" class="form-control selectpicker" data-live-search="true" DISABLED>
						<%
						List<InstanciaObjetoDOM> li3 = rel.getLi();
						for(InstanciaObjetoDOM o : li3){
						%>
						<option value="<%=o.getIdInstancia()%>" data-tokens="<%=o.getNombre()%>"><%=o.getNombre() %></option>
						<%} %>
					</select>
				<%if(rel.getAc().getConFecha() == 1){ %>
					<br>
					<label><fmt:message key="p2-2.modal123"></fmt:message></label>
					<form class="form-inline">
						<div class="form-group">
							<label for="diaIV<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input DISABLED type="number" name="diaIV<%=rel.getAc().getIdTipoHijo() %>" id="diaIV<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="mesIV<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input DISABLED type="number" name="mesIV<%=rel.getAc().getIdTipoHijo() %>" id="mesIV<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="anioIV<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input DISABLED type="number" name="anioIV<%=rel.getAc().getIdTipoHijo() %>" id="anioIV<%=rel.getAc().getIdTipoHijo() %>" size="4" class="inputDays form-control">
						</div>
					</form>
					<br>
					<label><fmt:message key="p2-2.modal124"></fmt:message></label>
					<form class="form-inline">
						<div class="form-group">
							<label for="diaFV<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input DISABLED type="number" name="diaFV<%=rel.getAc().getIdTipoHijo() %>" id="diaFV<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="mesFV<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input DISABLED type="number" name="mesFV<%=rel.getAc().getIdTipoHijo() %>" id="mesFV<%=rel.getAc().getIdTipoHijo() %>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="form-group">
							<label for="anioFV<%=rel.getAc().getIdTipoHijo() %>"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input DISABLED type="number" name="anioFV<%=rel.getAc().getIdTipoHijo() %>" id="anioFV<%=rel.getAc().getIdTipoHijo() %>" size="4" class="inputDays form-control">
						</div>
					</form>
				<%} %>				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<%
		}
	}
}%>

<div id="marcaTextoLeido1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal130"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal131"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="marcaTextoLeido2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-2.modal130"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-2.modal132"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
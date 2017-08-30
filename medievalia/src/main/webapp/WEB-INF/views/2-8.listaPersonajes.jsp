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
				<button type="button" class="btn btn-sm btn-default" id="createButton">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				</button>
				&nbsp;
				<fmt:message key="p2-8.crear"/>
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

<!-- Diálogos modales de modificación -->

<div id="modalCambiarDatos" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.datos.01"></fmt:message> <span id="modalDatosNombre"></span>
				</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group row">
						<div class="col-xs-12">
							<label for="modalOtros"><fmt:message key="p2-8.modal.datos.02"></fmt:message></label>
							<textarea class="form-control rows="3" id="modalOtros"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label><fmt:message key="p2-8.modal.datos.09"></fmt:message></label>
						</div>
					</div>
					<div class="row">
						
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowDN" checked="checked">
							<label for="modalDiaNac"><fmt:message key="p2-8.modal.datos.03"></fmt:message></label>
							<input type="number" id="modalDiaNac" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowMN" checked="checked">
							<label for="modalMesNac"><fmt:message key="p2-8.modal.datos.04"></fmt:message></label>
							<input type="number" id="modalMesNac" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowAN" checked="checked">
							<label for="modalAnioNac"><fmt:message key="p2-8.modal.datos.05"></fmt:message></label>
							<input type="number" id="modalAnioNac" size="3" max="9999" class="inputYears form-control">
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label><fmt:message key="p2-8.modal.datos.00"></fmt:message></label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowDF" checked="checked">
							<label for="modalDiaFal"><fmt:message key="p2-8.modal.datos.06"></fmt:message></label>
							<input type="number" id="modalDiaFal" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowMF" checked="checked">
							<label for="modalMesFal"><fmt:message key="p2-8.modal.datos.07"></fmt:message></label>
							<input type="number" id="modalMesFal" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowAF" checked="checked">
							<label for="modalAnioFal"><fmt:message key="p2-8.modal.datos.08"></fmt:message></label>
							<input type="number" id="modalAnioFal" size="4" max="9999" class="inputYears form-control">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="modalDatosOk" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
				<button type="button" id="modalDatosCancel" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalCrear" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.crear.01"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group row">
						<div class="col-xs-12">
							<label for="crearNombre"><fmt:message key="p2-8.modal.crear.11"></fmt:message></label>
							<input type="text" id="crearNombre" class="form-control">
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-12">
							<label for="crearOtros"><fmt:message key="p2-8.modal.crear.02"></fmt:message></label>
							<textarea class="form-control rows="3" id="modalOtros2"></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label><fmt:message key="p2-8.modal.crear.09"></fmt:message></label>
						</div>
					</div>
					<div class="row">
						
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowDN2">
							<label for="modalDiaNac2"><fmt:message key="p2-8.modal.crear.03"></fmt:message></label>
							<input type="number" id="modalDiaNac2" size="2" min="1" max="31" class="inputDays form-control" disabled>
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowMN2">
							<label for="modalMesNac2"><fmt:message key="p2-8.modal.crear.04"></fmt:message></label>
							<input type="number" id="modalMesNac2" size="2" min="1" max="12" class="inputDays form-control" disabled>
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowAN2">
							<label for="modalAnioNac2"><fmt:message key="p2-8.modal.crear.05"></fmt:message></label>
							<input type="number" id="modalAnioNac2" size="3" max="9999" class="inputYears form-control" disabled>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label><fmt:message key="p2-8.modal.crear.00"></fmt:message></label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowDF2" >
							<label for="modalDiaFal2"><fmt:message key="p2-8.modal.crear.06"></fmt:message></label>
							<input type="number" id="modalDiaFal2" size="2" min="1" max="31" class="inputDays form-control" disabled>
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowMF2">
							<label for="modalMesFal2"><fmt:message key="p2-8.modal.crear.07"></fmt:message></label>
							<input type="number" id="modalMesFal2" size="2" min="1" max="12" class="inputDays form-control" disabled>
						</div>
						<div class="col-xs-3 form-group">
							<input type="checkbox" id="knowAF2">
							<label for="modalAnioFal2"><fmt:message key="p2-8.modal.crear.08"></fmt:message></label>
							<input type="number" id="modalAnioFal2" size="4" max="9999" class="inputYears form-control" disabled>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="modalCrearOk" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
				<button type="button" id="modalCrearCancel" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<!-- Diálogos modales de resultados -->

<div id="modalCrearPersonaje1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.crear.10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-8.modal.crear.11"></fmt:message>
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

<div id="modalCrearPersonaje2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.crear.10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.crear.12"></fmt:message>
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

<div id="modalCrearPersonaje3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.crear.10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.crear.13"></fmt:message>
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

<div id="modalCrearPersonaje4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.crear.10"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.crear.14"></fmt:message>
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


<div id="modalBorrarPersonajePrev" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.borrar.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message key="p2-8.modal.borrar.05"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalConfirmarBorrado" class="btn btn-sm btn-default">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
				<button type="button" id="modalBorrarCancel" class="btn btn-sm btn-default" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorraPersonaje1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.borrar.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-8.modal.borrar.01"></fmt:message>
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

<div id="modalBorraPersonaje2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.borrar.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-8.modal.borrar.02"></fmt:message>
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

<div id="modalBorraPersonaje3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.borrar.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-8.modal.borrar.03"></fmt:message>
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

<div id="modalBorraPersonaje4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.borrar.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-8.modal.borrar.04"></fmt:message>
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

<div id="modalModificaPersonaje1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.ren.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-8.modal.ren.01"></fmt:message>
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

<div id="modalModificaPersonaje2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.ren.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-8.modal.ren.02"></fmt:message>
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

<div id="modalModificaPersonaje3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.ren.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-8.modal.ren.03"></fmt:message>
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

<div id="modalModificaPersonaje4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.ren.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.ren.04"></fmt:message>
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
<div id="modalModificaPersonaje5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.ren.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.ren.05"></fmt:message>
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

<div id="modalModificaPersonaje6" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.ren.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.ren.06"></fmt:message>
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

<div id="modalModPersonaje1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.mod.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-8.modal.mod.01"></fmt:message>
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

<div id="modalModPersonaje2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.mod.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.mod.02"></fmt:message>
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

<div id="modalModPersonaje3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.mod.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.mod.03"></fmt:message>
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

<div id="modalModPersonaje4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.mod.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-8.modal.mod.04"></fmt:message>
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

<div id="modalModPersonaje5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-8.modal.mod.00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-8.modal.mod.05"></fmt:message>
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

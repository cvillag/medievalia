<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%
User u = (User)request.getAttribute("usuario");
%>
<%@ include file="/WEB-INF/views/common/inicio-comun.jsp"%>
<%@ include file="/WEB-INF/views/common/groups-list.jsp"%>
<div class="container">
<!-- FIXME: Dos formularios: crear usuario y botón +, que haga aparecer el formulario con el nombre y acción crear -->
	<form class="form-inline" role="form" id="firstForm">
		<label class="control-label formGroup" for="showform"><fmt:message key="p4-0.crearGrupo"></fmt:message></label>
		<button type="button" class="btn btn-default" id="showform">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</form>
	<form class="form-inline" role="form" action="createGroup.do" id="secondForm">
		<label class="control-label formGroup" for="showform"><fmt:message key="p4-0.nombreGrupo"></fmt:message></label>
		<input type="text" name="nombreGrupo" id="nombreGrupo">
		<button type="button" class="btn btn-default" id="createButton">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
		<button type="button" class="btn btn-default" id="cancelButton">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		</button>
	</form>
</div>
<div id="modalCrearGrupo" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-0.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p4-0.modal1"></fmt:message>
				</p>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalCrearGrupo2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-0.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p4-0.modal2"></fmt:message>
				</p>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
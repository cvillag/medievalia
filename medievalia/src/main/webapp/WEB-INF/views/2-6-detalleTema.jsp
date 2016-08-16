<%@page import="com.cvilla.medievalia.domain.Tema"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%Tema t = (Tema)request.getAttribute("tema"); %>
<div class="container">
	<h2><fmt:message key="p2-6.title"></fmt:message></h2>
	<form>
		<div class="form-group">
			<label class="col-xs-3 control-label" for="nombre"><fmt:message
					key="p2-6-nombreTema" /></label>
			<div class="col-xs-4">
				<input class="form-control" type="text" id="nombreTema" name="nombreTema" value="<%=t.getNombre()%>" disabled/>
			</div>
			<button type="button" class="btn btn-default" id="activarNombre">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default" id="desactivarNombre">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default" id="cambiarNombre">
				<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
			</button>
		</div>
	</form>
</div>
<div class="container">
	<h2><fmt:message key="p2-6.subtemas"></fmt:message></h2>
</div>
<div class="container" id="listasubtemas">
</div>

<div class="container">
	<div id="firstForm">
		<label class="control-label formTopic" for="showform"><fmt:message key="p2-6.crearSTema"></fmt:message></label>
		<button type="button" class="btn btn-default" id="showform">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</div>
	<div id="secondForm">
		<label class="control-label formGroup" for="showform"><fmt:message key="p2-6.nombreSTema"></fmt:message></label>
		<input type="text" name="nombreSTema" id="nombreSTema">
		<button type="button" class="btn btn-default" id="createButton">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
		<button type="button" class="btn btn-default" id="cancelButton">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		</button>
	</div>
</div>

<!-- Diálogos de cambio de nombre de tema -->
<div id="modalRenTema" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal2"></fmt:message>
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
<div id="modalRenTema2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal3"></fmt:message>
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
<div id="modalRenTema3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal4"></fmt:message>
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
<div id="modalRenTema4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal5"></fmt:message>
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

<!-- Diálogos de creación de subtemas -->

<div id="modalCreaSTema" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal6"></fmt:message>
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
<div id="modalCreaSTema2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal7"></fmt:message>
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
<div id="modalCreaSTema3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal8"></fmt:message>
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
<div id="modalCreaSTema4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-6.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p2-6.modal9"></fmt:message>
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

<form id="topicDetail" method="post" action="topicDetail.do">
	<input type="hidden" name="idTopic" id="idTopic"/>
	<input type="hidden" name="idTema" id="idTema" value="<%=t.getIdTema()%>">
	<input type="hidden" name="topicName" id="topicName" value="<%=t.getNombre()%>"/>
</form>
<%@ include file="/WEB-INF/views/footer.jsp"%>
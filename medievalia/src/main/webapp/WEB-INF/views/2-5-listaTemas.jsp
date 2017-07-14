<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<div class="container">
	<h2>
		<fmt:message key="p2-5.listaTemas"></fmt:message>
	</h2>
</div>
<div class="container" id="listatemas">
</div>
<div class="container"></div>
<div class="container">
	<div id="firstForm">
		<label class="control-label formTopic" for="showform"><fmt:message key="p2-5.crearTema"></fmt:message></label>
		<button type="button" class="btn btn-default" id="showform">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
	</div>
	<div id="secondForm">
		<label class="control-label formGroup" for="showform"><fmt:message key="p2-5.nombreTema"></fmt:message></label>
		<input type="text" name="nombreTema" id="nombreTema">
		<button type="button" class="btn btn-default" id="createButton">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</button>
		<button type="button" class="btn btn-default" id="cancelButton">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
		</button>
	</div>
</div>
<div id="modalCrearTema" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-5.modal2"></fmt:message>
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
<div id="modalCrearTema2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-5.modal3"></fmt:message>
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
<div id="modalCrearGrupo3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-5.modal4"></fmt:message>
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
<div id="modalCrearGrupo4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-5.modal5"></fmt:message>
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

<div id="modalBorrarTema" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p2-5.modal7"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>



<div id="modalBorrarnoId" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-5.modal8"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorrarnoPrivileges" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-5.modal9"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorrarnoTopic" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-5.modal10"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorrarnoGroup" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-5.modal9"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorrarnoBorrado" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p2-5.modal11"></fmt:message>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalBorrarborrado" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="p2-5.modal6"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p2-5.modal12"></fmt:message><span id="numSubt"></span>
				</div>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalborrar" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<form id="topicDetail" method="post" action="topicDetail.do">
	<input type="hidden" name="idTopic" id="idTopic"/>
</form>

<%@ include file="/WEB-INF/views/footer.jsp"%>
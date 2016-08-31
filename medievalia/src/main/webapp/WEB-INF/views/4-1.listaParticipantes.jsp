<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<div class="container">
	<h2><fmt:message key="p4-1.titulo"></fmt:message> </h2>
	<div class="modal-body" id="divListaUsers">
							
	</div>
</div>





<div id="modalMatricularAlumno" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p2-1.listaUsuarios.title"></fmt:message>
				</h4>
			</div>
			<div class="modal-body" id="divListaPossibleUsers">
							
			</div>
			<div id="resultOk" class="modal-body alert alert-success" role="alert" >
				<fmt:message key="p2.1.listausuarios.resultOk"></fmt:message>
			</div>
			<div id="resultNok" class="modal-body alert alert-danger" role="alert" >
				<fmt:message key="p2.1.listausuarios.resultNok"></fmt:message>
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
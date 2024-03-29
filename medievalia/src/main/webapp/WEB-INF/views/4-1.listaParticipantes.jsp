<%@page import="com.cvilla.medievalia.domain.Teachers"%>
<%@page import="com.cvilla.medievalia.domain.Students"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<% 
@SuppressWarnings("unchecked")
List<Students> ls = (List<Students>)request.getAttribute("listaS"); 
@SuppressWarnings("unchecked")
List<Teachers> lt = (List<Teachers>)request.getAttribute("listaT");%>

<div class="container">
	<h2><fmt:message key="p4-1.titulo"></fmt:message> </h2>
</div>
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<button class="btn btn-info" data-toggle="modal" data-target="#modalMatricularAlumno" title="<fmt:message key="p4-1.btn.title"></fmt:message>"><fmt:message key="p4-1.btn.matricular"></fmt:message></button>
		</div>
	</div>
	<div class="row">
		<div class="panel panel-default col-lg-6" id="listaAlumnos">
			
		</div>
		<div class="panel panel-default col-lg-6" id="listaProfesores">
		</div>
	</div>
	<div class="row">
		<div id="desresultOk" class="modal-body alert alert-success" role="alert" >
			<fmt:message key="p2.1.desmatricular.resultOk"></fmt:message>
		</div>
		<div id="desresultNok" class="modal-body alert alert-danger" role="alert" >
			<fmt:message key="p2.1.desmatricular.resultNok"></fmt:message>
		</div>
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>

<div id="modalDesmatricula0" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-1.modal.desmat"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p4-1.modal.desmat10"></fmt:message>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.cancelar" ></fmt:message>
				</button>
				<button type="button" class="btn btn-sm btn-danger" data-dismiss="modal" id="modalUnenroll">
					<fmt:message key="general.borrar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="modalDesmatricula1" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-1.modal.desmat"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p4-1.modal.desmat01"></fmt:message>
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

<div id="modalDesmatricula2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-1.modal.desmat"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p4-1.modal.desmat02"></fmt:message>
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

<div id="modalDesmatricula3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-1.modal.desmat"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p4-1.modal.desmat03"></fmt:message>
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

<div id="modalDesmatricula4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p4-1.modal.desmat"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p4-1.modal.desmat04"></fmt:message>
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

<%@ include file="/WEB-INF/views/footer.jsp"%>
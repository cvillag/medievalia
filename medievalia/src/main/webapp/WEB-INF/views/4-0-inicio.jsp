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
				<button type="button" id="modalokcreate" class="btn btn-sm btn-info">
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
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
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
					<fmt:message key="p4-0.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<fmt:message key="p4-0.modal3"></fmt:message>
				</p>				
			</div>
			<div class="modal-footer">
				<button type="button" id="modalok" class="btn btn-sm btn-info" data-dismiss="modal">
					<fmt:message key="general.aceptar" ></fmt:message>
				</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/footer.jsp"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%
User u = (User)request.getAttribute("usuario");
%>
<div class="container">
	<form class="form-horizontal" role="form" id="modifyForm">
	<div class="form-group" id="formNombre">
		<label class="col-xs-3" for="nombre"><fmt:message key="p5.1.nombreUser"></fmt:message></label>
		<div class="col-xs-4">
			<input type="text" class="form-control" id="nombre" name="nombre" value="<%=u.getUser_name()%>" disabled>
		</div>
		<div class="col-xs-3">
			<div id="modificarUser">
				<label><fmt:message key="p5.1.btn1"></fmt:message></label>
				<button class="btn btn-sm btn-info" id="btnU-modificar">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				</button>
			</div>
			<div id="modificarUser2">
				<label><fmt:message key="p5.1.btn1"></fmt:message></label>
				<button class="btn btn-sm btn-info" id="btnU-cancel">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
				<button class="btn btn-sm btn-info" id="btnU-guardar">
					<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
				</button>
			</div>
		</div>
	</div>
	<div class="form-group" id="formNombreC">
		<label class="col-xs-3" for="nombreC"><fmt:message key="p5.1.nombreCUser"></fmt:message></label>
		<div class="col-xs-4">
			<input type="text" class="form-control" id="nombreC" name="nombreC" value="<%=u.getUser_long_name() %>" disabled> 
		</div>	
	</div>
	<div class="form-group">
		<label class="col-xs-3"><fmt:message key="p5.1.role"></fmt:message></label>
		<div class="col-xs-4">
			<input type="text" id="role" class="form-control" value=" 
			<%if(u.getUser_role() == Constants.ROLE_ADMIN){ %>
			<fmt:message key="general.administrador"></fmt:message>
			<%}else if(u.getUser_role() == Constants.ROLE_PROFESOR){ %>
			<fmt:message key="general.profesor"></fmt:message>
			<%}else if(u.getUser_role() == Constants.ROLE_ALUMNO){ %>
			<fmt:message key="general.alumno"></fmt:message>
			<%}else if(u.getUser_role() == Constants.ROLE_INACTIVO){ %>
			<fmt:message key="general.inactivo"></fmt:message>
			<%} %>
		 	" disabled>
		 </div>
	</div>
	
	
		<div class="form-group" id="modificarPass">
		<label class="col-xs-3"><fmt:message key="p5.1.btn2"></fmt:message></label>
		<div class="col-xs-4">
			<button class="btn btn-sm btn-info" id="btnP-modificar">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			</button>
		</div>
	</div>
	<div class="form-group" id="passForm1">
		<label class="col-xs-3"><fmt:message key="p5.1.pass1"></fmt:message></label>
		<div class="col-xs-4">
			<input type="password" class="form-control" id="pass1" name="pass1" disabled> 
		</div>	
	</div>
	<div class="form-group" id="passForm2">
		<label class="col-xs-3"><fmt:message key="p5.1.pass2"></fmt:message></label>
		<div class="col-xs-4">
			<input type="password" class="form-control" id="pass2" name="pass2" disabled> 
		</div>	
	</div>
	<div class="form-group" id="passForm3">
		<label class="col-xs-3"><fmt:message key="p5.1.pass3"></fmt:message></label>
		<div class="col-xs-4">
			<input type="password" class="form-control" id="pass3" name="pass3" disabled> 
		</div>	
	</div>
	<div class="form-group" id="modificarPass2">
		<label class="col-xs-3"><fmt:message key="p5.1.btn2"></fmt:message></label>
		<div class="col-xs-4">
			<button class="btn btn-sm btn-info" id="btnP-cancel">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</button>
			<button class="btn btn-sm btn-info" id="btnP-guardar">
				<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
			</button>
		</div>
	</div>
	</form>
</div>

<input type="hidden" id="nameO" value="<%=u.getUser_name()%>">
<input type="hidden" id="nameLO" value="<%=u.getUser_long_name()%>">

<!-- Diálogos modales de cambio de datos de usuario -->

<div id="modalModUser" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p5-1.modal1"></fmt:message>
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

<div id="modalModUser2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p5-1.modal2"></fmt:message>
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

<div id="modalModUser3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p5-1.modal3"></fmt:message>
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

<div id="modalModUser4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal0"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p5-1.modal4"></fmt:message>
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

<!-- Diálogos modales de cambio de contraseña -->

<div id="modalModPass2" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-success">
					<fmt:message key="p5-1.modal5"></fmt:message>
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

<div id="modalModPass3" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger">
					<fmt:message key="p5-1.modal6"></fmt:message>
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

<div id="modalModPass4" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p5-1.modal7"></fmt:message>
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

<div id="modalModPass5" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p5-1.modal8"></fmt:message>
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

<div id="modalModPass6" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<fmt:message key="p5-1.modal00"></fmt:message>
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-warning">
					<fmt:message key="p5-1.modal9"></fmt:message>
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

<%@ include file="/WEB-INF/views/common/groups-list.jsp"%>
<%@ include file="/WEB-INF/views/footer.jsp"%>
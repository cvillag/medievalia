<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<div class="container"">


	<!--  TOOO: Cambiar el grid -->

	<form class="form-horizontal" id="formusuario"
		action="createUserAction.do" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend></legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="name"><fmt:message
						key="p1-2.form.name" /></label>
				<div class="col-md-4">
					<input id="name" name="name" type="text" placeholder="Login"
						class="form-control input-md">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="longname"><fmt:message
						key="p1-2.form.longname" /></label>
				<div class="col-md-4">
					<input id="longname" name="longname" type="text"
						placeholder="<fmt:message key="p1-2.form.longname"/>"
						class="form-control input-md">

				</div>
			</div>

			<!-- Multiple Radios (inline) -->
			<%
			List<Role> roles = (List<Role>)request.getAttribute("roles");
		%>

			<div class="form-group">
				<label class="col-md-4 control-label" for="role"><fmt:message
						key="p1-2.form.role" /></label>
				<div class="col-md-4">
					<%
		  	int o = 0;
		  	for(Role r : roles){
		  		if(o>0){
		  %>
					<label class="radio-inline" for="role-<%=o%>"> <input
						type="radio" name="role" id="role-<%=o%> %>"
						value="<%=r.getRol()%>" <% if(o == 3){ %> checked <%} %>>
						<%=r.getNombreRol() %>
					</label>
					<%
		  		}
		  		o++;
		  	} %>
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="pass"><fmt:message
						key="p1-2.form.pass" /></label>
				<div class="col-md-4">
					<input id="pass" name="pass" type="password"
						placeholder="<fmt:message key="p1-2.form.passhelp"/>"
						class="form-control input-md"> <span class="help-block"><fmt:message
							key="p1-2.form.passnote" /></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label" for="pass2"><fmt:message
						key="p1-2.form.pass2" /></label>
				<div class="col-md-4">
					<input id="pass2" name="pass2" type="password"
						placeholder="<fmt:message key="p1-2.form.passhelp"/>"
						class="form-control input-md">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button id="createUser" name="createUser" class="btn btn-sm btn-info" title="<fmt:message key="acc.creauser" />">
						<fmt:message key="p1-2.form.button" />
					</button>
				</div>
				<div class="col-md-4"></div>
			</div>

		</fieldset>
	</form>

	<div class="panel panel-default" id="suceso">
		<p>Estado</p>
	</div>
</div>
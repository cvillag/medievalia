<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<%
	List<Role> roles = (List<Role>)request.getAttribute("roles");
	User user = (User)request.getAttribute("targetUser");
	String message = (String)request.getAttribute("message");
%>

<div class="container"">
	

<!--  TOOO: Cambiar el grid -->
	
	<form class="form-horizontal" id="formusuario" action="modifyUserAction.do" method="post">
	<fieldset>
	
	<!-- Form Name -->
	<legend></legend>
	
	<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="name"><fmt:message key="p1-2.form.name"/></label>  
		  <div class="col-md-4">
		  <input id="name" name="name" type="text" placeholder="Login" class="form-control input-md" value="<%=user.getUser_name()%>">
		    
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="longname"><fmt:message key="p1-2.form.longname"/></label>  
		  <div class="col-md-4">
		  <input id="longname" name="longname" type="text" placeholder="<fmt:message key="p1-2.form.longname"/>" class="form-control input-md" value="<%=user.getUser_long_name()%>">
		    
		  </div>
		</div>
		
		<!-- Multiple Radios (inline) -->
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="role"><fmt:message key="p1-2.form.role"/></label>
		  <div class="col-md-4"> 
		  <%
		  	int o = 0;
		  	for(Role r : roles){
		  		if(o>0){
		  %>
		    <label class="radio-inline" for="role-<%=o%>">
		      <input type="radio" name="role" id="role-<%=o%> %>" value="<%=r.getRol()%>" <% if(r.getRol() == user.getUser_role()){ %>checked <%} %>>
		      <%=r.getNombreRol() %>
		    </label>
		  <%
		  		}
		  		o++;
		  	} %> 
		  </div>
		</div>
		
		<!-- Password input
		<div class="form-group">
		  <label class="col-md-4 control-label" for="oldpass"><fmt:message key="p1-3.1.form.oldpass"/></label>
		  <div class="col-md-4">
		    <input id="oldpass" name="oldpass" type="password" class="form-control input-md" >
		  </div>
		</div>-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="newpass"><fmt:message key="p1-3.1.form.pass"/></label>
		  <div class="col-md-4">
		    <input id="pass" name="pass" type="password" class="form-control input-md" >
		  </div>
		</div>
		<div class="form-group">
		  <label class="col-md-4 control-label" for="newpass2"><fmt:message key="p1-3.1.form.pass2"/></label>
		  <div class="col-md-4">
		    <input id="pass2" name="pass2" type="password" class="form-control input-md" >
		  </div>
		</div>
		
		<div class="form-group">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<button id="saveChanges" name="saveChanges" class="btn btn-primary"><fmt:message key="p1-3.1.form.button"/></button>
			</div>
			<div class="col-md-4"></div>
		</div>
		<input type="hidden" name="id" id="id" value="<%=user.getId()%>">
	</fieldset>
	</form>
	<% if(message != null && message.length()>0 ){ %>
	<div class="container">
		<% if(message.equals("p1-3.1.error.mismatchPass")){ %>
		<div class="alert alert-danger"><fmt:message key="p1-3.1.error.mismatchPass"/></div>
		<%}
		else{
			if(message.equals("p1-3.1.error.nok")){ %>
		<div class="alert alert-danger"><fmt:message key="p1-3.1.error.nok"/></div>
		<%	}
		}%>
	<%} %>
	</div>
</div>
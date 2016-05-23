<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<div class="container"">
	

<!--  TOOO: Cambiar el grid -->
	
<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<legend></legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="name"><fmt:message key="p1-2.form.name"/></label>  
  <div class="col-md-4">
  <input id="name" name="name" type="text" placeholder="Login" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="longname"><fmt:message key="p1-2.form.longname"/></label>  
  <div class="col-md-4">
  <input id="longname" name="longname" type="text" placeholder="<fmt:message key="p1-2.form.longname"/>" class="form-control input-md">
    
  </div>
</div>

<!-- Multiple Radios (inline) -->
<!-- TODO: Crear los  DAO y Service de los roles e importar aquí los existentes -->
<div class="form-group">
  <label class="col-md-4 control-label" for="role"><fmt:message key="p1-2.form.role"/></label>
  <div class="col-md-4"> 
    <label class="radio-inline" for="role-0">
      <input type="radio" name="role" id="role-0" value="1">
      Administrador
    </label> 
    <label class="radio-inline" for="role-1">
      <input type="radio" name="role" id="role-1" value="2">
      Profesor
    </label> 
    <label class="radio-inline" for="role-2">
      <input type="radio" name="role" id="role-2" value="3" checked="checked">
      Alumno
    </label> 
    <label class="radio-inline" for="role-3">
      <input type="radio" name="role" id="role-3" value="4">
      Inactivo
    </label>
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="pass"><fmt:message key="p1-2.form.pass"/></label>
  <div class="col-md-4">
    <input id="pass" name="pass" type="password" placeholder="<fmt:message key="p1-2.form.passhelp"/>" class="form-control input-md" >
    <span class="help-block"><fmt:message key="p1-2.form.passnote"/></span>
  </div>
</div>

<div class="form-group">
	<div class="col-md-4">
		<button id="createUser" name="createUser" class="btn btn-primary"><fmt:message key="p1-2.form.button"/></button>
	</div>

</div>

</fieldset>
</form>
	
</div>
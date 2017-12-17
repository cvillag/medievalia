<%@page import="com.cvilla.medievalia.domain.Group"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User ugl= (User)request.getAttribute("usuario");
HttpSession sess = request.getSession();
Group grsel = (Group) sess.getAttribute("grupoActual");
int idGr =0;
if(grsel != null){
	idGr = grsel.getIdGrupo();
}
%>
<div class="container">
<%
if (ugl.getUser_role() == Constants.ROLE_PROFESOR || ugl.getUser_role() == Constants.ROLE_ADMIN){ %>

<%if (ugl.getUser_role() == Constants.ROLE_PROFESOR){%>
	<form class="form-inline" role="form" id="firstForm">
		<div class="form-group">
			<label class="control-label formGroup" for="showform"><fmt:message key="p4-0.crearGrupo"></fmt:message></label>
			<button type="button" class="btn btn-sm btn-info" id="showform">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			</button>
		</div>
	</form>
	<form id="secondForm">
		<div class="formGroup" id="nameGroupForm">
			<label class="control-label formGroup" for="nombreGrupo"><fmt:message key="p4-0.nombreGrupo"></fmt:message></label>
			<input type="text" name="nombreGrupo" id="nombreGrupo" class="form-control">
		</div>
		<div class="form-group" id="descGroupForm">
			<label class="control-label" for="descripcionGrupo"><fmt:message key="p4-0.descGrupo"></fmt:message></label>
			<textarea class="form-control" rows="3" name="descripcionGrupo" id="descripcionGrupo"></textarea>
			<p class="help-block"><fmt:message key="p4-0.descAyuda"></fmt:message></p>
		</div>
		<div class="form-group" id="buttonGroupForm">
			<button type="button" class="btn btn-sm btn-info" id="createButton">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-sm btn-info" id="cancelButton">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</button>
		</div>
	</form>
<%}}
%>
</div>
<form action="contentManager.do" method="post" id="activeGroupForm">
	<input type="hidden" name="idGroup" id="idGroup">
	<input type="hidden" name="groupSelected" id="groupSelected" value="<%=idGr%>" />
	<input type="hidden" name="change" value="true">
</form>
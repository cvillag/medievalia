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
if (ugl.getUser_role() == Constants.ROLE_PROFESOR || ugl.getUser_role() == Constants.ROLE_ADMIN){ %>
<div class="container">
	<legend>
		<h3>
			<button id="displayGroup1" class="btn btn-default btn-xs">
				<span id="displayGroup1i" class="glyphicon glyphicon-chevron-down"></span>
			</button>
			&nbsp;
			<fmt:message key="p1-3.2.gruposDir" />
		</h3>
	</legend>
</div>
<div id="group-block1" class="container"></div>
<%if (ugl.getUser_role() == Constants.ROLE_PROFESOR){%>
<div class="container">
	<form class="form-inline" role="form" id="firstForm">
		<div class="form-group">
			<label class="control-label formGroup" for="showform"><fmt:message key="p4-0.crearGrupo"></fmt:message></label>
			<button type="button" class="btn btn-default" id="showform">
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
			<button type="button" class="btn btn-default" id="createButton">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default" id="cancelButton">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</button>
		</div>
	</form>
</div>
<%}}
if (ugl.getUser_role() == Constants.ROLE_PROFESOR || ugl.getUser_role() == Constants.ROLE_ADMIN){ %>
<div class="container">
	<legend>
		<h3>
			<button id="displayGroup2" class="btn btn-default btn-xs">
				<span id="displayGroup2i" class="glyphicon glyphicon-chevron-down"></span>
			</button>
			&nbsp;
			<fmt:message key="p1-3.2.gruposProf" />
		</h3>
	</legend>
</div>
<div id="group-block2" class="container"></div>
<%} %>
<div class="container">
	<legend>
		<h3>
			<button id="displayGroup3" class="btn btn-default btn-xs">
				<span id="displayGroup3i" class="glyphicon glyphicon-chevron-down"></span>
			</button>
			&nbsp;
			<fmt:message key="p1-3.2.gruposAlum" />
		</h3>
	</legend>
</div>
<div id="group-block3" class="container"></div>
<form action="contentManager.do" method="post" id="activeGroupForm">
	<input type="hidden" name="idGroup" id="idGroup"> <input
		type="hidden" name="groupSelected" id="groupSelected"
		value="<%=idGr%>" />
</form>
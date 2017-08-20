<%@page import="com.cvilla.medievalia.domain.Log"%>
<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%
User user = (User)request.getAttribute("usuario");
int numPags = (Integer)request.getAttribute("numPags");
%>
<div class="container">
	<legend>
		<h2>
			<fmt:message key="p1-3.2.bienvenida" />
			<%=user.getUser_long_name() %></h2>
	</legend>
</div>
<div class="container">
	<ul class="list-inline">
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-1" />: <%=user.getId() %></li>
		<li class="col-xs-2"><fmt:message key="p1-3.2.t1-2" />: <%=user.getUser_name() %></li>
		<li class="col-xs-3"><fmt:message key="p1-3.2.t1-3" />: <%=user.getUser_long_name() %></li>
		<%if(user.getUser_role() == 1){ %>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4" />: <span
			class="glyphicon glyphicon-cog"
			title="<fmt:message key="general.administrador"/>"></span></li>
		<%} 
		else if(user.getUser_role() == 2){%>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4" />: <span
			class="glyphicon glyphicon-briefcase"
			title="<fmt:message key="general.profesor"/>"></span></li>
		<%} 
		else if(user.getUser_role() == 3){%>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4" />: <span
			class="glyphicon glyphicon-user"
			title="<fmt:message key="general.alumno"/>"></span></li>
		<%}
		else if(user.getUser_role() == 4){%>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4" />: <span
			class="glyphicon glyphicon-ban-circle"
			title="<fmt:message key="general.inactivo"/>"></span></li>
		<%} %>
	</ul>
</div>
<div class="container">
	<legend>
		<h3>
			<button id="displayActivity" class="btn btn-sm btn-default btn-xs">
				<span id="displayActivityi" class="glyphicon glyphicon-chevron-down"></span>
			</button>
			&nbsp;
			<fmt:message key="p1-3.2.actividad" />
		</h3>
	</legend>
</div>
<div class="container" id="activity-block"></div>
<div class="activityButtons">
	<div class="col-md-6 col-md-offset-3">
		<ul class="pagination">
			<li><span id="primero" class="glyphicon glyphicon-fast-backward"
				title="<fmt:message key="general.paginacion.control1"/>"></span></li>
			<li><span id="anterior"
				class="glyphicon glyphicon-step-backward"
				title="<fmt:message key="general.paginacion.control2"/>"></span></li>
			<li><span id="primeros"
				class="glyphicon glyphicon-fast-backward" style="color: grey;"
				title="<fmt:message key="general.paginacion.control1"/>"></span></li>
			<li><span id="anteriores"
				class="glyphicon glyphicon-step-backward" style="color: grey;"
				title="<fmt:message key="general.paginacion.control2"/>"></span></li>
			<li><span id="pagA"></span></li>
			<li><span id="pagB"></span></li>
			<li><span id="paginaActual"><u><strong>1</strong></u></span></li>
			<li><span id="pagC">2</span></li>
			<li><span id="pagD">3</span></li>
			<li><span id="siguiente"
				class="glyphicon glyphicon-step-forward"
				title="<fmt:message key="general.paginacion.control4"/>"></span></li>
			<li><span id="ultimo" class="glyphicon glyphicon-fast-forward"
				title="<fmt:message key="general.paginacion.control5"/>"></span></li>
			<li><span id="siguientes"
				class="glyphicon glyphicon-step-forward" style="color: grey;"
				title="<fmt:message key="general.paginacion.control4"/>"></span></li>
			<li><span id="ultimos" class="glyphicon glyphicon-fast-forward"
				style="color: grey;"
				title="<fmt:message key="general.paginacion.control5"/>"></span></li>
		</ul>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/groups-list.jsp"%>
<div class="container">
	<div class="col-md-6 col-md-offset-3">
		<button type="button" class="btn btn-sm btn-default" id="volver">
			<fmt:message key="general.volver" />
		</button>
	</div>
</div>
<input type="hidden" name="detailId" id="detailId"
	value="<%=request.getAttribute("detailId")%>">
<input type="hidden" name="idUser" id="idUser"
	value="<%=request.getAttribute("detailId")%>">
<input type="hidden" name="tamPag" id="tamPag"
	value="<%=request.getAttribute("tamPag")%>">
<input type="hidden" name="numPags" id="numPags" value="<%=numPags%>">
<%@ include file="/WEB-INF/views/footer.jsp"%>
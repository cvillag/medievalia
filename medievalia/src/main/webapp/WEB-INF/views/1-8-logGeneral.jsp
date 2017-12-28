<%@page import="com.cvilla.medievalia.domain.Log"%>
<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<%
int numPags = (Integer)request.getAttribute("numPag");
%>
<div class="container">
	<legend>
		<h2>
			<fmt:message key="p1-8.bienvenida" />
		</h2>
	</legend>
</div>
<div class="container">
	<legend>
		<h3>
			<button id="displayActivity" class="btn btn-sm btn-info btn-xs" title="<fmt:message key="acc.mostrarActividad"/>">
			<span id="displayActivityi" class="glyphicon glyphicon-chevron-down"></span>
			</button>
			&nbsp;
			<fmt:message key="p1-8.actividad" />
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
<input type="hidden" name="numPags" id="numPags" value="<%=numPags%>">
<input type="hidden" name="tamPag" id="tamPag"
	value="<%=request.getAttribute("tamPag")%>">
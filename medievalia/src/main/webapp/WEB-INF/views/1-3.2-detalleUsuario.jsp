<%@page import="com.cvilla.medievalia.domain.Log"%>
<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.cvilla.medievalia.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<%
@SuppressWarnings("unchecked")
User user = (User)request.getAttribute("user");
List<Log> actividad = (List<Log>)request.getAttribute("activity");
String message = (String)request.getAttribute("message");
int pagAct = (Integer)request.getAttribute("actual");
String actual = ((new Integer(pagAct))).toString();
int maxPag = (Integer)request.getAttribute("numPags");
String numPags = (new Integer(maxPag)).toString();
boolean last = (Boolean)request.getAttribute("last");
boolean first = (Boolean)request.getAttribute("first");
%>
<div class="container">
<legend><h2><fmt:message key="p1-3.2.bienvenida"/> <%=user.getUser_long_name() %></h2></legend>
</div>
<div class="container">
	<ul class="list-inline">
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-1"/>: <%=user.getId() %></li>
		<li class="col-xs-2"><fmt:message key="p1-3.2.t1-2"/>: <%=user.getUser_name() %></li>
		<li class="col-xs-3"><fmt:message key="p1-3.2.t1-3"/>: <%=user.getUser_long_name() %></li>
		<%if(user.getUser_role() == 1){ %>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4"/>: <span class="glyphicon glyphicon-cog" title="<fmt:message key="general.administrador"/>"></span></li>
		<%} 
		else if(user.getUser_role() == 2){%>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4"/>: <span class="glyphicon glyphicon-briefcase" title="<fmt:message key="general.profesor"/>"></span></li>
		<%} 
		else if(user.getUser_role() == 3){%>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4"/>: <span class="glyphicon glyphicon-user" title="<fmt:message key="general.alumno"/>"></span></li>
		<%}
		else if(user.getUser_role() == 4){%>
		<li class="col-xs-1"><fmt:message key="p1-3.2.t1-4"/>: <span class="glyphicon glyphicon-ban-circle" title="<fmt:message key="general.inactivo"/>"></span></li>
		<%} %>
	</ul>
</div>
<div class="container">
	<legend><h3><span id="displayActivity" class="glyphicon glyphicon-chevron-down"></span><fmt:message key="p1-3.2.actividad"/></h3></legend>
</div>
<div class="container" id="activity-block">
	<table class="table table-hover table-striped table-condensed table-scrollable">
		<thead>
			<tr>
				<th class="col-xs-2"><fmt:message key="p1-3.2.t2-1"></fmt:message></th>
				<th class="col-xs-2"><fmt:message key="p1-3.2.t2-2"></fmt:message></th>
				<th class="col-xs-3"><fmt:message key="p1-3.2.t2-3"></fmt:message></th>
				<th class="col-xs-4"><fmt:message key="p1-3.2.t2-4"></fmt:message></th>
				<th class="col-xs-1"><fmt:message key="p1-3.2.t2-5"></fmt:message></th>
			</tr>
		</thead>
		<tbody>
		<% if(actividad != null && actividad.size() > 0){
			for(Log l : actividad){%>
			<tr>
				<td><%=l.getTime() %></td>
				<td><%=l.getActionName() %></td>
				<td><%=l.getUserLongName() %></td>
				<td><%=l.getDescription() %></td>
				<td><% if(l.getSuccess()==1){%><fmt:message key="p1-3.2.t2-exito"/><%}else{ %><fmt:message key="p1-3.2.t2-noexito"/><%} %></td>	
			</tr>
			<%}
			}%>
		</tbody>
	</table>
</div>
<div class="container" id="activity-block2">
	<div class="col-md-6 col-md-offset-3">
		<ul class="pagination">
		<%if (!first){ %>
			<li><span id="primero" class="glyphicon glyphicon-fast-backward" title="<fmt:message key="p1-3.2.control1"/>"></span></li>
			<li><span id="anterior" class="glyphicon glyphicon-step-backward" title="<fmt:message key="p1-3.2.control2"/>"></span></li>
		<%}else{ %>
			<li><span id="primeros" class="glyphicon glyphicon-fast-backward" style="color:grey;" title="<fmt:message key="p1-3.2.control1"/>"></span></li>
			<li><span id="anteriores" class="glyphicon glyphicon-step-backward" style="color:grey;" title="<fmt:message key="p1-3.2.control2"/>"></span></li>
		<%}	%>
			<%if(pagAct > 2){ %>
			<li><span id="pagA"><%=pagAct-2 %></span></li>
			<%} %>
			<%if(pagAct > 1){ %>
			<li><span id="pagB"><%=pagAct-1 %></span></li>
			<%} %>
			<li><span><u><strong><%=actual %></strong></u></span></li>
			<%if(pagAct < maxPag){ %>
			<li><span id="pagC"><%=pagAct+1 %></span></li>
			<%} %>
			<%if(pagAct+1 < maxPag){ %>
			<li><span id="pagD"><%=pagAct+2 %></span></li>
			<%} %>
		<%if (!last){ %>
			<li><span id="siguiente" class="glyphicon glyphicon-step-forward" title="<fmt:message key="p1-3.2.control4"/>"></span></li>
			<li><span id="ultimo" class="glyphicon glyphicon-fast-forward" title="<fmt:message key="p1-3.2.control5"/>"></span></li>
		<%}else{ %>
			<li><span id="siguiente" class="glyphicon glyphicon-step-forward" style="color:grey;" title="<fmt:message key="p1-3.2.control4"/>"></span></li>
			<li><span id="ultimo" class="glyphicon glyphicon-fast-forward" style="color:grey;" title="<fmt:message key="p1-3.2.control5"/>"></span></li>
		<%} %>
		</ul>
	</div>
</div>
<%if(message != null && message.length()>0){ %>
<div class="container" id="activity-block3">
	<%if(message.equals("p1-3.2.error.paginaNoExiste")){ %>
	<div class="alert alert-danger"><fmt:message key="p1-3.2.error.paginaNoExiste"/></div>
	<%} %>
</div>
<%} %>
<div class="container">
	<legend><h3><span id="displayGroup1" class="glyphicon glyphicon-chevron-down"></span><fmt:message key="p1-3.2.gruposDir"/></h3></legend>
</div>
<div id="group-block1" class="container">
	<p>Lorem ipsum</p>
</div>
<div class="container">
	<legend><h3><span id="displayGroup2"class="glyphicon glyphicon-chevron-down"></span><fmt:message key="p1-3.2.gruposProf"/></h3></legend>
</div>
<div id="group-block2" class="container">
	<p>Lorem ipsum</p>
</div>
<div class="container">
	<legend><h3><span id="displayGroup3" class="glyphicon glyphicon-chevron-down"></span><fmt:message key="p1-3.2.gruposAlum"/></h3></legend>
</div>
<div id="group-block3" class="container">
	<p>Lorem ipsum</p>
</div>
<div class="container">
	<div class="col-md-6 col-md-offset-3">
		<button type="button" class="btn btn-default" id="volver"><fmt:message key="general.volver"/></button>
	</div>
</div>
<form action="detailUser.do" id="pagForm" method="post">
	<input type="hidden" name="pag" id="pag" value="1">
	<input type="hidden" name="tamPag" id="tamPag" value="10">
	<input type="hidden" name="actual"	id="actual" value="<%=actual %>">
	<input type="hidden" name="detailId" id="detailId" value="<%=request.getAttribute("detailId")%>">
	<input type="hidden" name="maxPag" id="maxPag" value="<%=numPags%>">
	<input type="hidden" name="pagp2" id="pagp2" value="<%=pagAct-2%>">
	<input type="hidden" name="pagp1" id="pagp1" value="<%=pagAct-1%>">
	<input type="hidden" name="pagm1" id="pagm1" value="<%=pagAct+1%>">
	<input type="hidden" name="pagm2" id="pagm2" value="<%=pagAct+2%>">
</form>
<%@ include file="/WEB-INF/views/footer.jsp" %>
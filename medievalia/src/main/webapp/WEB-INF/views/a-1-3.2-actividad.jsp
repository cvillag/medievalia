<%@page import="com.cvilla.medievalia.domain.Log"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<%
@SuppressWarnings("unchecked")
List<Log> actividad = (List<Log>)request.getAttribute("activity");
int pagAct = (Integer)request.getAttribute("actual");
String actual = ((new Integer(pagAct))).toString();
int maxPag = (Integer)request.getAttribute("numPags");
String numPags = (new Integer(maxPag)).toString();
boolean last = (Boolean)request.getAttribute("last");
boolean first = (Boolean)request.getAttribute("first");
String message = (String)request.getAttribute("message");
%>

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
	<!--  TODO: Pasar el div entero a 1-3.2-userDetail.jsp para tener botones fijos, ocultar mediante js los necesarios -->
<%if(message != null && message.length()>0){ %>
	<%if(message.equals("p1-3.2.error.paginaNoExiste")){ %>
	<div class="alert alert-danger"><fmt:message key="p1-3.2.error.paginaNoExiste"/></div>
	<%} %>
<%} %>
<form action="activityUserA.do" id="pagForm" method="post">
	<input type="hidden" name="pag" id="pag" value="1">
	<input type="hidden" name="tamPag" id="tamPag" value="10">
	<input type="hidden" name="detailId" id="detailId" value="<%=request.getAttribute("detailId")%>">
	<input type="hidden" name="maxPag" id="maxPag" value="<%=numPags%>">
	<input type="hidden" name="pagp2" id="pagp2" value="<%=pagAct-2%>">
	<input type="hidden" name="pagp1" id="pagp1" value="<%=pagAct-1%>">
	<input type="hidden" name="pagm1" id="pagm1" value="<%=pagAct+1%>">
	<input type="hidden" name="pagm2" id="pagm2" value="<%=pagAct+2%>">
</form>
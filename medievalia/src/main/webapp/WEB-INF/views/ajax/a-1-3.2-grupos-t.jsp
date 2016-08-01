<%@page import="com.cvilla.medievalia.domain.Group"%>
<%@page import="com.cvilla.medievalia.domain.Teachers"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
HttpSession sess = request.getSession();
Group g = (Group) sess.getAttribute("grupoActual");
@SuppressWarnings("unchecked")
List<Teachers> lista = (List<Teachers>) request.getAttribute("gruposTeach");
if(lista != null && lista.size() > 0){
%>

<div class="col-lg-4">
	<table
		class="table table-hover table-striped table-condensed table-scrollable">
		<thead>
			<tr>
				<!-- TODO: Crear enlaces a detalles del grupo -->
				<th class="col-lg-2"><fmt:message key="p1-3.2.t4-1"></fmt:message></th>
				<th class="col-lg-2"><fmt:message key="p1-3.2.t4-2"></fmt:message></th>
				<th class="col-lg-2"><fmt:message key="p1-3.2.t4-3"></fmt:message></th>
				<th class="col-lg-1"><fmt:message key="p1-3.2.t4-4"></fmt:message></th>
			</tr>
		</thead>
		<tbody>
			<%
		int i = 1;
		for(Teachers t : lista){%>
			<tr>
				<td><%= i++ %></td>
				<td><%=t.getName() %></td>
				<td><%=t.getDirectorName() %></td>
				<%if (g == null || g.getIdGrupo() != t.getIdGroup()){ %>
				<td><button class="btn btn-default btn-xs selectgrp"
						data-val="<%=t.getIdGroup()%>">
						<span class="glyphicon glyphicon-play"
							id="selectgrp<%=t.getIdGroup()%>"></span>
					</button></td>
				<%}
			else{%>
				<td><button class="btn btn-default btn-xs selectedgrp"
						data-val="<%=t.getIdGroup()%>">
						<span class="glyphicon glyphicon-check"
							id="selectgrp<%=t.getIdGroup()%>"></span>
					</button></td>
				<%} %>
			</tr>
			<%}
		%>
		</tbody>
	</table>
</div>
<%}
else{
%>
<div class="alert alert-danger col-lg-4">
	<fmt:message key="p1-3.2.t3-noList" />
</div>
<%
}
%>
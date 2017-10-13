<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Group"%>
<%@page import="com.cvilla.medievalia.domain.Teachers"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
HttpSession sess = request.getSession();
Group g = (Group) sess.getAttribute("grupoActual");
User teach = (User)request.getAttribute("teacher");
@SuppressWarnings("unchecked")
List<Teachers> lista = (List<Teachers>) request.getAttribute("gruposTeach");
boolean play = (Boolean)request.getAttribute("play");
if(lista != null && lista.size() > 0){
%>

<div>
	<table
		class="table table-hover table-striped table-condensed table-scrollable">
		<thead>
			<tr>
				<th class="col-xs-1"><fmt:message key="p1-3.2.t4-1"></fmt:message></th>
				<th class="col-xs-6"><fmt:message key="p1-3.2.t4-2"></fmt:message></th>
				<th class="col-xs-4"><fmt:message key="p1-3.2.t4-3"></fmt:message></th>
				<% if(play){ %>
				<th class="col-xs-1"><fmt:message key="p1-3.2.t4-4"></fmt:message></th>
				<%} %>
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
				<%
				if(play){
					if (g == null || g.getIdGrupo() != t.getIdGroup()){ %>
				<td><button class="btn btn-sm btn-info btn-xs selectgrp selgrp"
						data-val="<%=t.getIdGroup()%>">
						<span class="glyphicon glyphicon-play"
							id="selectgrp<%=t.getIdGroup()%>"></span>
					</button></td>
					<%}
					else{%>
				<td><button class="btn btn-sm btn-info btn-xs selectedgrp selgrp"
						data-val="<%=t.getIdGroup()%>">
						<span class="glyphicon glyphicon-check"
							id="selectgrp<%=t.getIdGroup()%>"></span>
					</button></td>
				<%	}
				}%>
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
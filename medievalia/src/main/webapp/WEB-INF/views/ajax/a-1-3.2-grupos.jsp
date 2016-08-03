<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Group"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
HttpSession sess = request.getSession();
Group ag = (Group) sess.getAttribute("grupoActual");
@SuppressWarnings("unchecked")
List<Group> lista = (List<Group>) request.getAttribute("gruposDir");
User dir = (User) request.getAttribute("director");
boolean play = (Boolean)request.getAttribute("play");
if(lista != null && lista.size() > 0){
%>
<div class="col-lg-4">
	<table
		class="table table-hover table-striped table-condensed table-scrollable">
		<thead>
			<tr>
				<!-- TODO: Crear enlaces a detalles del grupo -->
				<th class="col-lg-1"><fmt:message key="p1-3.2.t3-1"></fmt:message></th>
				<th class="col-lg-10"><fmt:message key="p1-3.2.t3-2"></fmt:message></th>
				<%if(play){ %>
				<th class="col-lg-1"><fmt:message key="p1-3.2.t3-4"></fmt:message></th>
				<%} %>
			</tr>
		</thead>
		<tbody>
			<%
		int i = 1;
		for(Group g : lista){%>
			<tr>
				<td><%= i++ %></td>
				<td><%=g.getName() %></td>
				<%
				if(play){
					if(ag == null || g.getIdGrupo() != ag.getIdGrupo()){ %>
				<td><button class="btn btn-default btn-xs selectgrp"
						data-val="<%=g.getIdGrupo()%>">
						<span class="glyphicon glyphicon-play"
							id="selectgrp<%=g.getIdGrupo()%>"></span>
					</button></td>
						<%}
					else{%>
				<td><button class="btn btn-default btn-xs selectedgrp"
						data-val="<%=g.getIdGrupo()%>">
						<span class="glyphicon glyphicon-check"
							id="selectgrp<%=g.getIdGrupo()%>"></span>
					</button></td>
				<%	}
				}%>
			</tr>
			<%}
		%>
		</tbody>
	</table>
	</form>
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

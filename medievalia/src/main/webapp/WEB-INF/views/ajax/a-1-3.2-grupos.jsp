<%@page import="com.cvilla.medievalia.domain.Group"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<%
	@SuppressWarnings("unchecked")
	List<Group> lista = (List<Group>) request.getAttribute("gruposDir");
if(lista != null && lista.size() > 0){
%>
<div class="col-lg-4">
<table class="table table-hover table-striped table-condensed table-scrollable">
	<thead>
		<tr>
		<!-- TODO: Crear enlaces a detalles del grupo -->
			<th class="col-lg-2"><fmt:message key="p1-3.2.t3-1"></fmt:message></th>
			<th class="col-lg-2"><fmt:message key="p1-3.2.t3-2"></fmt:message></th>
		</tr>
	</thead>
	<tbody>
	<%
		int i = 1;
		for(Group g : lista){%>
		<tr>
			<td><%= i++ %></td>
			<td><%=g.getName() %></td>	
		</tr>
		<%}
		%>
		</tbody>
	</table>
</div>
<%}
else{
%>
<div class="alert alert-danger col-lg-4"><fmt:message key="p1-3.2.t3-noList"/></div>
<%
}
%>

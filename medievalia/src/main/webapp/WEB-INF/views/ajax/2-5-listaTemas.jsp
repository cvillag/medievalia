<%@page import="com.cvilla.medievalia.domain.Tema"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
List<Tema> lista = (List<Tema>)request.getAttribute("listaTemas");
%>
<table class="table table-hover table-striped table-condensed table-scrollable">
	<thead>
		<tr>
			<th class="col-xs-2"><fmt:message key="p2-5-t1-1"></fmt:message></th>
			<th class="col-xs-2"><fmt:message key="p2-5-t1-2"></fmt:message></th>
		</tr>
	</thead>
	<tbody>
		<%for(Tema t : lista){%>
		<tr>
			<td><%=t.getNombre() %></td>
			<td></td>
		</tr>
		<%} %>
	</tbody>
</table>
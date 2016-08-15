<%@page import="com.cvilla.medievalia.domain.SubTema"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
List<SubTema> lista = (List<SubTema>)request.getAttribute("listaSubTemas");
%>
<table class="table table-hover table-striped table-condensed table-scrollable">
	<thead>
		<tr>
			<th class="col-xs-4"><fmt:message key="p2-6-t1-1"></fmt:message></th>
			<th class="col-xs-2"><fmt:message key="p2-6-t1-2"></fmt:message></th>
		</tr>
	</thead>
	<tbody>
		<%for(SubTema t : lista){%>
		<tr>
			<td><%=t.getNombreSubtema()%></td>
			<td>
				<button type="button" class="btn btn-default topicDetail" data-val="<%=t.getIdTema()%>">
					<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
		<%} %>
	</tbody>
</table>
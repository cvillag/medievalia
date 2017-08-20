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
			<th class="col-xs-4"><fmt:message key="p2-5-t1-1"></fmt:message></th>
			<th class="col-xs-1"><fmt:message key="p2-5-t1-2"></fmt:message></th>
			<th class="col-xs-2"><fmt:message key="p2-5-t1-3"></fmt:message></th>
		</tr>
	</thead>
	<tbody>
		<%for(Tema t : lista){%>
		<tr id="row<%=t.getIdTema()%>">
			<td><%=t.getNombre() %></td>
			<td><%=t.getNumSubtemas() %></td>
			<td>
				<button type="button" class="btn btn-sm btn-default topicDetail" data-val="<%=t.getIdTema()%>">
					<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>
				</button>
				<button type="button" class="btn btn-sm btn-default deleteTopic" data-val="<%=t.getIdTema()%>">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
		<%} %>
	</tbody>
</table>
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
		<tr id="strow<%=t.getIdSubtema()%>">
			<td>
				<input type="text" id="st<%=t.getIdSubtema()%>" value="<%=t.getNombreSubtema()%>" disabled>
				<button type="button" id="saveSt<%=t.getIdSubtema()%>" class="btn btn-default saveNewName" data-val="<%=t.getIdSubtema()%>">
					<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
				</button>
				<button type="button" id="cancelSt<%=t.getIdSubtema()%>" class="btn btn-default cancelNewName" data-val="<%=t.getIdSubtema()%>">
					<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
				</button>
			</td>
			<td>
				<button type="button" class="btn btn-default activarSNombre" data-val="<%=t.getIdSubtema()%>">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				</button>
				<button type="button" class="btn btn-default deleteSTopic" data-val="<%=t.getIdSubtema()%>">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
		<%} %>
	</tbody>
</table>
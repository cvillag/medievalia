<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.Role"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%
@SuppressWarnings("unchecked")
List<User> lu = (List<User>)request.getAttribute("list");
List<Role> lr = (List<Role>)request.getAttribute("roles");
if(lu == null){%>
	<p><fmt:message key="p2-1.listaUsuarios.empty"></fmt:message>
<%}
else{%>
<p><fmt:message key="p2-1.listaUsuarios.roles"></fmt:message>:
<select class="form-control" id="roleSelected">
	<%for(Role r : lr){ 
		if(Constants.isAcceptedRoleInGroup(r.getRol())){%>
		<option value="<%=r.getRol()%>" <%if(r.getRol() == Constants.ROLE_ALUMNO){ %> selected <% } %>><%=r.getNombreRol()%></option>
		<%} %>
	<%} %>
</select></p>
<table class="table table-striped">
	<thead>
		<tr>
			<th>
				<fmt:message key="p2-1.listaUsuarios.p1"></fmt:message>
			</th>
			<th>
				<fmt:message key="p2-1.listaUsuarios.p2"></fmt:message>
			</th>
			<th>
				<fmt:message key="p2-1.listaUsuarios.p3"></fmt:message>
			</th>
		</tr>
	</thead>
	<tbody>
	<%
	for(User u : lu){ 
		if(u.getUser_role() == Constants.ROLE_PROFESOR){%>
		<tr class="profe" id="enroll<%=u.getId()%>">
		<%}else{ %>
		<tr class="alumno" id="enroll<%=u.getId()%>">
		<%} %>
			<td><%=u.getUser_long_name() %>
			</td>
			<%if(u.getUser_role() == 1){ %>
				<td><span class="glyphicon glyphicon-cog"
					title="<fmt:message key="general.administrador"/>"></span></td>
				<%} 
				else if(u.getUser_role() == 2){%>
				<td><span class="glyphicon glyphicon-briefcase"
					title="<fmt:message key="general.profesor"/>"></span></td>
				<%} 
				else if(u.getUser_role() == 3){%>
				<td><span class="glyphicon glyphicon-user"
					title="<fmt:message key="general.alumno"/>"></span></td>
				<%}
				else if(u.getUser_role() == 4){%>
				<td><span class="glyphicon glyphicon-ban-circle"
					title="<fmt:message key="general.inactivo"/>"></span></td>
			<%} %>
			<td>
				<button type="button"
					class="btn btn-sm btn-info enroll"
					data-val="<%=u.getId() %>">
					<fmt:message key="p2-1.listaUsuarios.btn"></fmt:message>
				</button>
			</td>
		</tr>
		<%} %>
	</tbody>
</table>
<%}%>
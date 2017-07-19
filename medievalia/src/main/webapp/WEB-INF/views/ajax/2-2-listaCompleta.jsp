<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Charge"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Charge> cargos = (List<Charge>) request.getAttribute("listaCargos");
@SuppressWarnings("unchecked")
String delete = (String) request.getAttribute("permisoborrado");
if (cargos != null && cargos.size() > 0){
	for(Charge c : cargos){
		if(type.equals("table")){%>
			<tr class="trcargo" id="cargo<%=c.getIdCharge()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="cargoName<%=c.getIdCharge()%>" value="<%=c.getNombre() %>" disabled>
					<button type="button" id="saveCargo<%=c.getIdCharge()%>" class="btn btn-default saveNewName" data-val="<%=c.getIdCharge()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelCargo<%=c.getIdCharge()%>" class="btn btn-default cancelNewName" data-val="<%=c.getIdCharge()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%=c.getValidado()%>
				</td>
				<td>
					<button type="button" class="btn btn-default activarSNombre" data-val="<%=c.getIdCharge()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-default deleteSCargo" data-val="<%=c.getIdCharge()%>">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<%} %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdCharge()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
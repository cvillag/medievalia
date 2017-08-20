<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Place"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Place> lugares = (List<Place>) request.getAttribute("listaLugares");
@SuppressWarnings("unchecked")
String delete = (String) request.getAttribute("permisoborrado");
@SuppressWarnings("unckecked")
String rename = (String) request.getAttribute("permisoRenombrado");
if (lugares != null && lugares.size() > 0){
	for(Place c : lugares){
		if(type.equals("table")){%>
			<tr class="trlugar" id="lugar<%=c.getIdPlace()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="lugarName<%=c.getIdPlace()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="saveLugar<%=c.getIdPlace()%>" class="btn btn-sm btn-default saveNewName" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelLugar<%=c.getIdPlace()%>" class="btn btn-sm btn-default cancelNewName" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%=c.getValidado()%>
				</td>
				<td>
					<%if(rename != null){ %>
					<button type="button" class="btn btn-sm btn-default activarSNombre" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%}
					if(delete != null){ %>
					<button type="button" class="btn btn-sm btn-default deleteSLugar" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<%} %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdPlace()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
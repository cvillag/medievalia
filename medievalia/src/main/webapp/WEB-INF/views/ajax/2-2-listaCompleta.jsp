<%@page import="com.cvilla.medievalia.domain.ObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<ObjetoDOM> objetos = (List<ObjetoDOM>) request.getAttribute("listaObjetos");
String delete = (String) request.getAttribute("permisoborrado");
String rename = (String) request.getAttribute("permisoRenombrado");
if (objetos != null && objetos.size() > 0){
	for(ObjetoDOM c : objetos){
		if(type.equals("table")){%>
			<tr class="trobjeto" id="objeto<%=c.getIdObjetoDOM()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="objetoName<%=c.getIdObjetoDOM()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="saveObjeto<%=c.getIdObjetoDOM()%>" class="btn btn-sm btn-default saveNewName" data-val="<%=c.getIdObjetoDOM()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelObjeto<%=c.getIdObjetoDOM()%>" class="btn btn-sm btn-default cancelNewName" data-val="<%=c.getIdObjetoDOM()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%=c.getValidado()%>
				</td>
				<td>
					<%if(rename != null){ %>
					<button type="button" class="btn btn-sm btn-default activarSNombre" data-val="<%=c.getIdObjetoDOM()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%}
					if(delete != null){ %>
					<button type="button" class="btn btn-sm btn-default deleteSObjeto" data-val="<%=c.getIdObjetoDOM()%>">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<%} %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdObjetoDOM()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
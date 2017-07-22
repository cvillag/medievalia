<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Study"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Study> estudios = (List<Study>) request.getAttribute("listaEstudios");
@SuppressWarnings("unchecked")
String delete = (String) request.getAttribute("permisoborrado");
if (estudios != null && estudios.size() > 0){
	for(Study c : estudios){
		if(type.equals("table")){%>
			<tr class="trestudio" id="estudio<%=c.getIdStudy()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="estudioName<%=c.getIdStudy()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="saveEstudio<%=c.getIdStudy()%>" class="btn btn-default saveNewName" data-val="<%=c.getIdStudy()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelEstudio<%=c.getIdStudy()%>" class="btn btn-default cancelNewName" data-val="<%=c.getIdStudy()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%=c.getValidado()%>
				</td>
				<td>
					<button type="button" class="btn btn-default activarSNombre" data-val="<%=c.getIdStudy()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-default deleteSEstudio" data-val="<%=c.getIdStudy()%>">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<%} %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdStudy()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
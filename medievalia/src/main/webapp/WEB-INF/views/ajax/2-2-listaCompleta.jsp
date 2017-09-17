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
			<tr class="trobjeto" id="objeto<%=c.getIdInstancia()%>" data-nom="<%=c.getNombre()%>">
				<td>
<%-- 					<%if(c.isValidado()){ %> --%>
<%-- 						<span class="label label-success"><fmt:message key="general.validado"></fmt:message></span> --%>
<%-- 					<%}else{ %> --%>
<%-- 						<span class="label label-warning"><fmt:message key="general.novalidado"></fmt:message></span> --%>
<%-- 					<%} %> --%>
					<input type="text" id="objetoName<%=c.getIdInstancia()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="saveObjeto<%=c.getIdInstancia()%>" class="btn btn-sm btn-default saveNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelObjeto<%=c.getIdInstancia()%>" class="btn btn-sm btn-default cancelNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<button type="button" class="btn btn-sm btn-default detalleObjeto" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
					<%if(rename != null){ %>
					<button type="button" class="btn btn-sm btn-default activarSNombre" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%}
					if(delete != null){ %>
					<button type="button" class="btn btn-sm btn-default deleteSObjeto" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<%} %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdInstancia()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
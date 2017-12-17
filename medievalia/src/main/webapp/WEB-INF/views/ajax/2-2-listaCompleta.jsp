<%@page import="com.cvilla.medievalia.domain.InstanciaObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<InstanciaObjetoDOM> objetos = (List<InstanciaObjetoDOM>) request.getAttribute("listaObjetos");
String delete = (String) request.getAttribute("permisoborrado");
String rename = (String) request.getAttribute("permisoRenombrado");
String modify = (String) request.getAttribute("permisoModificar");

int i = 0;
if (objetos != null && objetos.size() > 0){
	for(InstanciaObjetoDOM c : objetos){
		if(type.equals("table")){%>
			<tr class="trobjeto" id="objeto<%=c.getIdInstancia()%>" data-nom="<%=c.getNombre()%>">
				<td>
<%-- 					<%if(c.isValidado()){ %> --%>
<%-- 						<span class="label label-success"><fmt:message key="general.validado"></fmt:message></span> --%>
<%-- 					<%}else{ %> --%>
<%-- 						<span class="label label-warning"><fmt:message key="general.novalidado"></fmt:message></span> --%>
<%-- 					<%} %> --%>
					<input type="text" id="objetoName<%=c.getIdInstancia()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="saveObjeto<%=c.getIdInstancia()%>" class="btn btn-xs btn-info saveNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelObjeto<%=c.getIdInstancia()%>" class="btn btn-xs btn-info cancelNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<div class="btn-group">
						<button type="button" class="btn btn-xs btn-info detalleObjeto" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>">
							<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
						</button>
						<div class="btn-group">
							<button class="btn btn-info btn-xs dropdown-toggle" type="button" id="dropdownAcciones" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownAcciones" role="menu">
								<%if(rename != null){ %>
								<li class="activarSNombre" data-val="<%=c.getIdInstancia()%>"><fmt:message key="p2.2.objetos.acciones.cnombre"></fmt:message></li>
								<%} 
								if(modify != null){ %>
								<li class="modifySObjeto" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>"><fmt:message key="p2.2.objetos.acciones.modify"></fmt:message></li>
								<%} 
								if(delete != null){ %>
								<li role="separator" class="divider"></li>
								<li class="deleteSObjeto" data-val="<%=c.getIdInstancia()%>"><fmt:message key="p2.2.objetos.acciones.delete"></fmt:message></li>
								<%} %>
							</ul>
						</div>
					</div>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			if(i++ == 0){
				%>
				<option value="0" selected></option>
				<%
			}
			%>
			<option value="<%=c.getIdInstancia()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Personage"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Personage> personajes = (List<Personage>) request.getAttribute("listaPersonajes");
@SuppressWarnings("unchecked")
String delete = (String) request.getAttribute("permisoborrado");
@SuppressWarnings("unckecked")
String rename = (String) request.getAttribute("permisoRenombrado");
if (personajes != null && personajes.size() > 0){
	for(Personage c : personajes){
		if(type.equals("table")){%>
			<tr class="trpersonaje" id="personaje<%=c.getIdPersonaje()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="personajeName<%=c.getIdPersonaje()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="savePersonaje<%=c.getIdPersonaje()%>" class="btn btn-sm btn-default saveNewName" data-val="<%=c.getIdPersonaje()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelPersonaje<%=c.getIdPersonaje()%>" class="btn btn-sm btn-default cancelNewName" data-val="<%=c.getIdPersonaje()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%=c.getValidado()%>
				</td>
				<td>
					<div class="row">
						<div class="col-xs-6">
							<div class="dropdown">
								<button class="btn btn-sm bnt-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" data-val="<%=c.getIdPersonaje()%>" id="otheract<%=c.getIdPersonaje()%>">
								<fmt:message key="p2.8.personajes.otraaccion"></fmt:message>
								 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" aria-labelledby="otheract<%=c.getIdPersonaje()%>">
								    <li><a class="oacdatos" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.cdatos"></fmt:message></a></li>
								    <li><a class="oaclnac" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.clnac"></fmt:message></a></li>
								    <li><a class="oaclfal" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.clfal"></fmt:message></a></li>
								    <li><a class="oacest" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.cest"></fmt:message></a></li>
								    <li><a class="oaccarg" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.ccarg"></fmt:message></a></li>
								  </ul>
							</div>
						</div>
						<%if(rename != null){ %>
						<div class="col-xs-2">
							<button type="button" class="btn btn-sm btn-default activarSNombre" data-val="<%=c.getIdPersonaje()%>">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</button>
						</div>
						<%}
						if(delete != null){ %>
						<div class="col-xs-2">
							<button type="button" class="btn btn-sm btn-default deleteSPersonaje" data-val="<%=c.getIdPersonaje()%>">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button>
						</div>
						<%} %>
					</div>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdPersonaje()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
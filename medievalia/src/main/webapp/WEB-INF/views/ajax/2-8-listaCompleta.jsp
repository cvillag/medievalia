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
@SuppressWarnings("unckecked")
String modify = (String) request.getAttribute("permisoModificado");

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
						<div class="col-xs-2">
							<div class="dropdown">
								<button class="btn btn-sm bnt-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" data-val="<%=c.getIdPersonaje()%>" id="otheract<%=c.getIdPersonaje()%>">
								<fmt:message key="p2.8.personajes.otraaccion"></fmt:message>
								 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" aria-labelledby="otheract<%=c.getIdPersonaje()%>">
									<%if(rename != null){ %>
									<li><a class="activarSNombre" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.cnom"></fmt:message></a></li>
									<%}
									if(delete != null){ %>
									<li><a class="deleteSPersonaje" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.delete"></fmt:message></a></li>
									<%} %>
									<li><a class="oacdatos" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.vdatos"></fmt:message></a></li>
									<%
									if(modify != null){
									%>
								    <li><a class="oacdatos" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.cdatos"></fmt:message></a></li>
								    <li><a class="oaclnac" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.clnac"></fmt:message></a></li>
								    <li><a class="oaclfal" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.clfal"></fmt:message></a></li>
								    <li><a class="oacest" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.cest"></fmt:message></a></li>
								    <li><a class="oaccarg" data-val="<%=c.getIdPersonaje()%>"><fmt:message key="p2.8.personajes.ccarg"></fmt:message></a></li>
								    <%} %>
								  </ul>
							</div>
						</div>
					</div>
				</td>
			</tr>
<%		
			for(Personage c2 : personajes){
				%>
				<input type="hidden" id="pnom<%=c2.getIdPersonaje() %>" value="<%=c2.getNombre()%>">
				<input type="hidden" id="pafal<%=c2.getIdPersonaje() %>" value="<%=c2.getAfallecimiento()%>">
				<input type="hidden" id="panac<%=c2.getIdPersonaje() %>" value="<%=c2.getAnacimiento()%>">
				<input type="hidden" id="pcreat<%=c2.getIdPersonaje() %>" value="<%=c2.getCreador()%>">
				<input type="hidden" id="pdfal<%=c2.getIdPersonaje() %>" value="<%=c2.getDfallecimiento()%>">
				<input type="hidden" id="pdnac<%=c2.getIdPersonaje() %>" value="<%=c2.getDnacimiento()%>">
				<input type="hidden" id="pmfal<%=c2.getIdPersonaje() %>" value="<%=c2.getMfallecimiento()%>">
				<input type="hidden" id="pmnac<%=c2.getIdPersonaje() %>" value="<%=c2.getMnacimiento()%>">
				<input type="hidden" id="potros<%=c2.getIdPersonaje() %>" value="<%=c2.getOtros()%>">
				<%
			}
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdPersonaje()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Place"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="textVal" value="<fmt:message key="general.validado"></fmt:message>">
<input type="hidden" id="textNVal" value="<fmt:message key="general.novalidado"></fmt:message>">
<%
@SuppressWarnings("unchecked")
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Place> lugares = (List<Place>) request.getAttribute("listaLugares");
@SuppressWarnings("unchecked")
String delete = (String) request.getAttribute("permisoborrado");
@SuppressWarnings("unchecked")
String valid = (String) request.getAttribute("permisovalidar");
if (lugares != null && lugares.size() > 0){
	for(Place c : lugares){
		if(type.equals("table")){%>
			<tr class="trlugarP" id="lugarProfe<%=c.getIdPlace()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="lugarProfeName<%=c.getIdPlace()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveProfeLugar<%=c.getIdPlace()%>" class="btn btn-sm btn-default saveProfeNewName" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelProfeLugar<%=c.getIdPlace()%>" class="btn btn-sm btn-default cancelProfeNewName" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%if(c.getValidado() == Constants.OBJETO_VALIDADO){ %>
					<span id="validado<%=c.getIdPlace() %>" class="label label-success"><fmt:message key="general.validado"></fmt:message></span>
					<%}
					else{
						if(c.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>
					
					<span id="validado<%=c.getIdPlace() %>" class="label label-warning"><fmt:message key="general.novalidado"></fmt:message></span>
						<%}else{ %>
					<span id="validado<%=c.getIdPlace() %>" class="label label-danger"><fmt:message key="general.denegado"></fmt:message></span>
					<%	}
					}%>
				</td>
				<td>
					<p><%=c.getNameCreator() %></p>
				</td>
				<td>
					
					<%if(valid != null){%>
					<button type="button" id="validarProfeLugar<%=c.getIdPlace()%>" class="btn btn-sm btn-default validarLugar" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
					</button>
					<%} %>
					<button type="button" class="btn btn-sm btn-default activarProfeSNombre" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-sm btn-default deleteProfeSLugar" data-val="<%=c.getIdPlace()%>">
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
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
if (lugares != null && lugares.size() > 0){
	for(Place c : lugares){
		if(type.equals("table")){%>
			<tr class="trlugarU" id="lugarStudent<%=c.getIdPlace()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="lugarStudentName<%=c.getIdPlace()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveStudentLugar<%=c.getIdPlace()%>" class="btn btn-default saveStudentNewName" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelStudentLugar<%=c.getIdPlace()%>" class="btn btn-default cancelStudentNewName" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%if(c.getValidado() == Constants.OBJETO_VALIDADO){ %>
					<span class="label label-success"><fmt:message key="general.validado"></fmt:message></span>
					<%}
					else{
						if(c.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>
					
					<span class="label label-warning"><fmt:message key="general.novalidado"></fmt:message></span>
						<%}else{ %>
					<span class="label label-danger"><fmt:message key="general.denegado"></fmt:message></span>
					<%	}
					}%>
				</td>
				<td>
					<button type="button" class="btn btn-default activarStudentSNombre" data-val="<%=c.getIdPlace()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-default deleteStudentSLugar" data-val="<%=c.getIdPlace()%>">
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
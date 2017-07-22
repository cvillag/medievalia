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
			<tr class="trestudioU" id="estudioStudent<%=c.getIdStudy()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="estudioStudentName<%=c.getIdStudy()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveStudentEstudio<%=c.getIdStudy()%>" class="btn btn-default saveStudentNewName" data-val="<%=c.getIdStudy()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelStudentEstudio<%=c.getIdStudy()%>" class="btn btn-default cancelStudentNewName" data-val="<%=c.getIdStudy()%>">
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
					<button type="button" class="btn btn-default activarStudentSNombre" data-val="<%=c.getIdStudy()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-default deleteStudentSEstudio" data-val="<%=c.getIdStudy()%>">
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
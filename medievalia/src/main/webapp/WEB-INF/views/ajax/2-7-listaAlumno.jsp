<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.Author"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
@SuppressWarnings("unchecked")
User user = (User) session.getAttribute("user");
@SuppressWarnings("unchecked")
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<Author> autores = (List<Author>) request.getAttribute("listaAutores");
@SuppressWarnings("unchecked")
String delete = (String) request.getAttribute("permisoborrado");
if (autores != null && autores.size() > 0){
	for(Author c : autores){
		if(type.equals("table")){%>
			<tr class="trautorU" id="autorStudent<%=c.getIdAuthor()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="autorStudentName<%=c.getIdAuthor()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveStudentAutor<%=c.getIdAuthor()%>" class="btn btn-sm btn-default saveStudentNewName" data-val="<%=c.getIdAuthor()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelStudentAutor<%=c.getIdAuthor()%>" class="btn btn-sm btn-default cancelStudentNewName" data-val="<%=c.getIdAuthor()%>">
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
					<button type="button" class="btn btn-sm btn-default activarStudentSNombre" data-val="<%=c.getIdAuthor()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-sm btn-default deleteStudentSAutor" data-val="<%=c.getIdAuthor()%>">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
					<%} %>
				</td>
			</tr>
<%		
		}
		else if(type.equals("select")){
			%>
			<option value="<%=c.getIdAuthor()%>"><%=c.getNombre() %></option>
			<%
		}
	}
} %>
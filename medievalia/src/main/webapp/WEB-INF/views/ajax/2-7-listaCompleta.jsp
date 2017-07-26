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
@SuppressWarnings("unckecked")
String rename = (String) request.getAttribute("permisoRenombrado");
if (autores != null && autores.size() > 0){
	for(Author c : autores){
		if(type.equals("table")){%>
			<tr class="trautor" id="autor<%=c.getIdAuthor()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="autorName<%=c.getIdAuthor()%>" value="<%=c.getNombre() %>" disabled class="nombreCom">
					<button type="button" id="saveAutor<%=c.getIdAuthor()%>" class="btn btn-default saveNewName" data-val="<%=c.getIdAuthor()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelAutor<%=c.getIdAuthor()%>" class="btn btn-default cancelNewName" data-val="<%=c.getIdAuthor()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%=c.getValidado()%>
				</td>
				<td>
					<%if(rename != null){ %>
					<button type="button" class="btn btn-default activarSNombre" data-val="<%=c.getIdAuthor()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%}
					if(delete != null){ %>
					<button type="button" class="btn btn-default deleteSAutor" data-val="<%=c.getIdAuthor()%>">
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
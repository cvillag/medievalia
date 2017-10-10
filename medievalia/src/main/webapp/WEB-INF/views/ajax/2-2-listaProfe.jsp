<%@page import="com.cvilla.medievalia.domain.InstanciaObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<input type="hidden" id="textVal" value="<fmt:message key="general.validado"></fmt:message>">
<input type="hidden" id="textNVal" value="<fmt:message key="general.novalidado"></fmt:message>">
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("type");
// @SuppressWarnings("unchecked")
// List<Charge> cargos = (List<Charge>) request.getAttribute("listaCargos");
String delete = (String) request.getAttribute("permisoborrado");
String valid = (String) request.getAttribute("permisovalidar");
@SuppressWarnings("unchecked")
List<InstanciaObjetoDOM> lista = (List<InstanciaObjetoDOM>) request.getAttribute("listaObjetos");
 if (lista != null && lista.size() > 0){
 	for(InstanciaObjetoDOM c : lista){
		if(type.equals("table")){%>
			<tr class="trObjetoP" id="objetoProfe<%=c.getIdInstancia()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="objetoProfeName<%=c.getIdInstancia()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveProfeObjeto<%=c.getIdInstancia()%>" class="btn btn-sm btn-default saveProfeNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelProfeObjeto<%=c.getIdInstancia()%>" class="btn btn-sm btn-default cancelProfeNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%if(c.getValidado() == Constants.OBJETO_VALIDADO){ %>
					<span id="validado<%=c.getIdInstancia() %>" class="label label-success"><fmt:message key="general.validado"></fmt:message></span>
					<%}
					else{
 						if(c.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>
					
					<span id="validado<%=c.getIdInstancia() %>" class="label label-warning"><fmt:message key="general.novalidado"></fmt:message></span>
						<%}else{ %>
					<span id="validado<%=c.getIdInstancia() %>" class="label label-danger"><fmt:message key="general.denegado"></fmt:message></span>
					<%	}
 					}%>
				</td>
				<td>
					<p><%=c.getCreador().getUser_long_name() %></p>
				</td>
				<td>
					
					<%if(valid != null){%>
					<button type="button" id="validarProfeObjeto<%=c.getIdInstancia()%>" class="btn btn-sm btn-default validarObjeto" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
					</button>
					<%} %>
					<button type="button" class="btn btn-sm btn-default activarProfeSNombre" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					</button>
					<%if(delete != null){ %>
					<button type="button" class="btn btn-sm btn-default deleteProfeSObjeto" data-val="<%=c.getIdInstancia()%>">
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
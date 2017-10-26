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
String rename = (String) request.getAttribute("permisorenombrar");
String modify = (String) request.getAttribute("permisomodificar");
@SuppressWarnings("unchecked")
List<InstanciaObjetoDOM> lista = (List<InstanciaObjetoDOM>) request.getAttribute("listaObjetos");
 if (lista != null && lista.size() > 0){
 	for(InstanciaObjetoDOM c : lista){
		if(type.equals("table")){%>
			<tr class="trObjetoP" id="objetoProfe<%=c.getIdInstancia()%>" data-nom="<%=c.getNombre()%>">
				<td rowspan="2">
					<input type="text" id="objetoProfeName<%=c.getIdInstancia()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveProfeObjeto<%=c.getIdInstancia()%>" class="btn btn-xs btn-info saveProfeNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelProfeObjeto<%=c.getIdInstancia()%>" class="btn btn-xs btn-info cancelProfeNewName" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<p><%=c.getCreador().getUser_long_name() %></p>
				</td>
				<td rowspan="2">
					<div class="btn-group">
						<button type="button" class="btn btn-xs btn-info detalleObjetoProfe" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>">
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
								<li class="modifyObjetoP" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>"><fmt:message key="p2.2.objetos.acciones.modify"></fmt:message></li>
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
			<tr>
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
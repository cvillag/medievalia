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
					<input type="text" id="objetoNameP<%=c.getIdInstancia()%>" value="<%=c.getNombre() %>" disabled class="nombreP">
					<button type="button" id="saveObjetoP<%=c.getIdInstancia()%>" class="btn btn-xs btn-info saveNewNameP" data-val="<%=c.getIdInstancia()%>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelObjetoP<%=c.getIdInstancia()%>" class="btn btn-xs btn-info cancelNewNameP" data-val="<%=c.getIdInstancia()%>">
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
							<button class="btn btn-info btn-xs dropdown-toggle" type="button" id="dropdownAcciones<%=c.getIdInstancia()%>" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownAcciones<%=c.getIdInstancia()%>" role="menu">
								<%if(rename != null){ %>
								<li class="activarNombreProfe" data-val="<%=c.getIdInstancia()%>"><fmt:message key="p2.2.objetos.acciones.cnombre"></fmt:message></li>
								<%} 
								if(modify != null){ %>
								<li class="modifyObjetoP" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>"><fmt:message key="p2.2.objetos.acciones.modify"></fmt:message></li>
								<%} 
								if(delete != null){ %>
								<li role="separator" class="divider"></li>
								<li class="deleteObjetoP" data-val="<%=c.getIdInstancia()%>"><fmt:message key="p2.2.objetos.acciones.delete"></fmt:message></li>
								<%} %>
							</ul>
						</div>
					</div>
				</td>
			</tr>
			<tr class="trObjetoP" data-nom="<%=c.getNombre()%>">
				<td>
					<%if(c.getValidado() == Constants.OBJETO_VALIDADO){ %>
					<span id="validado<%=c.getIdInstancia() %>" class="label label-success  <%=c.getTextoValidacion().length()>0?"textoValidaOB":""%>" data-textVal="<%=c.getTextoValidacion()%>"><fmt:message key="general.validado"></fmt:message>
					<%if(!c.isTextoLeido()){ %>
					<span class="label label-info">1</span>
					<%} %>
					</span>
					<%}
					else{
 						if(c.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>
					
					<span id="validado<%=c.getIdInstancia() %>" class="label label-warning <%=c.getTextoValidacion().length()>0?"textoNoValidaOB":""%>" data-textVal="<%=c.getTextoValidacion()%>"><fmt:message key="general.novalidado"></fmt:message>
					<%if(!c.isTextoLeido()){ %>
					<span class="label label-info">1</span>
					<%} %>
					</span>
						<%}else{ %>
					<span id="validado<%=c.getIdInstancia() %>" class="label label-danger"><fmt:message key="general.denegado"></fmt:message></span>
						<%}
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
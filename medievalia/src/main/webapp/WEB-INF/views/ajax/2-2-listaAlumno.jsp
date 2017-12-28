<%@page import="com.cvilla.medievalia.domain.TipoObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaObjetoDOM"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("type");
@SuppressWarnings("unchecked")
List<InstanciaObjetoDOM> objetos = (List<InstanciaObjetoDOM>) request.getAttribute("listaObjetos");
String delete = (String) request.getAttribute("permisoborrado");
String modify = (String) request.getAttribute("permisomodificar");
String rename = (String) request.getAttribute("permisorenombrar");

if (objetos != null && objetos.size() > 0){
	for(InstanciaObjetoDOM c : objetos){
		if(type.equals("table")){%>
			<tr class="trobjetoU" id="objetoStudent<%=c.getIdInstancia()%>" data-nom="<%=c.getNombre()%>">
				<td>
					<input type="text" id="objetoStudentName<%=c.getIdInstancia()%>" value="<%=c.getNombre() %>" disabled class="nombreAl">
					<button type="button" id="saveStudentObjeto<%=c.getIdInstancia()%>" class="btn btn-xs btn-info saveStudentNewName" data-val="<%=c.getIdInstancia()%>" title="<fmt:message key="acc.2-2.btn.savenewname"></fmt:message>">
						<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
					</button>
					<button type="button" id="cancelStudentObjeto<%=c.getIdInstancia()%>" class="btn btn-xs btn-info cancelStudentNewName" data-val="<%=c.getIdInstancia()%>" title="<fmt:message key="acc.2-2.btn.cancelnewname"></fmt:message>">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</button>
				</td>
				<td>
					<%if(c.isValidado()){ %>
					<span id="validado<%=c.getIdInstancia() %>" class="label label-success  <%=c.getTextoValidacion().length()>0?"textoValidaOBA":""%>" data-textval="<%=c.getTextoValidacion()%>" data-idtextval="<%=c.getIdInstancia()%>" data-leido="<%=c.isTextoLeido()?"1":"0"%>"><fmt:message key="general.validado"></fmt:message>
					<%if(!c.isTextoLeido()){ %>
					<span class="label label-info">1</span>
					<%} %>
					</span>
					<%}
					else{
 						if(c.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>
					
							<span id="validado<%=c.getIdInstancia() %>" class="label label-warning <%=c.getTextoValidacion().length()>0?"textoNoValidaOBA":""%>" data-textval="<%=c.getTextoValidacion()%>" data-idtextval="<%=c.getIdInstancia()%>" data-leido="<%=c.isTextoLeido()?"1":"0"%>"><fmt:message key="general.novalidado"></fmt:message>
							<%if(!c.isTextoLeido()){ %>
							<span class="label label-info">1</span>
							<%} %>
							</span>
						<%}else{ %>
					<span class="label label-danger"><fmt:message key="general.denegado"></fmt:message></span>
					<%	}
 					}%>
				</td>
				<td>
					<div class="btn-group">
						<button type="button" class="btn btn-xs btn-info detalleObjetoStudent" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>" title="<fmt:message key="acc.2-2.btn.objectdetail"></fmt:message><%=c.getNombre()%>">
							<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
						</button>
						<div class="btn-group">
							<button class="btn btn-info btn-xs dropdown-toggle" type="button" id="dropdownAccionesS<%=c.getIdInstancia()%>" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" title="<fmt:message key="acc.2-2.btn.objectactions"></fmt:message><%=c.getNombre()%>">
								<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownAccionesS<%=c.getIdInstancia()%>" role="menu">
								<%if(rename != null && c.getCreador().getId() == user.getId()){ %>
								<li class="activarStudentSNombre" data-val="<%=c.getIdInstancia()%>"><fmt:message key="p2.2.objetos.acciones.cnombre"></fmt:message></li>
								<%} 
								if(modify != null){ %>
								<li class="modifyObjetoS" data-val="<%=c.getIdInstancia()%>" data-name="<%=c.getNombre()%>" data-validado="<%=c.isValidado()?"1":"0"%>"><fmt:message key="p2.2.objetos.acciones.modify"></fmt:message></li>
								<%} 
								if(delete != null && user.getId() == c.getCreador().getId() && !c.isValidado()){ %>
								<li role="separator" class="divider"></li>
								<li class="deleteObjetoS" data-val="<%=c.getIdInstancia()%>"><fmt:message key="p2.2.objetos.acciones.delete"></fmt:message></li>
								<%} %>
							</ul>
						</div>
					</div>
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
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.cvilla.medievalia.domain.TipoAtributoComplejoDOM"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaAtributoComplejoDOM"%>
<%@page import="com.cvilla.medievalia.utils.SpecialDate"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaAtributoSencilloDOM"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaObjetoDOM"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("message");
InstanciaObjetoDOM objeto = (InstanciaObjetoDOM) request.getAttribute("object");
@SuppressWarnings("unchecked")
List<User> listU = (List<User>) request.getAttribute("users");
int modo = ((Integer) request.getAttribute("modo")).intValue();
boolean disabled = modo == 1;
@SuppressWarnings("unchecked")
List<TipoAtributoComplejoDOM> listaTipos = (List<TipoAtributoComplejoDOM>) request.getAttribute("tatributoc");
@SuppressWarnings("unchecked")
Map<Integer, Integer> badges = (Map<Integer, Integer>) request.getAttribute("badges");
int totalbadges = 0;
if(objeto != null){
	List<InstanciaAtributoComplejoDOM> acl = objeto.getAtributosComplejos();
	%>
	<input type="hidden" id="objetodetallevalidado" value="<%=objeto.isValidado()?"1":"0"%>">
	<input type="hidden" id="idObjetoValidar" value="<%=objeto.getIdInstancia()%>">
	<ul class="nav nav-tabs">
		<li class="active listaA" data-val="0"><a href="#"><fmt:message key="p2.2.detalle.atributos"></fmt:message></a></li>
		<%
		if(listaTipos != null && listaTipos.size() > 0){
			
			for(TipoAtributoComplejoDOM ac : listaTipos){
				int badge = 0;
				if(badges != null){
					if(badges.containsKey(ac.getIdTipoHijo())){
						badge = badges.get(ac.getIdTipoHijo());
						totalbadges += badge;
					}
				}
			%>
		<li class="listaA" data-val="<%=ac.getIdTipoHijo()%>"><a href="#"><%=ac.getNombreAtributo() %>&nbsp;<span class="label <%=badge==0?"label-success":"label-warning"%>"><%=badge %></span></a></li>
		<%	}
		}%>
	</ul>
	<input type="hidden" id="validateButtonFlag" value="<%=totalbadges%>">
	<input type="hidden" id="modoConsulta" value="<%=modo%>">
	<div id="modDetAtributos0">
	<%
	
	List<InstanciaAtributoSencilloDOM> atrS = objeto.getAtributosSencillos();
	if(atrS != null && atrS.size() > 0){
		%>
		
			<form>
			<%
			for(InstanciaAtributoSencilloDOM as : atrS){
				if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_FECHA){
					boolean empty = as.getValor() == null;
					Integer d = null,m = null,a = null;
					if(!empty){
						d = ((SpecialDate)as.getValor()).getDia();
						m = ((SpecialDate)as.getValor()).getMes();
						a = ((SpecialDate)as.getValor()).getAnio();
					}
					%>
					<div class="row">
						<div class="col-xs-12">
							<label><%=as.getNombreTipoAtributo() %></label>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-3 form-group">
							<label for="dia"><fmt:message key="p2.2.detalle.dia"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="dia<%=as.getIdAtributo() %>" value="<%=empty || d == null?"":d%>" size="2" min="1" max="31" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="mes"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="mes<%=as.getIdAtributo() %>" value="<%=empty || m == null?"":m%>" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="anio"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="anio<%=as.getIdAtributo() %>" value="<%=empty || a == null?"":a%>"  size="2" class="inputYears form-control"	>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_DOUBLE){
					boolean nulo = as.getValor() == null;
					String d = "";
					if(!nulo)
						d = ((Double)as.getValor()).toString();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="double"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" <%=disabled?"disabled":"" %> id="double<%=as.getIdAtributo() %>" value="<%=d%>"
							 	class="form-control">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_INT){
					boolean nulo = as.getValor() == null;
					int i=0;
					if(!nulo)
						i = ((Integer)as.getValor()).intValue();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="int"><%=as.getNombreTipoAtributo() %></label>
							<input type="number" <%=disabled?"disabled":"" %> id="int<%=as.getIdAtributo() %>" value="<%if(!nulo){ %><%=i%><%} %>" class="form-control">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_STRING){
					String s = (String)as.getValor();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="st"><%=as.getNombreTipoAtributo() %></label>
							<input type="text" <%=disabled?"disabled":"" %> id="string<%=as.getIdAtributo() %>" value="<%=s%>" class="form-control">
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_TEXT){
					String s = (String)as.getValor();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="st"><%=as.getNombreTipoAtributo() %></label>
							<textarea <%=disabled?"disabled":"" %> id="text<%=as.getIdAtributo() %>" class="form-control"><%=s%></textarea>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_OBJECT){
					InstanciaObjetoDOM o = (InstanciaObjetoDOM) as.getValor();
					%>
					<div class="row">
						<div class="col-xs-12 form-group">
							<label for="st"><%=as.getNombreTipoAtributo() %></label>
							<input type="text" <%=disabled?"disabled":"" %> id="string<%=as.getIdAtributo() %>" value="<%=o!=null?o.getNombre():""%>" class="form-control">
						</div>
					</div>
					<%
				}
			}
		%>
			</form>
	<%
	}
	else{
		%>
		<div class="row">
			<div class="col-xs-12">
				<fmt:message key="p2.2.detalle.vacio"></fmt:message>
			</div>
		</div>
		<%
	}
	%>
	</div>
	<%if(listaTipos != null && listaTipos.size() > 0){
		int idPag = 0;
		for(TipoAtributoComplejoDOM act : listaTipos){
			idPag++;	
			%>
	<div id="modDetAtributos<%=act.getIdTipoHijo() %>" class="modDetAtributosC">
		<ul class="list-group">
		<%	for(InstanciaAtributoComplejoDOM ac : acl){
				if(ac.getTipoHijo().getTipoDOM() == act.getIdTipoHijo()){
			%>
			<li class="list-group-item clearfix">
				<div class="row">
					<div class="col-xs-4">
						<p ><%=ac.getInstanciaHijo().getNombre() %></p>
					</div>
					<div class="col-xs-4">
						<p><%for(User u : listU){
							if(ac.getCreador() == u.getId()){
								%><%=u.getUser_long_name()%><%
								break;
							}
						}
						%></p>
					</div>
					<div class="col-xs-4">
						<span class="pull-right">
						<%if(ac.getValidado() == Constants.OBJETO_VALIDADO){ %>
						<span id="validado<%=ac.getTipoHijo().getTipoDOM() %>-<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-success <%=(ac.getTextoValidacion().length() > 1)?"validationText":""%>" data-textvalidacion="<%=ac.getTextoValidacion()%>"><fmt:message key="general.validado"></fmt:message><%if(!ac.isTextoLeido()){ %><span class="label label-info">1</span> <%} %></span>
							<%}
							else{
								if(ac.getValidado() == Constants.OBJETO_NO_VALIDADO){ %>
						<button type="button" id="validarProfeAtributoC<%=ac.getTipoHijo().getTipoDOM() %>-<%=ac.getInstanciaHijo().getIdInstancia() %>" class="btn btn-xs btn-info validarAtributoC" data-val="<%=ac.getInstanciaHijo().getIdInstancia()%>" data-thijo="<%=ac.getTipoHijo().getTipoDOM() %>" data-padre="<%=objeto.getIdInstancia()%>" data-textrel="<%if(ac.getIdTipoObjetoRelacion() != null && ac.getIdTipoObjetoRelacion() != 0){%><fmt:message key="p2-2.modal801"></fmt:message><%=ac.getInstanciaObjetoRelacion()!=null?ac.getInstanciaObjetoRelacion().getNombre():"(sin relación)"%><%}else{%> (sin relación)<%}%>">
							<span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
						</button>					
						<span id="validado<%=ac.getTipoHijo().getTipoDOM() %>-<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-warning <%=(ac.getTextoValidacion().length() > 1)?"novalidationText":""%>" data-textvalidacion="<%=ac.getTextoValidacion()%>"><fmt:message key="general.novalidado"></fmt:message><%if(!ac.isTextoLeido()){ %><span class="label label-info">1</span> <%} %></span>
						
							<%}else{ %>
						<span id="validado<%=ac.getTipoHijo().getTipoDOM() %>-<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-danger <%=(ac.getTextoValidacion().length() > 1)?"denegationText":""%>" data-textvalidacion="<%=ac.getTextoValidacion()%>"><fmt:message key="general.denegado"></fmt:message></span>
						<%	}
						}%>
						</span>
					</div>
				</div>	
			</li>
		<% 		}
			} %>
		</ul>
	</div>
	
	<%
		}
	}
}
else{
	%>
	<div class="alert alert-danger">
		<fmt:message key="p2.2.objetos.error.noExiste" ></fmt:message>
	</div>
	<%
}
%>
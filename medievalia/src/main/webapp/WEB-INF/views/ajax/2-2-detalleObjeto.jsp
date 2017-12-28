<%@page import="com.cvilla.medievalia.dao.mappers.ObjetoDOMMapper"%>
<%@page import="com.cvilla.medievalia.domain.TipoAtributoComplejo"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaAtributoComplejo"%>
<%@page import="com.cvilla.medievalia.utils.SpecialDate"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaAtributoSencillo"%>
<%@page import="com.cvilla.medievalia.domain.InstanciaObjeto"%>
<%@page import="com.cvilla.medievalia.utils.Constants"%>
<%@page import="com.cvilla.medievalia.domain.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%
User user = (User) session.getAttribute("user");
String type= (String) request.getAttribute("message");
InstanciaObjeto objeto = (InstanciaObjeto) request.getAttribute("object");
boolean disabled = ((Integer) request.getAttribute("modo")).intValue() == 1;
@SuppressWarnings("unchecked")
List<TipoAtributoComplejo> listaTipos = (List<TipoAtributoComplejo>) request.getAttribute("tatributoc");
if(objeto != null){
	List<InstanciaAtributoComplejo> acl = objeto.getAtributosComplejos();
	%>
	<ul class="nav nav-tabs">
		<li class="active listaA" data-val="0"><a href="#"><fmt:message key="p2.2.detalle.atributos"></fmt:message></a></li>
		<%
		if(listaTipos != null && listaTipos.size() > 0){
			for(TipoAtributoComplejo ac : listaTipos){
			%>
		<li class="listaA" data-val="<%=ac.getIdTipoHijo()%>"><a href="#"><%=ac.getNombreAtributo() %></a></li>
		<%	}
		}%>
	</ul>
	<div id="modDetAtributos0">
	<%
	
	List<InstanciaAtributoSencillo> atrS = objeto.getAtributosSencillos();
	if(atrS != null && atrS.size() > 0){
		%>
		
			<form>
			<%
			for(InstanciaAtributoSencillo as : atrS){
				if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_FECHA){
					boolean empty = as.getValor() == null;
					Integer d =0,m=0,a=0;
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
							<input type="number" <%=disabled?"disabled":"" %> id="dia<%=as.getIdAtributo() %>" value="<%=empty?"":d%>" size="2" min="1" max="12" class="inputDays form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="mes"><fmt:message key="p2.2.detalle.mes"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="mes<%=as.getIdAtributo() %>" value="<%=empty?"":m%>" size="2" min="1" max="12" class="inputMonths form-control">
						</div>
						<div class="col-xs-3 form-group">
							<label for="anio"><fmt:message key="p2.2.detalle.anio"></fmt:message></label>
							<input type="number" <%=disabled?"disabled":"" %> id="anio<%=as.getIdAtributo() %>" value="<%=empty?"":a%>"  size="2" min="1" max="12" class="inputYears form-control"	>
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
							<input type="text" <%=disabled?"disabled":"" %> id="string<%=as.getIdAtributo() %>" value="<%=s!=null?s:""%>" class="form-control">
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
							<textarea <%=disabled?"disabled":"" %> id="text<%=as.getIdAtributo() %>" class="form-control"><%=s!=null?s:""%></textarea>
						</div>
					</div>
					<%
				}
				else if(as.getTipoAtributo() == Constants.TIPO_ATRIBUTO_OBJECT){
					InstanciaObjeto o = (InstanciaObjeto) as.getValor();
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
		for(TipoAtributoComplejo act : listaTipos){
			idPag++;	
			%>
	<div id="modDetAtributos<%=act.getIdTipoHijo() %>" class="modDetAtributosC">
		<ul class="list-group">
		<%	for(InstanciaAtributoComplejo ac : acl){
				if(ac.getTipoHijo().getTipoDOM() == act.getIdTipoHijo()){
					int cols;
					if(ac.getConFecha() == 1){
						cols = 4;
					}
					else{
						cols = 6;
					}
		%>
			<li class="list-group-item">
			<div class="row">
				<div class="col-xs-<%=cols%>">
				<%=ac.getInstanciaHijo().getNombre() %>
				<%if(!ac.isValidado()){ %>
					<span id="validado<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-warning textoNoValidaOBAC" data-textvalidacion="<%=ac.getInstanciaHijo().getTextoValidacion()%>" data-idPadretv="<%=ac.getIdInstanciaPadre() %>" data-idHijopv="<%=ac.getInstanciaHijo().getIdInstancia()%>" data-tipoH="<%=ac.getInstanciaHijo().getTipo().getTipoDOM()%>"><fmt:message key="general.novalidado"></fmt:message><%if(!ac.isTextoLeido()){ %><span class="label label-info">1</span> <%} %></span>
				<%}
				else{
					%>
					<span id="validado<%=ac.getInstanciaHijo().getIdInstancia() %>" class="label label-success textoValidaOBAC" data-textvalidacion="<%=ac.getInstanciaHijo().getTextoValidacion()%>" data-idPadretv="<%=ac.getIdInstanciaPadre() %>" data-idHijopv="<%=ac.getInstanciaHijo().getIdInstancia()%>" data-tipoH="<%=ac.getInstanciaHijo().getTipo().getTipoDOM()%>"><fmt:message key="general.validado"></fmt:message><%if(!ac.isTextoLeido()){ %><span class="label label-info">1</span> <%} %></span>
					<%
				}
				%>
				</div>
				<%if(ac.getConFecha() == 1){
					SpecialDate in = ac.getFechaInicio();
					SpecialDate fi = ac.getFechaFin();
				%>
				<div class="col-xs-<%=cols%>">
					<%if(in != null){%>
					<div class="row">
						<span><fmt:message key="general.from"></fmt:message> <%=in.getDia()%>-<%=in.getMes()%>-<%=in.getAnio()%></span>
					</div>
					<%}%>
					<%if(fi != null){%>
					<div class="row">
						<span><fmt:message key="general.to"></fmt:message> <%=fi.getDia()%>-<%=fi.getMes()%>-<%=fi.getAnio()%></span>
					</div>
					<%}%>
				</div>
				<%} %>
				<% if(ac.getIdTipoObjetoRelacion() != null && ac.getIdTipoObjetoRelacion() != 0){%>
				<div class="col-xs-<%=cols%>">
					<span class="pull-right"><%=ac.getInstanciaObjetoRelacion()!=null?ac.getInstanciaObjetoRelacion().getNombre():"" %><%if(ac.isConPagina()){ %>, <fmt:message key="general.pagina"></fmt:message> <%=ac.getPaginaDoc() %><%} %></span>
				</div>
				<%} %>
			</div>
			</li>
		<%
				}
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